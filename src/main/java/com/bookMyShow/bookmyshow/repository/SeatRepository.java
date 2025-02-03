package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends MongoRepository<Seat,String> {
    @Query("{ '$and' : [ { 'showId' : ?0 }, { 'seatNumber' : ?1 } ] }")
    Optional<Seat> findByNumberAndShowId(String showId, Integer seatNumber);

}
