package com.example.kino_xp.service;

import com.example.kino_xp.converter.HallConverter;
import com.example.kino_xp.dto.HallDTO;
import com.example.kino_xp.exception.HallNotFoundException;
import com.example.kino_xp.model.Hall;
import com.example.kino_xp.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HallService {

  @Autowired
  HallRepository hallRepository;

  @Autowired
  HallConverter hallConverter;

  public List<HallDTO> getAllHalls() {
    List<Hall> halls = hallRepository.findAll();
    return halls.stream()
      .map(hallConverter::hallToDTO)
      .collect(Collectors.toList());
  }

  public HallDTO getHallById(int id) {
    Optional<Hall> optionalHall = hallRepository.findById(id);
    if (optionalHall.isPresent()) {
      return hallConverter.hallToDTO(optionalHall.get());
    } else {
      throw new HallNotFoundException("Hall not found with id: " + id);
    }
  }

  public HallDTO updateHallById(int id, HallDTO hallDTO) {
    Optional<Hall> existingHall = hallRepository.findById(id);
    if (existingHall.isEmpty()) {
      throw new HallNotFoundException("Could not find hall with id: " + id);
    } else {
      Hall hallToUpdate = hallConverter.hallToEntity(hallDTO);
      hallToUpdate.setId(id);
      Hall savedHall = hallRepository.save(hallToUpdate);
      return hallConverter.hallToDTO(savedHall);
    }
  }
}
