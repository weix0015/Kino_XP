package com.example.kino_xp.exception;

public class ViewingNotFoundException extends RuntimeException{
    public ViewingNotFoundException(String message){
        super(message);
    }
}
