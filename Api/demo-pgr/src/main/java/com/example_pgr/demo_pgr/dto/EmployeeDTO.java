package com.example_pgr.demo_pgr.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int id_user;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String position;
    private int id_cine;
    private String dni;
}
