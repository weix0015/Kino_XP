package com.example.kino_xp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
  @Getter
  @Setter

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;


  @ManyToOne
  @JoinColumn(name = "ticket", referencedColumnName = "id")
  @JsonBackReference
  private User user;

  private String whatever;
}
