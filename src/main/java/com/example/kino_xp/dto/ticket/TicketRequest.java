package com.example.kino_xp.dto.ticket;

import com.example.kino_xp.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public void copyTo(Ticket ticket){
        ticket.setDateOfPurchase(dateOfPurchase);
        ticket.setHall(hall);
    }

    public Ticket toTicket(){
        Ticket ticket = new Ticket();
        copyTo(ticket);
        return ticket;
    }
}
