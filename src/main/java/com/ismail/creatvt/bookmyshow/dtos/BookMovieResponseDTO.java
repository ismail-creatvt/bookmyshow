package com.ismail.creatvt.bookmyshow.dtos;

import com.ismail.creatvt.bookmyshow.model.Booking;
import lombok.Data;

@Data
public class BookMovieResponseDTO {
    private long bookingId;
    private int amount;
    private ResponseStatus state;
    private String failureReason;
}
