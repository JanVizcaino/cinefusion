package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.Cine;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CineRepository  extends JpaRepository<Cine, Integer> {
}
