package com.bookMyShow.bookmyshow.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ErrorResults {

    USER_ALREADY_EXISTS(HttpStatus.CONFLICT,"User Already Exists"),
    MOVIE_NOT_FOUND(HttpStatus.NOT_FOUND,"Movies Not Found"),
    SEAT_NOT_AVAILABLE(HttpStatus.NOT_FOUND,"Seat Not Available");



    private final HttpStatus status;
    private final String message;
}
