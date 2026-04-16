package com.bookMyShow.bookmyshow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movie")
public class Movie {
    @Id
    private String id;
    private String name;
    private List<com.bookMyShow.bookmyshow.dto.Persona> persona;
    private String movieType;
    private LocalDateTime lastUpdated;
}
