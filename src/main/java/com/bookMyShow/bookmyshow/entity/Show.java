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
@Document(collection = "show")
public class Show {
    @Id
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieId;
    private String theatreId;
    private String screenId;
    private List<com.bookMyShow.bookmyshow.dto.SeatType> seatType;
    private List<Integer> seatMarking;
}
