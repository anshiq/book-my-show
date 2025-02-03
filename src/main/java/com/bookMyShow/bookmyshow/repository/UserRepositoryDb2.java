package com.bookMyShow.bookmyshow.repository;

import com.bookMyShow.bookmyshow.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryDb2 extends MongoRepository<User,String> {
    @Query("{ '$or' : [ { 'email' : ?0 },{ 'phoneNumber' : ?0 } ] }")
    public User ifUserExists(String email,String phoneNumber);
}
