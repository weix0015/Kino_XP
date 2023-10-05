package com.example.kino_xp.model;

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
    @ManyToOne
    @JoinColumn(name = "ticket", referencedColumnName = "id")
    @JsonBackReference
    private User user;
    private LocalDateTime dateOfPurchase;

    @ManyToOne(cascade = CascadeType.ALL)
    private Viewing viewing;
    private int hall;
    private double price;


}
