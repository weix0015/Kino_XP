package com.example.kino_xp.dto.seatRow;

import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatRowResponse {
    private Long seatRowNumber;
    private List<Long> seatNumbers;

    public SeatRowResponse(SeatRow seatRow) {
        this.seatRowNumber = seatRow.getSeatRowNumber();
        this.seatNumbers = seatRow.getSeatList().stream()
                .map(Seat::getSeatNumber)
                .collect(Collectors.toList());
    }
}
