package com.example.kino_xp.service;

import com.example.kino_xp.dto.seat.SeatRequest;
import com.example.kino_xp.dto.seat.SeatResponse;
import com.example.kino_xp.exception.SeatNotFoundException;
import com.example.kino_xp.model.Seat;
import com.example.kino_xp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatResponse> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream()
                .map(SeatResponse::new)
                .collect(Collectors.toList());
    }

    public SeatResponse getSeatById(Long id) {
        Optional<Seat> foundSeatOptional = seatRepository.findById(id);
        Seat foundSeat = foundSeatOptional.get();
        if (foundSeatOptional.isEmpty()) {
            throw new SeatNotFoundException("The seat with the given id: "
                    + id
                    + " could not be found");
        } else {
            return new SeatResponse(foundSeat);
        }
    }

    public SeatResponse updateSeat(Long id, SeatRequest seatRequest) {
        Optional<Seat> existingSeatOptional = seatRepository.findById(id);
        Seat existingSeat = existingSeatOptional.get();
        if (existingSeatOptional.isEmpty()) {
            throw new SeatNotFoundException("The seat with the given id: "
                    + id
                    + " could not be found");
        } else {
            seatRequest.copyTo(existingSeat);
            return new SeatResponse(seatRepository.save(existingSeat));
        }
    }

    public boolean isSeatReserved(Long seatNumber) {
        Optional<Seat> optionalSeat = seatRepository.findById(seatNumber);
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            return seat.getTicket() != null;
        }
        return false;
    }
}
