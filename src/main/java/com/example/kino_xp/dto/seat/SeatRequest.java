package com.example.kino_xp.dto.seat;


import com.example.kino_xp.model.Seat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequest {
    private Long seatNumber;
    private Long seatRowNumber;

    public void copy(Seat seat){
        seat.setSeatNumber(seat.getSeatNumber());
    }

    public Seat toSeat(){
        Seat seat = new Seat();
        copy(seat);
        return seat;
    }
}
