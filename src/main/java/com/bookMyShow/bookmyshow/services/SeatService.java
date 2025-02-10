package com.bookMyShow.bookmyshow.services;


import com.bookMyShow.bookmyshow.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SeatService {
    boolean ifSeatAvailable(String showId,Integer seatNumber);

    Seat changeStatusofSeat(boolean bool, String showId, Integer seatNumber);

    Optional<Seat> findByNumberAndShowId(String showId, Integer seatNumber);
}
