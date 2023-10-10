package com.example.kino_xp.repository;

import com.example.kino_xp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  List<Movie> findAllByTitle(String title);
  boolean existsByTitle(String title);
}
