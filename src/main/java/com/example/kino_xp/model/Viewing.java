package com.example.kino_xp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viewing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private LocalDateTime showTime;
    private int hall;
    private LocalDateTime showEndTime;



}


