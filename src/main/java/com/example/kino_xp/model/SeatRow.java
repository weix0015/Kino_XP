package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "seat_row")
public class SeatRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatRowNumber;
    @OneToMany(mappedBy = "seatRow", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Seat> seatList = new ArrayList<>();
}
