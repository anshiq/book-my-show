package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.entity.Seat;
import com.bookMyShow.bookmyshow.repository.SeatRepository;
import com.bookMyShow.bookmyshow.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Qualifier("newdb1MongoTemplate")
    private final MongoTemplate newdb1MongoTemplate;

    public boolean ifSeatAvailable(String showId,Integer seatNumber){
        Optional<Seat> seat  = seatRepository.findByNumberAndShowId(showId,seatNumber);
        return seat.get().getBookingStatus();
    }

    public Seat changeStatusofSeat(boolean bool, String showId, Integer seatNumber){
        Optional<Seat> seat  = seatRepository.findByNumberAndShowId(showId,seatNumber);

        if(seat.isPresent()) {
            if(bool)seat.get().setBookingStatus(Boolean.TRUE);
            else seat.get().setBookingStatus(Boolean.FALSE);
            seatRepository.save(seat.get());
        }

        Seat savedSeat = null;
        Integer version = seat.get().getVersion();

        for(int i = 0; i<3; i++) {
            Query query = new Query(Criteria.where("version").is(version));
            Update update = new Update().inc("version", 2).setOnInsert("status", Boolean.TRUE);

            savedSeat = newdb1MongoTemplate.update(Seat.class).matching(query).apply(update).findAndModifyValue();

            if(savedSeat!=null)break;

            if(i==2)throw new RuntimeException("Seat Cannot Be booked");
        }

        return savedSeat;

    }

    public Optional<Seat> findByNumberAndShowId(String showId, Integer seatNumber){
        return seatRepository.findByNumberAndShowId(showId,seatNumber);
    }
}
