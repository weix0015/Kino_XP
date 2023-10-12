package com.example.kino_xp.restController;

import com.example.kino_xp.dto.seat.SeatRequest;
import com.example.kino_xp.dto.seat.SeatResponse;
import com.example.kino_xp.repository.SeatRepository;
import com.example.kino_xp.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class SeatRestController
{
    @Autowired
    SeatService seatService;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/seats")
    public ResponseEntity<List<SeatResponse>> getSeats()
    {
        List<SeatResponse> seatResponseList = seatService.getAllSeats();
        return new ResponseEntity<>(seatResponseList, HttpStatus.OK);
    }

    @GetMapping("/seat/{seatNumber}")
    public ResponseEntity<SeatResponse> getSeat(@PathVariable("seatNumber") Long seatNumber)
    {
        SeatResponse seatResponse = seatService.getSeatById(seatNumber);
        return new ResponseEntity<>(seatResponse, HttpStatus.OK);
    }

    @PutMapping("/seat/{seatNumber}")
    public ResponseEntity<SeatResponse> putUser(@PathVariable("seatNumber") Long seatNumber, @RequestBody SeatRequest seatRequest) {
        // Check the current reservation status
        boolean isReserved = seatService.isSeatReserved(seatNumber);

        // Create a new SeatDTO with the updated reserved status
        SeatRequest updatedSeatRequest = new SeatRequest(
                seatRequest.getSeatNumber(),
                seatRequest.getSeatRowNumber()
        );

        // Save the updated seatDTO
        SeatResponse updatedSeatResponse = seatService.updateSeat(seatNumber, updatedSeatRequest);

        // Return the updated seatDTO in the response
        return new ResponseEntity<>(updatedSeatResponse, HttpStatus.OK);
    }


}
