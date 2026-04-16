package com.bookMyShow.bookmyshow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "seat")
public class Seat {
    @Id
    private String id;
    private Integer seatNumber;
    private String seatType;
    private String showId;
    private Boolean bookingStatus;
    private Integer version;
}
