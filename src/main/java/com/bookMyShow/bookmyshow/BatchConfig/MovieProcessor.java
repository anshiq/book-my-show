//package com.bookMyShow.bookmyshow.BatchConfig;
//
//import com.bookMyShow.bookmyshow.entity.Movie;
//import com.bookMyShow.bookmyshow.entity.MovieElastic;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import static com.bookMyShow.bookmyshow.Utilities.compareTime;
//import static com.bookMyShow.bookmyshow.Utilities.movieToMovieElastic;
//
//@RequiredArgsConstructor
//public class MovieProcessor implements ItemProcessor<Movie, MovieElastic> {
//
//    private final RedisTemplate redisTemplate;
//
//    @Override
//    public MovieElastic process(Movie movie) throws Exception {
//        if(compareTime(movie.getLastUpdated(),redisTemplate.opsForValue().get("lastUpdatedTime").toString()))
//        return movieToMovieElastic(movie);
//        else return null;
//    }
//}
