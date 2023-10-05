package com.example.kino_xp.restController;

import com.example.kino_xp.dto.TicketDTO;
import com.example.kino_xp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TicketRestController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> ticketDTOList = ticketService.getAllTickets();
        return new ResponseEntity<>(ticketDTOList, HttpStatus.OK);
    }

    @PostMapping("/ticket")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity <TicketDTO> getTicketById(@PathVariable("id") int id) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketDTO);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> putTicket(@PathVariable int id, @RequestBody TicketDTO ticketDTO) {
        TicketDTO updateTicketDTO = ticketService.updateTicket(id, ticketDTO);
        return ResponseEntity.ok(updateTicketDTO);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int id) {
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
