package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Integer> {

}
