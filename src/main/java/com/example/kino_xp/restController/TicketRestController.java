package com.example.kino_xp.restController;

import com.example.kino_xp.dto.ticket.TicketRequest;
import com.example.kino_xp.dto.ticket.TicketResponse;
import com.example.kino_xp.model.Ticket;
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
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> ticketDTOList = ticketService.getAllTickets();
        return new ResponseEntity<>(ticketDTOList, HttpStatus.OK);
    }

    @PostMapping("/ticket")
    public ResponseEntity<String> createTicket(@RequestBody TicketRequest ticketRequest) {
        TicketResponse createdTicket = ticketService.createTicket(ticketRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Ticket created successfully");
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity <TicketResponse> getTicketById(@PathVariable("id") Long id) {
        TicketResponse ticketResponse = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketResponse);
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketResponse> putTicket(@PathVariable Long id, @RequestBody TicketRequest ticketRequest) {
        TicketResponse ticketResponse = ticketService.updateTicket(id, ticketRequest);
        return ResponseEntity.ok(ticketResponse);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ticket created successfully");
    }

}
