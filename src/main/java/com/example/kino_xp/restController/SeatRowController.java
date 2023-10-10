package com.example.kino_xp.restController;

import com.example.kino_xp.dto.seatRow.SeatRowRequest;
import com.example.kino_xp.dto.seatRow.SeatRowResponse;
import com.example.kino_xp.service.SeatRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatRowController {
    @Autowired
    SeatRowService seatRowService;

    @GetMapping("/seat-rows")
    public ResponseEntity<List<SeatRowResponse>> getSeatRows(){
        List<SeatRowResponse> seatRowResponses = seatRowService.getAllSeatRows();
        return new ResponseEntity<>(seatRowResponses, HttpStatus.OK);
    }

    @GetMapping("/seat-row/{seat-row-number}")
    public ResponseEntity<SeatRowResponse> getSeatRowBySeatRowNumber(@PathVariable("seat-row-number") Long seatRowNumber){
        SeatRowResponse foundSeatRow = seatRowService.getSeatRowBySeatRowNumber(seatRowNumber);
        return new ResponseEntity<>(foundSeatRow, HttpStatus.OK);
    }

    @PutMapping("/seat-row/{seat-row-number}")
    public ResponseEntity<SeatRowResponse> updateSeatRow(@PathVariable("seat-row-number") Long seatRowNumber, @RequestBody SeatRowRequest seatRowRequest){
        return new ResponseEntity<>(seatRowService.updateSeatRowBySeatRowNumber(seatRowNumber, seatRowRequest), HttpStatus.OK);
    }
}
