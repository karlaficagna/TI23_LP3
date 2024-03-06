package com.example.mercado.vendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVenda estado;

    public void checarOperacaoEmVenda() {
        if(estado == EstadoVenda.FINALIZADO) {
            throw new RuntimeException("Não pode operar em vendas finalizadas");
        }
    }

}