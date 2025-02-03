package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    @Query("{'bookedByUserId' : ?0 }")
    List<Ticket> findByBookedByUserId(String bookedByUserId);

}
