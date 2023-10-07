package com.example.kino_xp.dto;

import com.example.kino_xp.model.Seat;
import com.example.kino_xp.model.SeatRow;
import com.example.kino_xp.model.User;
import com.example.kino_xp.model.Viewing;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.time.LocalDateTime;
import java.util.List;


public record TicketDTO(int id,
                        User user,
                        LocalDateTime dateOfPurchase,
                        double price, Viewing viewing,
                        int hall,
                        List<SeatDTO> seats) {
}
