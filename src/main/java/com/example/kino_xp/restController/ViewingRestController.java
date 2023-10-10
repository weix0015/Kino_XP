package com.example.kino_xp.restController;

import com.example.kino_xp.dto.viewing.ViewingRequest;
import com.example.kino_xp.dto.viewing.ViewingResponse;
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
    public ResponseEntity<List<ViewingResponse>> getAllViewings() {
        List<ViewingResponse> viewingDTOList = viewingService.getAllViewings();
        return new ResponseEntity<>(viewingDTOList, HttpStatus.OK);
    }

    @PostMapping("/viewing")
    public ResponseEntity<String> createViewing(@RequestBody ViewingRequest viewingRequest) {
        ViewingResponse createdViewing = viewingService.createViewing(viewingRequest);
        if (createdViewing != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Viewing created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Viewing creation failed");
        }
    }

    @GetMapping("/viewing/{id}")
    public ResponseEntity <ViewingResponse> getViewingById(@PathVariable("id") Long id) {
        ViewingResponse viewingResponse = viewingService.getViewingById(id);
        return ResponseEntity.ok(viewingResponse);
    }

    @PutMapping("/viewing/{id}")
    public ResponseEntity<ViewingResponse> putViewing(@PathVariable Long id, @RequestBody ViewingRequest
            viewingRequest) {
        ViewingResponse updatedViewingResponse = viewingService.updateViewing(id, viewingRequest);
        return ResponseEntity.ok(updatedViewingResponse);
    }

    @DeleteMapping("/viewing/{id}")
    public ResponseEntity<String> deleteViewing(@PathVariable Long id) {
        viewingService.deleteViewingById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Viewing deleted successfully");
    }
}
