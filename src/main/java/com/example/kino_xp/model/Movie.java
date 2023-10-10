package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Movie {
  @Id
  private String title;
  private Genre genre;
  private LocalTime showLength;
  private int age;
  @OneToMany
  @JoinColumn(name = "viewing_id")
  @JsonBackReference("viewingReference")
  private List<Viewing> viewing;

}
