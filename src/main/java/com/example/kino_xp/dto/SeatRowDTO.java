package com.example.kino_xp.dto;

import com.example.kino_xp.model.Hall;
import com.example.kino_xp.model.Seat;

import java.util.List;

public record SeatRowDTO(int rowNumber, List<Seat> seatList, Hall hall) {
    public int getRowNumber() {
        return rowNumber;
    }
}
