package com.example.kino_xp.dto;

import com.example.kino_xp.model.User;
import com.example.kino_xp.model.Viewing;

import java.time.LocalDateTime;

public record TicketDTO(int id, User user, LocalDateTime dateOfPurchase, double price, Viewing viewing, int hall) {
    @Override
    public int id() {
        return id;
    }

    @Override
    public User user(){return user;}

    @Override
    public LocalDateTime dateOfPurchase() {
        return dateOfPurchase;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public Viewing viewing() {
        return viewing;
    }

    @Override
    public int hall() {
        return hall;
    }
}