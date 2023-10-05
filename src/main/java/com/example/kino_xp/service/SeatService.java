package com.example.kino_xp.service;

import com.example.kino_xp.converter.SeatConverter;
import com.example.kino_xp.dto.SeatDTO;
import com.example.kino_xp.exception.SeatNotFoundException;
import com.example.kino_xp.model.Seat;
import com.example.kino_xp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService
{
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatConverter seatConverter;
    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream()
                .map(seatConverter::toDTO)
                .collect(Collectors.toList());

    }
    public SeatDTO getSeatById(int id) {
        Optional<Seat> optionalSeat = seatRepository.findById(id);
        if (optionalSeat.isPresent()) {
            return seatConverter.toDTO(optionalSeat.get());
        } else {
            throw new SeatNotFoundException("Seat not found with id: " + id);
        }
    }

    public SeatDTO updateSeat(int id, SeatDTO seatDTO) {
        Optional<Seat> existingSeat = seatRepository.findById(id);
        if (existingSeat.isPresent()) {
            Seat seatToUpdate=seatConverter.toEntity(seatDTO);
            seatToUpdate.setSeatNumber(id);
            Seat savedSeat=seatRepository.save(seatToUpdate);
            return seatConverter.toDTO(savedSeat);
        }else{
            throw new SeatNotFoundException("Seat is not found with id: "+id);
        }
    }

    public boolean isSeatReserved(int seatNumber) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatNumber);
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            return seat.isReserved();
        } else {
            throw new SeatNotFoundException("Seat not found with seat number: " + seatNumber);
        }
    }


}
