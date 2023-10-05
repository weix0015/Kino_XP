package com.example.kino_xp.service;

import com.example.kino_xp.converter.SeatRowConverter;
import com.example.kino_xp.dto.SeatRowDTO;
import com.example.kino_xp.exception.SeatRowNotFoundExeption;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.repository.SeatRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatRowService
{
    @Autowired
    SeatRowRepository seatRowRepository;

    @Autowired
    SeatRowConverter seatRowConverter;

    public List<SeatRowDTO> getAllSeatRows(){
        List<SeatRow> seatRows = seatRowRepository.findAll();
        return seatRows.stream()
                .map(seatRowConverter::toDTO)
                .collect(Collectors.toList());
    }
    public SeatRowDTO getSeatRowById(int id) {
        Optional<SeatRow> optionalSeatRow = seatRowRepository.findById(id);
        if (optionalSeatRow.isPresent()) {
            return seatRowConverter.toDTO(optionalSeatRow.get());
        } else {
            throw new SeatRowNotFoundExeption("Row not found with id: " + id);
        }
    }
}