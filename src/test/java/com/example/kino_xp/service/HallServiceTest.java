package com.example.kino_xp.service;

import com.example.kino_xp.converter.HallConverter;
import com.example.kino_xp.dto.HallDTO;
import com.example.kino_xp.model.Hall;
import com.example.kino_xp.repository.HallRepository;
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
class HallServiceTest {

  @Mock
  private HallRepository mockedHallRepository;

  @Autowired
  HallConverter hallConverter;

  HallService hallService;

  Hall hallToSave = new Hall(
    1,
    new ArrayList<>()
  );

  @BeforeEach
  void init() {
    hallService = new HallService();
    Hall hall1 = new Hall();
    hall1.setId(1);
    hall1.setSeatRows(new ArrayList<>());

    Hall hall2 = new Hall();
    hall2.setId(2);
    hall2.setSeatRows(new ArrayList<>());

    List<Hall> halls = new ArrayList<>();
    halls.add(hall1);
    halls.add(hall2);

    // Mockito

    // GetAll
    when(mockedHallRepository.findAll()).thenReturn(halls);

    // GetById
    when(mockedHallRepository.findById(1)).thenReturn(Optional.of(hall1));

    // Save
    when(mockedHallRepository.save(Mockito.any(Hall.class))).thenReturn(hall1);
  }

  @Test
  void getAllHalls() {
    // Act
    List<HallDTO> hallDTOS = hallService.getAllHalls();

    // Assert
    assertEquals(2, hallDTOS.size());
  }

  @Test
  void getHallById() {
    assertEquals(1, mockedHallRepository.findById(1).get().getSeatRows());
  }

  @Test
  void updateHallById() {
    // Act
    HallDTO hallDTO = hallService.updateHallById(1, hallConverter.hallToDTO(hallToSave));

    // Assert
    assertEquals(hallConverter.hallToDTO(hallToSave), hallDTO);
  }
}