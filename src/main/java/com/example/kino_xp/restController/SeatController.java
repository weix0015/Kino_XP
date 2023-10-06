package com.example.kino_xp.restController;

import com.example.kino_xp.converter.SeatConverter;
import com.example.kino_xp.dto.SeatDTO;
import com.example.kino_xp.model.Seat;
import com.example.kino_xp.repository.SeatRepository;
import com.example.kino_xp.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController
{
    @Autowired
    SeatService seatService;

    @Autowired
    SeatConverter seatDTOConverter;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/seats")
    public ResponseEntity<List<SeatDTO>> getSeats()
    {
        List<SeatDTO> seatDTOList = seatService.getAllSeats();
        return new ResponseEntity<>(seatDTOList, HttpStatus.OK);
    }

    @GetMapping("/seat/{seatNumber}")
    public ResponseEntity<SeatDTO> getSeat(@PathVariable("seatNumber") int seatNumber)
    {
        SeatDTO seatDTO = seatService.getSeatById(seatNumber);
        return new ResponseEntity<>(seatDTO, HttpStatus.OK);
    }

    @PutMapping("/seat/{seatNumber}")
    public ResponseEntity<SeatDTO> putUser(@PathVariable("seatNumber") int seatNumber, @RequestBody SeatDTO seatDTO) {
        // Check the current reservation status
        boolean isReserved = seatService.isSeatReserved(seatNumber);

        // Create a new SeatDTO with the updated reserved status
        SeatDTO updatedSeatDTO = new SeatDTO(
                seatDTO.seatNumber(),
                seatDTO.row(),
                seatDTO.ticket()
        );

        // Save the updated seatDTO
        updatedSeatDTO = seatService.updateSeat(seatNumber, updatedSeatDTO);

        // Return the updated seatDTO in the response
        return new ResponseEntity<>(updatedSeatDTO, HttpStatus.OK);
    }


}
