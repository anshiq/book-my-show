package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.dto.AllMovieShows;
import com.bookMyShow.bookmyshow.dto.ShowDto;
import com.bookMyShow.bookmyshow.entity.*;
import com.bookMyShow.bookmyshow.exceptions.ErrorResults;
import com.bookMyShow.bookmyshow.exceptions.PostExceptions;
import com.bookMyShow.bookmyshow.repository.*;
import com.bookMyShow.bookmyshow.services.MovieService;
import com.bookMyShow.bookmyshow.services.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bookMyShow.bookmyshow.Utilities.*;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final MovieService movieService;
    @Autowired(required = false)
    private MovieElasticRepository movieElasticRepository;

    public Show addShow(ShowDto showDto) {
        boolean res1 = movieElasticRepository != null && movieElasticRepository.findById(showDto.getMovieId()).isPresent();
        boolean res2 = screenRepository.findById(showDto.getScreenId()).isPresent();
        boolean res3 = theatreRepository.findById(showDto.getTheatreId()).isPresent();

        Show show = makeShow(showDto);

        if (!(res1 && res2 && res3)) return show;

        int x = 0;

        for (int i = 0; i < showDto.getSeatMarking().size(); i++) {
            for (int j = 0; j < showDto.getSeatMarking().get(i); j++) {
                x++;
                Seat seat = Seat.builder()
                        .id(UUID.randomUUID().toString())
                        .seatNumber(x)
                        .seatType(showDto.getSeatType().get(i).getType())
                        .showId(show.getId())
                        .bookingStatus(Boolean.FALSE)
                        .build();
                seatRepository.save(seat);
            }
        }


        showRepository.save(show);


        return show;
    }

    public List<AllMovieShows> movieShows(String id) {

        if (movieService.ifMoviePresent(id).isEmpty()) throw new PostExceptions(ErrorResults.MOVIE_NOT_FOUND);
        List<Show> sh = showRepository.allShows(id);
        return sh.stream().map(a -> {

            MovieElastic movieElastic = movieElasticRepository != null ? movieElasticRepository.findById(a.getMovieId()).orElse(null) : null;
            Theatre theatre = theatreRepository.findById(a.getTheatreId()).orElse(null);
            Screen screen = screenRepository.findById(a.getScreenId()).orElse(null);

            if (movieElastic == null || theatre == null || screen == null) {
                return null;
            }
            return makeAllMovieShows(movieElastic, theatre, screen);
        }).filter(Objects::nonNull).toList();
    }

//    @Scheduled(fixedRateString = "PT24H")
//    public void showExpiration() {
//        List<Show> shows = showRepository.findAll();
//
//        showRepository.deleteAll();
//
//        shows.parallelStream().forEach(show->
//                {
//                    if(!compareTime(show.getEndTime().toString())){
//                        showRepository.save(show);
//                    }
//                }
//        );
//    }

}
