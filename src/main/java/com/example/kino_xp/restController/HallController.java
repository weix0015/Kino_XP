package com.example.kino_xp.restController;

import com.example.kino_xp.dto.HallDTO;
import com.example.kino_xp.model.Hall;
import com.example.kino_xp.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {

  @Autowired
  HallService hallService;

  @GetMapping("/halls")
  public ResponseEntity<List<HallDTO>> getHalls() {
    List<HallDTO> hallDTOS = hallService.getAllHalls();
    return new ResponseEntity<>(hallDTOS, HttpStatus.OK);
  }

  @GetMapping("/hall/{id}")
  public ResponseEntity<HallDTO> getHallById(@PathVariable("id") int id) {
    HallDTO foundHall = hallService.getHallById(id);
    return new ResponseEntity<>(foundHall, HttpStatus.OK);
  }

  @PutMapping("/hall/{id}")
  public ResponseEntity<HallDTO> updateHall(@PathVariable("id") int id, @RequestBody HallDTO hallDTO) {
    return new ResponseEntity<>(hallService.updateHallById(id, hallDTO), HttpStatus.OK);
  }
}
