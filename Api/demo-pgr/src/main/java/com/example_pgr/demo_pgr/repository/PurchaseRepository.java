package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
