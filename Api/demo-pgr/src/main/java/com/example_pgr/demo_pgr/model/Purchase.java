package com.example_pgr.demo_pgr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PURCHASES")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BUY")
    private Integer id_buy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "TOTAL_PRICE")
    private Double total_price;
    @ManyToOne
    @JoinColumn(name = "ID_USER",referencedColumnName = "ID_USER", nullable = false)
    private User user;
}
