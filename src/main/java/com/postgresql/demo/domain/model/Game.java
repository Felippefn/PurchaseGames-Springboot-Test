package com.postgresql.demo.domain.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data                   // gera getters/setters, toString, equals, hashCode
@NoArgsConstructor      // gera construtor vazio (obrigatório pro JPA)
@AllArgsConstructor     // gera construtor com todos os campos
@Builder
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // geralmente melhor p/ Postgres
    private Long id;

    @NotBlank private String name;
    @Size(max = 1000) private String description;
    @Size(max = 500) private String image;
    @NotBlank private String category;
    @PositiveOrZero private BigDecimal price;
}
