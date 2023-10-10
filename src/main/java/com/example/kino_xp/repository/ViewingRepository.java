package com.example.kino_xp.repository;

import com.example.kino_xp.model.Viewing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.View;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViewingRepository extends JpaRepository<Viewing, Long> {
    List<Viewing> findAll();

}
