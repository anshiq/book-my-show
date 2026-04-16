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
public class ShowDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieId;
    private String theatreId;
    private String screenId;
    private List<SeatType> seatType;
    private List<Integer> seatMarking;
}
