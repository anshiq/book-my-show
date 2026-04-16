package com.bookMyShow.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllMovieShows {
    private String movieName;
    private List<Persona> persona;
    private String movieType;
    private String theatreName;
    private String theatreLocation;
    private String theatreCity;
    private String screen;
}
