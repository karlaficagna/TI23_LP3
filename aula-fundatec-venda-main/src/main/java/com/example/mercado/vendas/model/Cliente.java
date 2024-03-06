package com.example.mercado.vendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 12, nullable = false)
    private String identidade;

    @Column(length = 12, nullable = false)
    private String cpf;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "endereco_id", foreignKey = @ForeignKey(name = "fk_cliente_endereco"))
    private Endereco endereco;

}
