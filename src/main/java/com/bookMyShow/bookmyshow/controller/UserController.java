package com.bookMyShow.bookmyshow.controller;

import com.bookMyShow.bookmyshow.dto.ConfigDto;
import com.bookMyShow.bookmyshow.dto.TicketDto;
import com.bookMyShow.bookmyshow.dto.TicketResponseDto;
import com.bookMyShow.bookmyshow.dto.UserDto;
import com.bookMyShow.bookmyshow.entity.User;
import com.bookMyShow.bookmyshow.services.*;
import com.bookMyShow.bookmyshow.services.serviceimplementation.MovieElasticsearching;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@ControllerAdvice
public class UserController {

    private final UserService userService;
    private final MovieService movieService;
    private final TicketService ticketService;
    private final ShowService showService;
    private final MovieElasticsearching movieElasticsearching;
    private final ConfigService_book configService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) throws ExecutionException {
        User user = userService.addUser(userDto);
        System.out.println(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity<?> allMovies() {
            return movieService.allMovies();
    }

    @GetMapping("find-by-user-id")
    public ResponseEntity<?> user(){
        return new ResponseEntity<>(userService.findUser(),HttpStatus.OK);
    }

    @GetMapping("/movie")
    public ResponseEntity<?> movie(@RequestParam("query") String query){
        return new ResponseEntity<>(movieElasticsearching.findByKey(query),HttpStatus.OK);
    }
    @GetMapping("/shows")
    public ResponseEntity<?> getShows(@RequestParam("id") String id) {
        return new ResponseEntity<>(showService.movieShows(id),HttpStatus.OK);
    }

    @PostMapping("/book-tickets")
    public ResponseEntity<?> bookTicket(@RequestBody TicketDto ticketDto) {
            return new ResponseEntity<>(ticketService.bookTicket(ticketDto), HttpStatus.CREATED);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketResponseDto>> allTickets() {
            return new ResponseEntity<>(ticketService.allTickets(), HttpStatus.OK);
    }

    @DeleteMapping("/cancel-tickets")
    public ResponseEntity<?> cancelTicket(@RequestParam("id") String id){
           return new ResponseEntity<>(ticketService.cancelTicket(id),HttpStatus.OK);
    }

    @PostMapping("/db-config")
    public ResponseEntity<?> setConfig(@RequestBody ConfigDto configDto){
        return new ResponseEntity<>(configService.setConfig(configDto), HttpStatus.CREATED);
    }
}
