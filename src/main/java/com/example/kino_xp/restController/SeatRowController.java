package com.example.kino_xp.restController;

import com.example.kino_xp.dto.SeatRowDTO;
import com.example.kino_xp.model.Seat;
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
    public ResponseEntity<List<SeatRowDTO>> getSeatRows(){
        List<SeatRowDTO> seatRowDTOS = seatRowService.getAllSeatRows();
        return new ResponseEntity<>(seatRowDTOS, HttpStatus.OK);
    }

    @GetMapping("/seat-row/{seat-row-number}")
    public ResponseEntity<SeatRowDTO> getSeatRowBySeatRowNumber(@PathVariable("seat-row-number") int seatRowNumber){
        SeatRowDTO foundSeatRow = seatRowService.getSeatRowBySeatRowNumber(seatRowNumber);
        return new ResponseEntity<>(foundSeatRow, HttpStatus.OK);
    }

    @PutMapping("/seat-row/{seat-row-number}")
    public ResponseEntity<SeatRowDTO> updateSeatRow(@PathVariable("seat-row-number") int seatRowNumber, @RequestBody SeatRowDTO seatRowDTO){
        return new ResponseEntity<>(seatRowService.updateSeatRowBySeatRowNumber(seatRowNumber, seatRowDTO), HttpStatus.OK);
    }
}
