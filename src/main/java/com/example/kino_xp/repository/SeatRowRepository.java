package com.example.kino_xp.repository;

import com.example.kino_xp.model.SeatRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRowRepository extends JpaRepository<SeatRow, Integer> {
}
