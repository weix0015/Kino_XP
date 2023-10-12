package com.example.kino_xp.converter;

import com.example.kino_xp.dto.HallDTO;
import com.example.kino_xp.model.Hall;
import org.springframework.stereotype.Component;

@Component
public class HallConverter {

  public Hall hallToEntity(HallDTO hallDTO) {
    return new Hall(
      hallDTO.id(),
      hallDTO.seatRows()
    );
  }

  public HallDTO hallToDTO(Hall hall) {
    return new HallDTO(
      hall.getId(),
      hall.getSeatRows()
    );
  }
}
