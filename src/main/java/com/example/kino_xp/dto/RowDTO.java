package com.example.kino_xp.dto;

import com.example.kino_xp.model.Seat;

import java.util.List;

public record RowDTO(int rowNumber, List<Seat> seatList) {
}
