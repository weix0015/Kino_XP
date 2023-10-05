package com.example.kino_xp.dto;

import com.example.kino_xp.model.Ticket;

import java.time.LocalDateTime;

public record ViewingDTO(int id, LocalDateTime showTime, int hall, LocalDateTime showEndTime, Ticket ticket) {

    @Override
    public int id() {
        return id;
    }

    @Override
    public LocalDateTime showTime() {
        return showTime;
    }

    @Override
    public int hall() {
        return hall;
    }

    @Override
    public LocalDateTime showEndTime() {
        return showEndTime;
    }

    @Override
    public Ticket ticket() {
        return ticket;
    }
}
