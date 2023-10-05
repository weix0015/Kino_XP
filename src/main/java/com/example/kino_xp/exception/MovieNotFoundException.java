package com.example.kino_xp.exception;

public class MovieNotFoundException extends RuntimeException {
  public MovieNotFoundException(String message) {
    super(message);
  }
}
