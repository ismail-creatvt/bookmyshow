package com.ismail.creatvt.bookmyshow.dtos;

import com.ismail.creatvt.bookmyshow.model.Booking;
import lombok.Data;

@Data
public class BookMovieResponseDTO {
    private Booking booking;
    private ResponseStatus state;
    private String failureReason;
}
