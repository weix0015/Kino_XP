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
    @JoinColumn(name = "seatRowNumber1")
    @JsonBackReference
    private SeatRow row;

    @ManyToOne
    @JoinColumn(name = "ticket")
    @JsonBackReference
    private Ticket ticket;

}
