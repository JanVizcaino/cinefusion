package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
