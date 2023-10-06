package com.example.kino_xp.converter;

import com.example.kino_xp.dto.SeatRowDTO;
import com.example.kino_xp.model.SeatRow;
import org.springframework.stereotype.Component;

@Component
public class SeatRowConverter
{
    public SeatRow toEntity(SeatRowDTO rowDTO)
    {
        return new SeatRow(
                rowDTO.rowNumber(),
                rowDTO.seatList(),
                rowDTO.hall()
        )
                ;}
    public SeatRowDTO toDTO(SeatRow seatRow){
        return new SeatRowDTO(
                seatRow.getSeatRowNumber(),
                seatRow.getSeatList(),
                seatRow.getHall()
        );
    }
}
