package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.dto.AllMovieShows;
import com.bookMyShow.bookmyshow.dto.TicketDto;
import com.bookMyShow.bookmyshow.dto.TicketResponseDto;
import com.bookMyShow.bookmyshow.entity.*;
import com.bookMyShow.bookmyshow.exceptions.ErrorResults;
import com.bookMyShow.bookmyshow.exceptions.PostExceptions;
//import com.bookMyShow.bookmyshow.payment.PaytmPaymentProcessot;
import com.bookMyShow.bookmyshow.payment.PaymentProvider;
import com.bookMyShow.bookmyshow.payment.PaymentProviderFactory;
import com.bookMyShow.bookmyshow.repository.*;
import com.bookMyShow.bookmyshow.services.SeatService;
import com.bookMyShow.bookmyshow.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.bookMyShow.bookmyshow.Utilities.*;
import static com.bookMyShow.bookmyshow.userfilter.UserContext.getUserThreadId;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final ShowRepository showRepository;
    private final TheatreRepository theatreRepository;
    private final ScreenRepository screenRepository;
    @Autowired(required = false)
    private MovieElasticRepository movieElasticRepository;

    private final PaymentProviderFactory paymentProviderFactory;

    @Transactional
    public Ticket bookTicket(TicketDto ticketDto) {
        if (seatService.ifSeatAvailable(ticketDto.getShowId(), ticketDto.getSeatNumber()))
            throw new PostExceptions(ErrorResults.SEAT_NOT_AVAILABLE);

        Seat seat = seatService.findByNumberAndShowId(ticketDto.getShowId(), ticketDto.getSeatNumber()).get();

        Show show = showRepository.findById(ticketDto.getShowId()).get();

        int pr = show.getSeatType().stream().filter(k -> k.getType().equals(seat.getSeatType())).findFirst().get().getPrice();

        paymentProviderFactory.getPaymentProcessor(PaymentProvider.PAYTM);


        seatService.changeStatusofSeat(true, ticketDto.getShowId(), ticketDto.getSeatNumber());

        return ticketRepository.save(makeTicket(ticketDto, pr, seat));
    }

    public List<TicketResponseDto> allTickets() {


        List<Ticket> ticket = ticketRepository.findByBookedByUserId(getUserThreadId());

        return ticket.stream().map(a -> {
            Show show = showRepository.findById(a.getShowId()).orElse(null);
            if (show == null) return null;
            MovieElastic movieElastic = movieElasticRepository != null ? movieElasticRepository.findById(show.getMovieId()).orElse(null) : null;
            Theatre theatre = theatreRepository.findById(show.getTheatreId()).orElse(null);
            Screen screen = screenRepository.findById(show.getScreenId()).orElse(null);

            if (movieElastic == null || theatre == null || screen == null) return null;
            AllMovieShows allMovieShows = makeAllMovieShows(movieElastic, theatre, screen);

            return makeTicketResponse(a, show, allMovieShows);
        }).filter(Objects::nonNull).toList();

    }

    public String cancelTicket(String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            seatService.changeStatusofSeat(false, ticket.get().getShowId(), ticket.get().getSeatNumber());
            ticketRepository.deleteById(id);
            return "Ticket Cancelled Successfully";

        } else return "NO Ticket Found";
    }
}
