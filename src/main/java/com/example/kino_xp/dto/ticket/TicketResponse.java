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
public class TicketResponse {
    private Long id;
    private Long user_id;
    private LocalDateTime dateOfPurchase;
    private Long viewing_id;
    private Long hall;
    private double price;

    public TicketResponse(Ticket ticket){
        this.id = ticket.getId();
        this.user_id = ticket.getUser().getId();
        this.dateOfPurchase = ticket.getDateOfPurchase();
        this.hall = ticket.getHall();
        this.price = ticket.getPrice();
    }
}

