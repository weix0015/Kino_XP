package com.example.kino_xp.service;

import com.example.kino_xp.converter.TicketConverter;
import com.example.kino_xp.dto.TicketDTO;
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

    private final TicketConverter ticketConverter;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketConverter ticketConverter) {
        this.ticketRepository = ticketRepository;
        this.ticketConverter = ticketConverter;
    }


    public List<TicketDTO> getAllTickets() {
        List<TicketDTO> tickets = ticketRepository.findAll().stream().
                map(ticketConverter::ticketToDTO).
                collect(Collectors.toList());
        return tickets;
    }

    public TicketDTO getTicketById(int id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return ticketConverter.ticketToDTO(optionalTicket.get());
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public TicketDTO createTicket(TicketDTO ticketDTO){
        Ticket ticketToSave = ticketConverter.toEntity(ticketDTO);
        ticketToSave.setId(0);
        Ticket savedTicket = ticketRepository.save(ticketToSave);
        return ticketConverter.ticketToDTO(savedTicket);
    }


    public void deleteTicketById(int id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isPresent()){
            ticketRepository.deleteById(id);
        } else{
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public TicketDTO updateTicket(int id, TicketDTO ticketDTO){
        Optional<Ticket> existingTicket = ticketRepository.findById(id);
        if(existingTicket.isPresent()){
            Ticket ticketToUpdate = ticketConverter.toEntity(ticketDTO);
            ticketToUpdate.setId(id);
            Ticket savedTicket = ticketRepository.save(ticketToUpdate);
            return ticketConverter.ticketToDTO(savedTicket);
        } else{
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }



}
