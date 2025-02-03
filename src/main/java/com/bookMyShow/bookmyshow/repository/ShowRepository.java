package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends MongoRepository<Show, String> {
    @Query("{ '$or' : [ { 'movieId' : ?0 } ] }")
    public List<Show> allShows(String uuid);
}
