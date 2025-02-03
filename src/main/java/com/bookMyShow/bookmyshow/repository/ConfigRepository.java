package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.Config;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends MongoRepository<Config,String> {
}
