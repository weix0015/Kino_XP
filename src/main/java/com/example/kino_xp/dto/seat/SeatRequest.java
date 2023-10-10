package com.example.kino_xp.dto.seat;


import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {

    private Long seatNumber;
    private Long seatRowNumber;

    public void copyTo(Seat seat){
        seat.setSeatNumber(seat.getSeatNumber());
    }

    public Seat getSeatEntity(SeatRequest seatRequest){
        return Seat.builder()
                .seatNumber(seatRequest.getSeatNumber())
                .build();
    }

    public Seat toSeat(){
        Seat seat = new Seat();
        copyTo(seat);
        return seat;
    }
}
