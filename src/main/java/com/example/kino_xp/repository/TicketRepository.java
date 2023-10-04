package com.example.kino_xp.repository;

import com.example.kino_xp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
   // Optional<Ticket> findById(int id);
    List<Ticket> findAll();

}
