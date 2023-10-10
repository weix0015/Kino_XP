package com.example.kino_xp.dto.ticket;

import com.example.kino_xp.model.Ticket;
import com.example.kino_xp.model.User;
import com.example.kino_xp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {

    private Long user_id;
    private LocalDateTime dateOfPurchase;
    private Long viewing_id;
    private Long hall;

    private double price;

    public void copyTo(Ticket ticket) {
        ticket.setDateOfPurchase(dateOfPurchase);
        ticket.setHall(hall);
    }

    public Ticket getTicketEntity(TicketRequest t) {
        return Ticket.builder()
                .dateOfPurchase(t.dateOfPurchase)
                .hall(hall)
                .price(price)
                .build();
    }

    public Ticket toTicket() {
        Ticket ticket = new Ticket();
        copyTo(ticket);
        return ticket;
    }
}
