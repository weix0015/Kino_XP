package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Hall {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<SeatRow> seatRows = new ArrayList<>();
}
