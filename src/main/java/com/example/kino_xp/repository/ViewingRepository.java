package com.example.kino_xp.repository;

import com.example.kino_xp.model.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ViewingRepository extends JpaRepository<Viewing, Integer> {
    Viewing findById(int id);
    List<Viewing> findAll();
}
