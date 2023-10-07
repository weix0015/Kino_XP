package com.example.kino_xp.dto;

import com.example.kino_xp.model.Movie;
import com.example.kino_xp.model.Ticket;

import java.time.LocalDateTime;

public record ViewingDTO(int id, LocalDateTime showTime, int hall, LocalDateTime showEndTime, Ticket ticket, Movie movie) {


}
