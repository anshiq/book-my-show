package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TheatreRepository extends MongoRepository<Theatre, String> {
}
