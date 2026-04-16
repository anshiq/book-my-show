package com.bookMyShow.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private String name;
    private java.util.List<Persona> persona;
    private String movieType;
    private LocalDateTime lastUpdated;
}
