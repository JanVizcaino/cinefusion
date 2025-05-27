package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
