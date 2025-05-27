package com.example_pgr.demo_pgr.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id_user;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
}
