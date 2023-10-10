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
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "ticket", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TICKET_USER"))
    @JsonBackReference("userReference")
    private User user;
    private LocalDateTime dateOfPurchase;

    @ManyToOne()
    @JoinColumn(name = "viewing_id", foreignKey = @ForeignKey(name = "FK_TICKET_VIEWING"))
    @JsonBackReference("viewingReference")
    private Viewing viewing;
    private Long hall;
    private double price;

    @OneToMany(mappedBy = "ticket")
    @JsonManagedReference("ticketReference")
    private List<Seat> seats;

}
