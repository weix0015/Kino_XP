package com.example.kino_xp.exception;

public class SeatRowNotFoundException extends RuntimeException{
    public SeatRowNotFoundException(String message) {
        super(message);
    }

}
