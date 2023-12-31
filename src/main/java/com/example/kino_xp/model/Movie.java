package com.example.kino_xp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

  private String posterUrl;
  private Genre genre;
  private LocalTime showLength;
  private int age;
  @OneToMany(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "viewing_id", foreignKey = @ForeignKey(name = "FK_MOVIE_VIEWING"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference("viewingReference")
  private List<Viewing> viewing;

}
