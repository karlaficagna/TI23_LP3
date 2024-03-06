package com.example.mercado.vendas.controller;

import com.example.mercado.vendas.controller.request.AdicionarPagamentoRequest;
import com.example.mercado.vendas.controller.request.ProdutoId;
import com.example.mercado.vendas.controller.request.ProdutosIdRequest;
import com.example.mercado.vendas.controller.response.VendaResponse;
import com.example.mercado.vendas.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void iniciarVenda(
            @RequestBody ProdutosIdRequest produtosIdRequest
    ) {
        List<Long> idsProduto = produtosIdRequest.getProdutos()
                .stream()
                .map(ProdutoId::getIdProduto)
                .toList();

        vendaService.iniciarVenda(idsProduto);
    }

    @PostMapping("/{idVenda}/produtos")
    public void adicionarProdutosVenda(
            @PathVariable("idVenda") Long idVenda,
            @RequestBody ProdutosIdRequest produtosIdRequest
    ) {

        System.out.println("Adicionar Produtos a venda");
        List<Long> idsProduto = produtosIdRequest.getProdutos()
                .stream()
                .map(ProdutoId::getIdProduto)
                .toList();

        vendaService.adicionarProdutoAVenda(idsProduto, idVenda);

    }

    @DeleteMapping("/{idVenda}/produtos")
    public void removerProdutosDeVenda(
            @PathVariable("idVenda") Long idVenda,
            @RequestBody ProdutosIdRequest produtosIdRequest
    ) {

        System.out.println("Remover Produtos a venda");

        List<Long> idsProduto = produtosIdRequest.getProdutos()
                .stream()
                .map(ProdutoId::getIdProduto)
                .toList();

        vendaService.removerProduto(idsProduto, idVenda);

    }

    @PostMapping("/{idVenda}/pagamentos")
    public void adicionarPagamento(
            @PathVariable("idVenda") Long idVenda,
            @RequestBody AdicionarPagamentoRequest adicionarPagamentoRequest
    ) {

        System.out.println("Adicionar Pagamentos a venda");

        vendaService.adicionarPagamento(idVenda, adicionarPagamentoRequest.getValorPago(), adicionarPagamentoRequest.getFormaPagamento());

    }

    @PostMapping("/{idVenda}/finalizar")
    public void finalizarVenda(
            @PathVariable("idVenda") Long idVenda
    ) {

        System.out.println("Finalizar Venda");

        vendaService.finalizarVenda(idVenda);

    }

    @GetMapping
    public List<VendaResponse> listarVendas() {
        return VendaResponse.fromModel(vendaService.listarVendas());
    }


}
