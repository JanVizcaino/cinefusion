package com.example_pgr.demo_pgr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO {
    private Integer id_buy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Double total_price;
    private String client_name;
    private int id_user;
}
