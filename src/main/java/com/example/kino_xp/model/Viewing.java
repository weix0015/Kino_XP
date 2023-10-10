package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime showTime;
    private Long hall;
    private LocalDateTime showEndTime;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ticket-id")
    @JsonManagedReference("viewingReference")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "title")
    @JsonManagedReference("viewingReference")
    private Movie movie;


}


