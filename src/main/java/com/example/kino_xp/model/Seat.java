package com.example.kino_xp.model;

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
    @JoinColumn(name = "seatRowNumber1")
    private SeatRow row;
    //private Ticket ticket;
}
