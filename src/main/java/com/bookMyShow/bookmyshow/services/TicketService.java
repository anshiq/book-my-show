package com.bookMyShow.bookmyshow.services;

import com.bookMyShow.bookmyshow.dto.TicketDto;
import com.bookMyShow.bookmyshow.dto.TicketResponseDto;
import com.bookMyShow.bookmyshow.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(TicketDto ticketDto);
    List<TicketResponseDto> allTickets();
    String cancelTicket(String id);
}
