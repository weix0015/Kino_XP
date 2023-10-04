package com.example.kino_xp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
private LocalTime showLength;
private int age;


}
