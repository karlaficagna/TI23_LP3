package com.example.mercado.vendas.controller.response;

import com.example.mercado.vendas.model.Produto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProdutoResponse {
    private Long id;
    private String nome;
    private String gtin;
    private String descricao;
    private LocalDateTime dataFabricacao;
    private BigDecimal valor;

    public static ProdutoResponse fromModel(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setGtin(produto.getGtin());
        response.setDescricao(produto.getDescricao());
        response.setDataFabricacao(produto.getDataFabricacao());
        response.setValor(produto.getValor());
        return response;
    }

    public static List<ProdutoResponse> fromModel(List<Produto> produtos) {
        return produtos.stream()
                .map(ProdutoResponse::fromModel)
                .toList();
    }
}
