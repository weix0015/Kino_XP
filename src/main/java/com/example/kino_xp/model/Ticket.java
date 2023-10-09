package com.example.kino_xp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "ticket", referencedColumnName = "id")
    @JsonBackReference("userReference")
    private User user;
    private LocalDateTime dateOfPurchase;

    @ManyToOne()
    @JsonBackReference("viewingReference")
    private Viewing viewing;
    private int hall;
    private double price;

    @OneToMany(mappedBy = "ticket")
    @JsonManagedReference("ticketReference")
    private List<Seat> seats;

}
