package com.example.kino_xp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viewing {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private LocalDateTime showTime;
    @Column
    private int hall;
    @Column
    private LocalDateTime showEndTime;

    @OneToOne
    @JoinColumn(name = "id")
    private Ticket ticket;



}


