package com.example.mercado.vendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "venda_produto")
public class VendaProduto {

    @EmbeddedId
    private VendaProdutoId id;

    @ManyToOne
    @MapsId(value = "venda_id")
    @JoinColumn(name = "venda_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_venda_produto_venda"))
    private Venda venda;

    @ManyToOne
    @MapsId(value = "produto_id")
    @JoinColumn(name = "produto_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_venda_produto_produto"))
    private Produto Produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;
}