package com.example.kino_xp.dto;

import com.example.kino_xp.entity.Ticket;

import java.util.List;

public record UserDTO(int id, String name, String email, String password, List<Ticket> tickets, boolean admin)
{
}
