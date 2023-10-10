package com.example.kino_xp.service;

import com.example.kino_xp.dto.ticket.TicketRequest;
import com.example.kino_xp.dto.ticket.TicketResponse;
import com.example.kino_xp.exception.TicketNotFoundException;
import com.example.kino_xp.model.Ticket;
import com.example.kino_xp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(TicketResponse::new)
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return new TicketResponse(optionalTicket.get());
        } else {
            throw new TicketNotFoundException("Ticket with id: "
                    + id
                    + "could not be found");
        }
    }

    public TicketResponse createTicket(TicketRequest ticketRequest) {
        Ticket newTicket = ticketRequest.toTicket();
        ticketRepository.save(newTicket);
        return new TicketResponse(newTicket);
    }


    public void deleteTicketById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketRepository.deleteById(id);
        } else {
            throw new TicketNotFoundException("Ticket with id: "
                    + id
                    + " could not be found");
        }
    }

    public TicketResponse updateTicket(Long id, TicketRequest ticketRequest) {
        Optional<Ticket> ticketToEditOptional = ticketRepository.findById(id);
        Ticket ticketToEdit = ticketToEditOptional.get();
        if (ticketToEditOptional.isEmpty()) {
            throw new TicketNotFoundException("Ticket with id: "
                    + id
                    + " could not be found");
        } else {
            ticketRequest.copyTo(ticketToEdit);
            return new TicketResponse(ticketRepository.save(ticketToEdit));
        }
    }
}
