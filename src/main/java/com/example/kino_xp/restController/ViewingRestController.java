package com.example.kino_xp.restController;

import com.example.kino_xp.dto.ViewingDTO;
import com.example.kino_xp.service.ViewingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ViewingRestController {
    @Autowired
    ViewingService viewingService;

    @GetMapping("/viewings")
    public ResponseEntity<List<ViewingDTO>> getAllViewings() {
        List<ViewingDTO> viewingDTOList = viewingService.getAllViewings();
        return new ResponseEntity<>(viewingDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ViewingDTO> createViewing(@RequestBody ViewingDTO viewingDTO) {
        ViewingDTO createdViewing = viewingService.createViewing(viewingDTO);
        return new ResponseEntity<>(createdViewing, HttpStatus.CREATED);
    }

    @GetMapping("/viewings/{id}")
    public ResponseEntity <ViewingDTO> getViewingById(@PathVariable("id") int id) {
        ViewingDTO viewingDTO = viewingService.getViewingById(id);
        return ResponseEntity.ok(viewingDTO);
    }

    @PutMapping("/viewings/{id}")
    public ResponseEntity<ViewingDTO> putViewing(@PathVariable int id, @RequestBody ViewingDTO
            viewingDTO) {
        ViewingDTO updateViewingDTO = viewingService.updateViewing(id, viewingDTO);
        return ResponseEntity.ok(updateViewingDTO);
    }

    @DeleteMapping("/viewings/{id}")
    public ResponseEntity<Void> deleteViewing(@PathVariable int id) {
        viewingService.deleteViewingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
