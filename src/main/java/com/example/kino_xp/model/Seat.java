package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatNumber;

    @ManyToOne
    @JoinColumn(name = "seatRowNumber", foreignKey = @ForeignKey(name = "FK_SEAT_SEAT_ROW_NUMBER"))
    @JsonBackReference("seatRowReference")
    private SeatRow seatRow;

    @ManyToOne
    @JoinColumn(name = "ticket", foreignKey = @ForeignKey(name = "FK_SEAT_TICKET"))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonBackReference("ticketReference")
    private Ticket ticket;

}
