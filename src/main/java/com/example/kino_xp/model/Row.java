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
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rowNumber;
    @OneToMany(mappedBy = "row")
    @JsonManagedReference
    private List<Seat> seatList = new ArrayList<>();
}
