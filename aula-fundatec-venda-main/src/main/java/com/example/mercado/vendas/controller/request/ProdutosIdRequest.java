package com.example.mercado.vendas.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class ProdutosIdRequest {
    private List<ProdutoId> produtos;
}
