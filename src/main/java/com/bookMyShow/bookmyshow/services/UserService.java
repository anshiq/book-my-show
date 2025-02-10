package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.UserDto;
import com.bookMyShow.bookmyshow.entity.User;

import java.util.concurrent.ExecutionException;

public interface UserService {
    User addUser(UserDto userDto) throws ExecutionException;

    boolean ifUserExists(String email,String phoneNumber);
    User findUser();

}
