package com.bookMyShow.bookmyshow.controller;

import com.bookMyShow.bookmyshow.dto.MovieDto;
import com.bookMyShow.bookmyshow.dto.ScreenDto;
import com.bookMyShow.bookmyshow.dto.ShowDto;
import com.bookMyShow.bookmyshow.dto.TheatreDto;
import com.bookMyShow.bookmyshow.entity.*;
import com.bookMyShow.bookmyshow.services.MovieService;
import com.bookMyShow.bookmyshow.services.ScreenService;
import com.bookMyShow.bookmyshow.services.ShowService;
import com.bookMyShow.bookmyshow.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ScreenService screenService;
    private final ShowService showService;

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.addMovie(movieDto), HttpStatus.CREATED);
    }

    @PostMapping("/theatre")
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreDto theatreDto) {
        return new ResponseEntity<>(theatreService.addTheatre(theatreDto), HttpStatus.CREATED);
    }

    @PostMapping("/screen")
    public ResponseEntity<Screen> addScreen(@RequestBody ScreenDto screenDto) {
        return new ResponseEntity<>(screenService.addScreen(screenDto), HttpStatus.CREATED);
    }

    @PostMapping("/show")
    public ResponseEntity<Show> addShow(@RequestBody ShowDto showDto) {
        return new ResponseEntity<>(showService.addShow(showDto), HttpStatus.CREATED);
    }
}
