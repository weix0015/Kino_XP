package com.example.kino_xp.service;

import com.example.kino_xp.converter.SeatRowConverter;
import com.example.kino_xp.dto.SeatRowDTO;
import com.example.kino_xp.exception.SeatRowNotFoundException;
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

    private final SeatRowConverter seatRowConverter;

    @Autowired
    public SeatRowService(SeatRowRepository seatRowRepository, SeatRowConverter seatRowConverter) {
        this.seatRowRepository = seatRowRepository;
        this.seatRowConverter = seatRowConverter;
    }

    public List<SeatRowDTO> getAllSeatRows() {
        List<SeatRow> seatRows = seatRowRepository.findAll();
        return seatRows.stream()
                .map(seatRowConverter::toDTO)
                .collect(Collectors.toList());
    }

    public SeatRowDTO getSeatRowBySeatRowNumber(int id) {
        Optional<SeatRow> optionalSeatRow = seatRowRepository.findById(id);
        if (optionalSeatRow.isPresent()) {
            return seatRowConverter.toDTO(optionalSeatRow.get());
        } else {
            throw new SeatRowNotFoundException("Row not found with id: " + id);
        }
    }

    public SeatRowDTO updateSeatRowBySeatRowNumber(int seatRowNumber, SeatRowDTO seatRowDTO) {
        Optional<SeatRow> existingSeatRow = seatRowRepository.findById(seatRowNumber);
        if (existingSeatRow.isEmpty()) {
            throw new SeatRowNotFoundException("Could not find seat with seat number: " + seatRowNumber);
        } else {
            SeatRow seatRowToUpdate = seatRowConverter.toEntity(seatRowDTO);
            seatRowToUpdate.setSeatRowNumber(seatRowNumber);
            SeatRow savedSeatRow = seatRowRepository.save(seatRowToUpdate);
            return seatRowConverter.toDTO(savedSeatRow);
        }
    }
}

