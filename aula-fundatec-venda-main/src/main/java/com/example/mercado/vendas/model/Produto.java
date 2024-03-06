package com.example.mercado.vendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String gtin;
    private String descricao;
    private LocalDateTime dataFabricacao;

    @Column(nullable = false)
    private BigDecimal valor;

}