package com.example.kino_xp.service;

import com.example.kino_xp.converter.SeatRowConverter;
import com.example.kino_xp.dto.SeatRowDTO;
import com.example.kino_xp.exception.SeatRowNotFoundException;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.repository.SeatRowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SeatRowServiceTest {

    @Mock
    private SeatRowRepository mockedSeatRowRepository;

    @Autowired
    SeatRowConverter seatRowConverter;

    SeatRowService seatRowService;

    SeatRow seatRowToSave = new SeatRow(
            1,
            new ArrayList<>()
    );

    @BeforeEach
    void init() {
        seatRowService = new SeatRowService(mockedSeatRowRepository, seatRowConverter);
        SeatRow seatRow1= new SeatRow();
        seatRow1.setSeatList(new ArrayList<>());
        seatRow1.setSeatRowNumber(1);

        SeatRow seatRow2= new SeatRow();
        seatRow2.setSeatList(new ArrayList<>());
        seatRow2.setSeatRowNumber(2);

        List<SeatRow> seatRowList = new ArrayList<>();
        seatRowList.add(seatRow1);
        seatRowList.add(seatRow2);

        // Mockito

        //GetAll
        when(mockedSeatRowRepository.findAll()).thenReturn(seatRowList);

        //GetById
        when(mockedSeatRowRepository.findById(1)).thenReturn(Optional.of(seatRow1));

        //Save
        Mockito.when(mockedSeatRowRepository.save(Mockito.any(SeatRow.class))).thenReturn(seatRow1);
    }


    @Test
    void getAllSeatRows() {
        List<SeatRowDTO> seatRowDTOS = seatRowService.getAllSeatRows();

        assertEquals(2, seatRowDTOS.size());
    }

    @Test
    void getSeatRowBySeatRowNumber_ExistingRow() {
        assertEquals(1, mockedSeatRowRepository.findById(1).get().getSeatRowNumber());
    }


    @Test
    void getSeatRowBySeatRowNumber_RowNotFound() {
        when(mockedSeatRowRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(SeatRowNotFoundException.class, () -> seatRowService.getSeatRowBySeatRowNumber(3));

    }

    @Test
    void updateSeatRowBySeatRowNumber_ExistingRow() {
        SeatRowDTO seatRowDTO = seatRowService.updateSeatRowBySeatRowNumber(1, seatRowConverter.toDTO(seatRowToSave));
        assertEquals(seatRowConverter.toDTO(seatRowToSave), seatRowDTO);
    }

    @Test
    void updateSeatRowBySeatRowNumber_RowNotFound() {
        SeatRowDTO updatedSeatRowDTO = new SeatRowDTO(3, new ArrayList<>());
        when(mockedSeatRowRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(SeatRowNotFoundException.class, () -> seatRowService.updateSeatRowBySeatRowNumber(3, updatedSeatRowDTO));

    }
}
