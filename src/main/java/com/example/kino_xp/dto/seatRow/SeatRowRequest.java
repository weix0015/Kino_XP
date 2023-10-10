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

    public void copyTo(SeatRow seatRow){
        seatRow.setSeatRowNumber(seatRowNumber);
    }

    public SeatRow toSeatRow(){
        SeatRow seatRow = new SeatRow();
        copyTo(seatRow);
        return seatRow;
    }
}
