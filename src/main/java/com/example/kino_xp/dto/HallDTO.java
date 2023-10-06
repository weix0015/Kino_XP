package com.example.kino_xp.dto;

import com.example.kino_xp.model.SeatRow;

import java.util.List;

public record HallDTO(int id, List<SeatRow> seatRows) {
}
