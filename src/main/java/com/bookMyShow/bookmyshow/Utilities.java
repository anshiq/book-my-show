package com.bookMyShow.bookmyshow;

import com.bookMyShow.bookmyshow.dto.*;
import com.bookMyShow.bookmyshow.entity.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.threads.ScheduledThreadPoolExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import java.util.concurrent.Executors;

import static com.bookMyShow.bookmyshow.userfilter.UserContext.getUserThreadId;

@UtilityClass
public class Utilities {


    public static AllMovieShows makeAllMovieShows(MovieElastic movieElastic, Theatre theatre, Screen screen) {

            return AllMovieShows.builder()
                    .movieName(movieElastic.getName())
                    .persona(movieElastic.getPersona())
                    .movieType(movieElastic.getMovieType())
                    .theatreName(theatre.getName())
                    .theatreLocation(theatre.getLocation())
                    .theatreCity(theatre.getCity())
                    .screen(screen.getName())
                    .build();

    }

    public static MovieElastic makeMovieElastic(MovieDto movieDto) {
        return MovieElastic.builder()
                .id(UUID.randomUUID().toString())
                .name(movieDto.getName())
                .persona(movieDto.getPersona())
                .movieType(movieDto.getMovieType())
                .lastUpdated(movieDto.getLastUpdated())
                .build();
    }

    public static Movie makeMovie(MovieElastic movieElastic) {
        return Movie.builder()
                .id(movieElastic.getId())
                .name(movieElastic.getName())
                .persona(movieElastic.getPersona())
                .movieType(movieElastic.getMovieType())
                .lastUpdated(movieElastic.getLastUpdated())
                .build();
    }

    public static MovieElastic movieToMovieElastic(Movie movie) {
        return MovieElastic.builder()
                .id(movie.getId())
                .name(movie.getName())
                .persona(movie.getPersona())
                .movieType(movie.getMovieType())
                .lastUpdated(movie.getLastUpdated())
                .build();
    }

    public static Screen makeScreen(ScreenDto screenDto) {
        return Screen.builder()
                .id(UUID.randomUUID().toString())
                .name(screenDto.getName())
                .theatreId(screenDto.getTheatreId())
                .build();

    }

    public static Show makeShow(ShowDto showDto) {
        return Show.builder()
                .id(UUID.randomUUID().toString())
                .startTime(showDto.getStartTime())
                .endTime(showDto.getEndTime())
                .movieId(showDto.getMovieId())
                .theatreId(showDto.getTheatreId())
                .screenId(showDto.getScreenId())
                .seatType(showDto.getSeatType())
                .seatMarking(showDto.getSeatMarking())
                .build();
    }

    public static Theatre makeTheatre(TheatreDto theatreDto) {
        return Theatre.builder()
                .id(UUID.randomUUID().toString())
                .name(theatreDto.getName())
                .location(theatreDto.getLocation())
                .city(theatreDto.getCity())
                .build();

    }

    public static Ticket makeTicket(TicketDto ticketDto,int pr,Seat seat) {

        return Ticket.builder()
                .id(UUID.randomUUID().toString())
                .seatNumber(ticketDto.getSeatNumber())
                .seatCategory(seat.getSeatType())
                .price(pr)
                .showId(ticketDto.getShowId())
                .bookedByUserId(getUserThreadId())
                .status(true)
                .paymentStatus(true)
                .paymentMethod(ticketDto.getPaymentMethod())
                .build();
    }


    public static TicketResponseDto makeTicketResponse(Ticket ticket,Show show,AllMovieShows allMovieShows) {
      return  TicketResponseDto.builder()
                .seatNumber(ticket.getSeatNumber())
                .seatCategory(ticket.getSeatCategory())
                .price(ticket.getPrice())
                .showDate(show.getStartTime())
                .description(allMovieShows)
                .bookedStatus(ticket.getStatus())
                .paymentStatus(ticket.getPaymentStatus())
                .build();
    }

    public static User makeUser(UserDto userDto) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(userDto.getName())
                .age(userDto.getAge())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

    }

    public static Config makeConfig(ConfigDto configDto){
        return Config.builder()
                .id(configDto.getDbName()+"_"+configDto.getHashId())
                .hashId(configDto.getHashId())
                .dbName(configDto.getDbName())
                .dbType(configDto.getDbType())
                .userName(configDto.getUserName())
                .password(configDto.getPassword())
                .host(configDto.getHost())
                .build();
    }

public static boolean compareTime(String dateString,String currTime){
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    try {
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);

        LocalDateTime now = LocalDateTime.parse(currTime,formatter);

        if (date.isBefore(now)) {
            return true;
        } else
            return false;
    } catch (DateTimeParseException e) {
        System.err.println("Error parsing date string: " + e.getMessage());
    }
    return false;
}

    @Setter
    @Getter
    public static class Persona {
        String name;
        String type;//lead , actor, producer,director
    }
}
