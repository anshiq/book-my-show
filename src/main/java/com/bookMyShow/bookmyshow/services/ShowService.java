package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.AllMovieShows;
import com.bookMyShow.bookmyshow.dto.ShowDto;
import com.bookMyShow.bookmyshow.entity.Show;

import java.util.List;

public interface ShowService {
    Show addShow(ShowDto showDto);

    List<AllMovieShows> movieShows(String uuid);
}
