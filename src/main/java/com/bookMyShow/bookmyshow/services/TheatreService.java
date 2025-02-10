package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.TheatreDto;
import com.bookMyShow.bookmyshow.entity.Theatre;

public interface TheatreService {

    Theatre addTheatre(TheatreDto theatreDto);
}
