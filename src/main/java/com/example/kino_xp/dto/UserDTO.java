package com.example.kino_xp.dto;

import com.example.kino_xp.model.Ticket;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
public record UserDTO(int id,
                      String name,
                      String email,
                      @JsonIgnore String password,
                      List<Ticket> tickets,
                      boolean admin) {
}
