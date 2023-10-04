package com.example.kino_xp.dto;

import java.time.LocalDateTime;

public record ViewingDTO(int id, LocalDateTime showTime, int hall, LocalDateTime showEndTime) {

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
}
