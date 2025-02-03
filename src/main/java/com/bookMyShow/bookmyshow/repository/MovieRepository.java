package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    @Query("{ 'lastUpdated' : { '$gte' : ?0 } }")
    List<Movie> findAfterLastUpdatedTime(String lastUpdatedTime);

}
