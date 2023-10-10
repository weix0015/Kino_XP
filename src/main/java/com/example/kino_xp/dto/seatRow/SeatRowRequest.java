package com.example.kino_xp.dto.seatRow;

import com.example.kino_xp.model.SeatRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatRowRequest {
    private Long seatRowNumber;
    private List<Long> seatNumberList;

    public void copy(SeatRow seatRow){
        this.seatRowNumber = seatRow.getSeatRowNumber();
    }

    public SeatRow toSeatRow(){
        SeatRow seatRow = new SeatRow();
        copy(seatRow);
        return seatRow;
    }
}
