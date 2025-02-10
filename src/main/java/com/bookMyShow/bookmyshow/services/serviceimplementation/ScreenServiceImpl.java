package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.dto.ScreenDto;
import com.bookMyShow.bookmyshow.entity.Screen;
import com.bookMyShow.bookmyshow.repository.ScreenRepository;
import com.bookMyShow.bookmyshow.services.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bookMyShow.bookmyshow.Utilities.makeScreen;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {
    private final ScreenRepository screenRepository;

    public Screen addScreen(ScreenDto screenDto){
        Screen screen = makeScreen(screenDto);
        return screenRepository.save(screen);
    }
}
