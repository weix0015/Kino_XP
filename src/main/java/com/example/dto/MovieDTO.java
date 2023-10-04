package com.example.dto;

import com.example.kino_xp.model.Genre;

import java.time.LocalTime;

public record MovieDTO(int id, String title, Genre genre, LocalTime showLength,int age) {
}
