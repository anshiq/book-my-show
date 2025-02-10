package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.ScreenDto;
import com.bookMyShow.bookmyshow.entity.Screen;

public interface ScreenService {

    Screen addScreen(ScreenDto screenDto);
}
