package com.example.kino_xp.converter;

import com.example.kino_xp.dto.TicketDTO;
import com.example.kino_xp.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {
    public TicketDTO ticketToDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getDateOfPurchase(),
                ticket.getPrice(),
                ticket.getViewing(),
                ticket.getHall()
        );
    }


    public Ticket toEntity(TicketDTO ticketDTO) {
        return new Ticket(
                ticketDTO.id(),
                ticketDTO.dateOfPurchase(),
                ticketDTO.viewing(),
                ticketDTO.hall(),
                ticketDTO.price()
        );
    }
}
