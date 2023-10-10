package com.example.kino_xp.service;

import com.example.kino_xp.dto.seatRow.SeatRowRequest;
import com.example.kino_xp.dto.seatRow.SeatRowResponse;
import com.example.kino_xp.exception.SeatRowNotFoundExeption;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.repository.SeatRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatRowService {

    private final SeatRowRepository seatRowRepository;


    @Autowired
    public SeatRowService(SeatRowRepository seatRowRepository) {
        this.seatRowRepository = seatRowRepository;
    }

    public List<SeatRowResponse> getAllSeatRows() {
        List<SeatRow> seatRows = seatRowRepository.findAll();
        return seatRows.stream()
                .map(SeatRowResponse::new)
                .collect(Collectors.toList());
    }

    public SeatRowResponse getSeatRowBySeatRowNumber(Long id) {
        Optional<SeatRow> foundSeatRow = seatRowRepository.findById(id);
        if (foundSeatRow.isPresent()) {
            return new SeatRowResponse(foundSeatRow.get());
        } else {
            throw new SeatRowNotFoundExeption("Row not found with id: " + id);
        }
    }

    public SeatRowResponse updateSeatRowBySeatRowNumber(Long seatRowNumber, SeatRowRequest seatRowRequest) {
        Optional<SeatRow> existingSeatRowOptional = seatRowRepository.findById(seatRowRequest.getSeatRowNumber());
        SeatRow existingSeatRow = new SeatRow();
        if (existingSeatRowOptional.isEmpty()) {
            throw new SeatRowNotFoundExeption("Could not find seat with seat number: " + seatRowNumber);
        } else {
            existingSeatRow = existingSeatRowOptional.get();
            seatRowRequest.copyTo(existingSeatRow);
            return new SeatRowResponse(seatRowRepository.save(existingSeatRow));
        }
    }
}

