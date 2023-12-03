package com.ismail.creatvt.bookmyshow.controllers;

import com.ismail.creatvt.bookmyshow.dtos.BookMovieRequestDTO;
import com.ismail.creatvt.bookmyshow.dtos.BookMovieResponseDTO;
import com.ismail.creatvt.bookmyshow.dtos.ResponseStatus;
import com.ismail.creatvt.bookmyshow.model.Booking;
import com.ismail.creatvt.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDTO bookMovie(BookMovieRequestDTO data) {
        BookMovieResponseDTO response = new BookMovieResponseDTO();
        try {
            Booking booking = bookingService.bookMovie(data.getUserId(), data.getShowId(), data.getSeatIds());
            response.setState(ResponseStatus.SUCCESS);
            response.setBooking(booking);
        } catch(Exception e) {
            response.setFailureReason(e.getMessage());
            response.setState(ResponseStatus.FAILURE);
        }
        return response;
    }
}
