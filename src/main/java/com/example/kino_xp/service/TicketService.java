package com.example.kino_xp.service;

import com.example.kino_xp.dto.ticket.TicketRequest;
import com.example.kino_xp.dto.ticket.TicketResponse;
import com.example.kino_xp.exception.TicketNotFoundException;
import com.example.kino_xp.exception.UserNotFoundException;
import com.example.kino_xp.exception.ViewingNotFoundException;
import com.example.kino_xp.model.Ticket;
import com.example.kino_xp.model.User;
import com.example.kino_xp.model.Viewing;
import com.example.kino_xp.repository.TicketRepository;
import com.example.kino_xp.repository.UserRepository;
import com.example.kino_xp.repository.ViewingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ViewingRepository viewingRepository;

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
        Ticket newTicket = ticketRequest.getTicketEntity(ticketRequest);
        newTicket.setUser(findUser(ticketRequest));
        newTicket.setViewing(findViewing(ticketRequest));
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

    public User findUser(TicketRequest ticketRequest){
        return userRepository.findById(ticketRequest.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this ID is unknown"));

    }

    public Viewing findViewing(TicketRequest ticketRequest){
        return viewingRepository.findById(ticketRequest.getViewing_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Viewing with this ID is unknown"));
    }
}
