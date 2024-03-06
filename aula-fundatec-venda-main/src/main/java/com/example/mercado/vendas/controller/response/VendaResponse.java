package com.example.mercado.vendas.controller.response;

import com.example.mercado.vendas.model.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class VendaResponse {

    private Long id;
    private LocalDateTime dataVenda;
    private BigDecimal valorPago;
    private TipoPagamento tipoPagamento;
    private EstadoVenda estado;

    private List<ProdutoResponse> produtos;

    public static List<VendaResponse> fromModel(List<VendaProduto> vendasProduto) {


        Map<Venda, List<VendaProduto>> collect = vendasProduto.stream()
                .collect(Collectors.groupingBy(VendaProduto::getVenda));

        return collect.entrySet().stream().map(
                        (entry) ->  {
                            VendaResponse vendaResponse = new VendaResponse();
                            vendaResponse.setId(entry.getKey().getId());
                            vendaResponse.setDataVenda(entry.getKey().getDataVenda());
                            vendaResponse.setValorPago(entry.getKey().getValorPago());
                            vendaResponse.setTipoPagamento(entry.getKey().getTipoPagamento());
                            vendaResponse.setEstado(entry.getKey().getEstado());
                            var produtos = entry.getValue().stream()
                                    .map(VendaProduto::getProduto)
                                    .toList();
                            vendaResponse.setProdutos(ProdutoResponse.fromModel(produtos));
                            return vendaResponse;
                        }
                )
                .toList();
    }
}
