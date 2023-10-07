package com.example.kino_xp.converter;

import com.example.kino_xp.dto.SeatDTO;
import com.example.kino_xp.dto.TicketDTO;
import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketConverter {
    @Autowired
    SeatConverter seatConverter;
    public TicketDTO ticketToDTO(Ticket ticket) {
        List<SeatDTO> seatDTOList = ticket.getSeats().stream()
                .map(seatConverter::toDTO)
                .collect(Collectors.toList());

        return new TicketDTO(
                ticket.getId(),
                ticket.getUser(),
                ticket.getDateOfPurchase(),
                ticket.getPrice(),
                ticket.getViewing(),
                ticket.getHall(),
                seatDTOList
        );
    }


    public Ticket toEntity(TicketDTO ticketDTO) {
        List<Seat> seatList = ticketDTO.seats().stream()
                .map(seatConverter::toEntity)
                .collect(Collectors.toList());

        return new Ticket(
                ticketDTO.id(),
                ticketDTO.user(),
                ticketDTO.dateOfPurchase(),
                ticketDTO.viewing(),
                ticketDTO.hall(),
                ticketDTO.price(),
                seatList
        );
    }



}
