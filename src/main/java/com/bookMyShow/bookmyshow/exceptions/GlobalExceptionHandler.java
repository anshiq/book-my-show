package com.bookMyShow.bookmyshow.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PostExceptions.class})
    public ResponseEntity<ErrorResponse> handlePostExceptions(PostExceptions exceptions){
      return this.makeErrorResponseEntity(exceptions.getErrorResults());
    }

    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(ErrorResults errorResults) {
        return ResponseEntity.status(errorResults.getStatus())
                .body(new ErrorResponse(errorResults.name(), errorResults.getMessage()));
    }


    @Getter
    @Setter
    @AllArgsConstructor
    static class ErrorResponse{
        private String code;
        private String message;
    }
}
