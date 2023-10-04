package com.example.kino_xp.Repository;

import com.example.kino_xp.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findById(int id);
    List<Ticket> findAll();

}
