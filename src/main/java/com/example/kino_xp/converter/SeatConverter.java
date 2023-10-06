package com.example.kino_xp.converter;

import com.example.kino_xp.dto.SeatDTO;
import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatConverter
{
    public Seat toEntity(SeatDTO seatDTO)
    {
        return new Seat(
                seatDTO.seatNumber(),
                seatDTO.row(),
                seatDTO.ticket()
        )
    ;}
    public SeatDTO toDTO(Seat seat){
        return new SeatDTO(
                seat.getSeatNumber(),
                seat.getSeatRow(),
                seat.getTicket()
        );
    }
}
