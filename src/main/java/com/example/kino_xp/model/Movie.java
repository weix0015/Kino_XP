package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private int id;
  private String title;
  private Genre genre;
  private LocalTime showLength;
  private int age;
  @OneToOne
  @JoinColumn(name = "viewing_id")
  @JsonBackReference("viewingReference")
  private Viewing viewing;

}
