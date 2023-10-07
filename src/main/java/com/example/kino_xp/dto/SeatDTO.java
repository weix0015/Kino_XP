package com.example.kino_xp.dto;

import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.model.Ticket;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public record SeatDTO(int seatNumber, SeatRow row, Ticket ticket) {
}
