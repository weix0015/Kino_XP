package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "viewing_id", foreignKey = @ForeignKey(name = "FK_VIEWING_TICKET"))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonManagedReference("viewingReference")
    private List<Ticket> tickets;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "title", foreignKey = @ForeignKey(name = "FK_VIEWING_MOVIE"))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonManagedReference("viewingReference")
    private Movie movie;


}


