package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.MovieDto;
import com.bookMyShow.bookmyshow.entity.Movie;
import com.bookMyShow.bookmyshow.entity.MovieElastic;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.Optional;

public interface MovieService {
    Movie addMovie(MovieDto movieDto);
    ResponseEntity<?> allMovies();

    Optional<MovieElastic> ifMoviePresent(String id);


}
