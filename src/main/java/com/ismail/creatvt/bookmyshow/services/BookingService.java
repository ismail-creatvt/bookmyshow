package com.ismail.creatvt.bookmyshow.services;

import com.ismail.creatvt.bookmyshow.exceptions.InvalidShowException;
import com.ismail.creatvt.bookmyshow.exceptions.InvalidUserException;
import com.ismail.creatvt.bookmyshow.exceptions.SeatNotAvailableException;
import com.ismail.creatvt.bookmyshow.model.*;
import com.ismail.creatvt.bookmyshow.repos.BookingRepository;
import com.ismail.creatvt.bookmyshow.repos.ShowRepository;
import com.ismail.creatvt.bookmyshow.repos.ShowSeatRepository;
import com.ismail.creatvt.bookmyshow.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PriceCalculatorService priceCalculatorService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculatorService priceCalculatorService, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(long userId, long showId, List<Long> seatIds) throws InvalidUserException, InvalidShowException, SeatNotAvailableException {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new InvalidUserException(userId);
        }

        User user = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);

        if (showOptional.isEmpty()) {
            throw new InvalidShowException(showId);
        }

        Show show = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);

        checkIfAvailable(showSeats);

        List<ShowSeat> updatedShowSeats = updateStatusToBlocked(showSeats);

        int amount = priceCalculatorService.calculatePrice(updatedShowSeats, show);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(updatedShowSeats);
        booking.setAmount(amount);

        return bookingRepository.save(booking);
    }

    private List<ShowSeat> updateStatusToBlocked(List<ShowSeat> showSeats) {
        List<ShowSeat> updated = new ArrayList<>();
        for(ShowSeat showSeat: showSeats) {
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            updated.add(showSeatRepository.save(showSeat));
        }
        return updated;
    }

    private void checkIfAvailable(List<ShowSeat> showSeats) throws SeatNotAvailableException {
        for (ShowSeat showSeat : showSeats) {
            if (!isSeatAvailable(showSeat)) {
                throw new SeatNotAvailableException();
            }
        }
    }

    private boolean isSeatAvailable(ShowSeat showSeat) {
        ShowSeatStatus status = showSeat.getStatus();
        if (status.equals(ShowSeatStatus.AVAILABLE)) return true;

        if (status.equals(ShowSeatStatus.BLOCKED)) {
            return isBlockExpired(showSeat.getBlockedAt());
        }

        return false;
    }

    private boolean isBlockExpired(Date blockedAt) {
        Date now = new Date();
        return now.getTime() - blockedAt.getTime() >= Duration.ofMinutes(15).toMillis();
    }
}
