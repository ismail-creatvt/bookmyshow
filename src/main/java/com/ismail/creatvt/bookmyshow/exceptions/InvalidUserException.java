package com.ismail.creatvt.bookmyshow.exceptions;

public class InvalidUserException extends Exception {
    public InvalidUserException(long userId) {
        super("No user with userId " + userId + " exists");
    }
}
