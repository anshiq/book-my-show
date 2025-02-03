package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Screen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScreenRepository extends MongoRepository<Screen,String> {
}
