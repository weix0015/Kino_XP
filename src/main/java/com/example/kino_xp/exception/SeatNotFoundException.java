package com.example.kino_xp.exception;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException(String message) {
        super(message);
    }

}
