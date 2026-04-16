package com.bookMyShow.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private Integer seatNumber;
    private String seatCategory;
    private Integer price;
    private LocalDateTime showDate;
    private AllMovieShows description;
    private Boolean bookedStatus;
    private Boolean paymentStatus;
}
