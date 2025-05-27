package com.example_pgr.demo_pgr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class Employee extends User {

    private String position;

    @ManyToOne
    @JoinColumn(name = "ID_CINE", referencedColumnName = "ID_CINE", nullable = false)
    private Cine cine;

    @Column(name = "DNI")
    private String dni;
}
