package com.example.kino_xp.dto.seat;

import com.example.kino_xp.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {
    private Long seatNumber;
    private Long seatRowNumber;

    public SeatResponse(Seat seat){
        seatNumber = seat.getSeatNumber();
        seatRowNumber = seat.getSeatRow() != null ? seat.getSeatRow().getSeatRowNumber() : 0;
    }
}
