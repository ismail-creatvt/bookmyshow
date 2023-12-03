package com.ismail.creatvt.bookmyshow.exceptions;

public class SeatNotAvailableException extends Exception {

    public SeatNotAvailableException() {
        super("All or part of selected seats are not available for booking");
    }
}
