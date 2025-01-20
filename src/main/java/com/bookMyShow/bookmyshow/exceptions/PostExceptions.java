package com.bookMyShow.bookmyshow.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostExceptions extends RuntimeException {
    private final ErrorResults errorResults;
}
