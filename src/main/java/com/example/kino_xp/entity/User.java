package com.example.kino_xp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String email;
  private String password;
  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Ticket> tickets = new ArrayList<>();
  private boolean admin;

}
