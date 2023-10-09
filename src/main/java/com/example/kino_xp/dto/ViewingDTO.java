package com.example.kino_xp.dto;

import com.example.kino_xp.model.Movie;
import com.example.kino_xp.model.Ticket;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.List;

public record ViewingDTO(int id, LocalDateTime showTime, int hall, LocalDateTime showEndTime, List<Ticket> tickets, Movie movie) {


}
