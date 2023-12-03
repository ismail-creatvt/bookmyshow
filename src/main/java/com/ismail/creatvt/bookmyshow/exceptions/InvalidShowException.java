package com.ismail.creatvt.bookmyshow.exceptions;

public class InvalidShowException extends Exception {
    public InvalidShowException(long showId) {
        super("Show with showId " + showId + " doesn't exist");
    }
}
