package com.example.kino_xp.exception;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message){
        super(message);
    }
}
