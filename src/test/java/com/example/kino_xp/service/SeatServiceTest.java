package com.example.kino_xp.service;

import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.repository.SeatRepository;
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
class SeatServiceTest {
/*
    @Mock
    private SeatRepository mockedSeatRepository;

    @Autowired
    SeatConverter seatConverter;

    SeatService seatService;

    @Mock
    SeatRowRepository mockedSeatRowRepository;

    SeatRow rowToSave = new SeatRow(
            1,
            new ArrayList<>()
    );
    Seat seatToSave = new Seat(
            1,
            rowToSave,
            null
    );

    @BeforeEach
    void init() {
        seatService = new SeatService(mockedSeatRepository, seatConverter);
        SeatRow seatRow1= new SeatRow();
        seatRow1.setSeatList(new ArrayList<>());
        seatRow1.setSeatRowNumber(1);
        mockedSeatRowRepository.save(seatRow1);

        Seat seat1 = new Seat();
        seat1.setSeatNumber(1);
        seat1.setSeatRow(seatRow1);
        List<Seat> seatList = new ArrayList<>();
        seatList.add(seat1);

        //Mockito

        //getAll
        when(mockedSeatRepository.findAll()).thenReturn(seatList);

        //getById
        when(mockedSeatRepository.findById(1)).thenReturn(Optional.of(seat1));

        //Save
        when(mockedSeatRepository.save(Mockito.any(Seat.class))).thenReturn(seat1);
    }

    @Test
    void getAllSeats() {
        List<SeatDTO> seatDTOS = seatService.getAllSeats();
        assertEquals(1, seatDTOS.size());

    }

    @Test
    void getSeatById() {
        {
            when(mockedSeatRepository.findById(1)).thenReturn(Optional.of(seatToSave));
            SeatDTO seatDTO = seatService.getSeatById(1);

            assertNotNull(seatDTO);
            assertEquals(1, seatToSave.getSeatNumber());
            assertEquals(rowToSave, seatToSave.getSeatRow());
        }

    }

/*    @Test
    void updateSeat() {
        SeatDTO seatDTO= seatService.updateSeat(1, seatConverter.toDTO(seatToSave));
        seatToSave.setReserved(true);
        assertFalse(seatDTO.reserved());
    }

    @Test
    void isSeatReserved() {
        assertFalse(seatService.isSeatReserved(1));
    }
*/

}