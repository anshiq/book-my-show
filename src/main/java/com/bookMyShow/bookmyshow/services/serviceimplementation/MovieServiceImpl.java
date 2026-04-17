package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.dto.MovieDto;
import com.bookMyShow.bookmyshow.entity.Movie;
import com.bookMyShow.bookmyshow.entity.MovieElastic;
import com.bookMyShow.bookmyshow.exceptions.ErrorResults;
import com.bookMyShow.bookmyshow.exceptions.PostExceptions;
import com.bookMyShow.bookmyshow.repository.MovieElasticRepository;
import com.bookMyShow.bookmyshow.repository.MovieRepository;
import com.bookMyShow.bookmyshow.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.bookMyShow.bookmyshow.Utilities.*;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final RedisTemplate redisTemplate;
    private final MovieRepository movieRepository;
    @Autowired(required = false)
    private MovieElasticRepository movieElasticRepository;

    public Movie addMovie(MovieDto movieDto) {
        MovieElastic movieElastic = makeMovieElastic(movieDto);
        Movie movie = makeMovie(movieElastic);
        return movieRepository.save(movie);
    }

    public ResponseEntity<?> allMovies() {
        if (movieElasticRepository == null) {
            throw new PostExceptions(ErrorResults.MOVIE_NOT_FOUND);
        }
        List<MovieElastic> movieElastics = new ArrayList<>();
        movieElasticRepository.findAll().forEach(movieElastics::add);
        if (!movieElastics.isEmpty()) return new ResponseEntity<>(movieElastics, HttpStatus.OK);
        throw new PostExceptions(ErrorResults.MOVIE_NOT_FOUND);
    }

    public Optional<MovieElastic> ifMoviePresent(String id) {
        if (movieElasticRepository == null) {
            return Optional.empty();
        }
        return movieElasticRepository.findById(id);
    }

//        @Scheduled(fixedDelay = 10000)
    public void ingestMoviesFromDatabase(){
//         redis lock -> distributed lock
//         currTime
//         redis.getLastValue()
//         movieRepo.findAllGTRedisLastValue //paginate using batch iterator
//         es.save
//         redis.save(currTime)
//         unlock

        if (movieElasticRepository == null) {
            System.out.println("Elasticsearch not available, skipping ingestion");
            return;
        }
        movieElasticRepository.deleteAll();

        String lastUpdatedTime;


        if( redisTemplate.opsForValue().get("lastUpdatedTime")==null)lastUpdatedTime="1970-01-01T00:00:00";
        else lastUpdatedTime= Objects.requireNonNull(redisTemplate.opsForValue().get("lastUpdatedTime")).toString();

        String currTime = LocalDateTime.now().toString();

        List<Movie> movies = movieRepository.findAfterLastUpdatedTime(lastUpdatedTime);



        System.out.println("running ingestion" + currTime);

        List<MovieElastic> movieElastics = movies.stream().map(movie-> movieToMovieElastic(movie)).toList();
                movieElasticRepository.saveAll(movieElastics);

        redisTemplate.opsForValue().set("lastUpdatedTime", currTime);
    }

//    @Scheduled(fixedDelay = 10000)
//    public void ingestMoviesFromDatabaseUsingBatchProcessing() {
//        movieElasticRepository.deleteAll();
//
//        String lastUpdatedTime = (String) redisTemplate.opsForValue().get("lastUpdatedTime");
//        if (lastUpdatedTime == null) lastUpdatedTime = "1970-01-01T00:00:00";
//
//        String currTime = LocalDateTime.now().toString();
//
//        System.out.println("Running ingestion at: " + currTime);
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters();
//
//        try {
//            jobLauncher.run(job, jobParameters);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to execute batch job", e);
//        }
//
//        redisTemplate.opsForValue().set("lastUpdatedTime", currTime);
//    }
}
