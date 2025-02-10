package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.dto.TheatreDto;
import com.bookMyShow.bookmyshow.entity.Theatre;
import com.bookMyShow.bookmyshow.repository.TheatreRepository;
import com.bookMyShow.bookmyshow.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bookMyShow.bookmyshow.Utilities.makeTheatre;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    public Theatre addTheatre(TheatreDto theatreDto){
        Theatre theatre = makeTheatre(theatreDto);
        return theatreRepository.save(theatre);
    }
}
