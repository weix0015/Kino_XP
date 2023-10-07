package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "seatRowNumber")
    @JsonBackReference("seatRowReference")
    private SeatRow seatRow;

    @ManyToOne
    @JoinColumn(name = "ticket")
    @JsonBackReference("ticketReference")
    private Ticket ticket;

}
