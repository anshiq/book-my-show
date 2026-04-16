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
@Document(collection = "ticket")
public class Ticket {
    @Id
    private String id;
    private Integer seatNumber;
    private String seatCategory;
    private Integer price;
    private String showId;
    private String bookedByUserId;
    private Boolean status;
    private Boolean paymentStatus;
    private String paymentMethod;
}
