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

    public void copy(Ticket ticket){
        this.user_id = ticket.getUser().getId();
        this.dateOfPurchase = ticket.getDateOfPurchase();
        this.viewing_id = ticket.getViewing().getId();
        this.hall = ticket.getHall();
        this.price = ticket.getPrice();
    }

    public Ticket toTicket(){
        Ticket ticket = new Ticket();
        copy(ticket);
        return ticket;
    }
}
