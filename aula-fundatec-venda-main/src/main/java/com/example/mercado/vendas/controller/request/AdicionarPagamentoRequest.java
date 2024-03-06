package com.example.mercado.vendas.controller.request;

import lombok.Data;

@Data
public class AdicionarPagamentoRequest {
    private Integer valorPago;
    private String formaPagamento;
}
