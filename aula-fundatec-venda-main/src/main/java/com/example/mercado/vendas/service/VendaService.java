package com.example.mercado.vendas.service;

import com.example.mercado.vendas.model.*;
import com.example.mercado.vendas.repository.ProdutoRepository;
import com.example.mercado.vendas.repository.VendaProdutoRepository;
import com.example.mercado.vendas.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaProdutoRepository vendaProdutoRepository;

    @Transactional
    public void iniciarVenda(List<Long> idProdutos) {

        var venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        venda.setEstado(EstadoVenda.ABERTO);
        venda.setValorPago(BigDecimal.ZERO);
        venda.setTipoPagamento(null);
        // criar relacao produtos com vendas

        venda = vendaRepository.save(venda);

        Venda finalVenda = venda;
        idProdutos.forEach(idProduto -> {
            Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException(
                    String.format("Produto %s não encontrado", idProduto)));
            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setVenda(finalVenda);
            vendaProduto.setProduto(produto);
            VendaProdutoId vendaProdutoId = new VendaProdutoId(produto.getId(), finalVenda.getId());
            vendaProduto.setId(vendaProdutoId);
            vendaProduto.setQuantidade(1);
            vendaProduto.setDataEntrada(LocalDateTime.now());
            vendaProdutoRepository.save(vendaProduto);
        });


    }

    public void adicionarProdutoAVenda(List<Long> idProdutos, Long idVenda) {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));
        // criar relacao produtos com vendas

        venda.checarOperacaoEmVenda();

        Venda finalVenda = venda;
        idProdutos.forEach(idProduto -> {
            Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException(
                    String.format("Produto %s não encontrado", idProduto)));
            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setVenda(finalVenda);
            vendaProduto.setProduto(produto);
            VendaProdutoId vendaProdutoId = new VendaProdutoId(produto.getId(), finalVenda.getId());
            vendaProduto.setId(vendaProdutoId);
            vendaProduto.setQuantidade(1);
            vendaProduto.setDataEntrada(LocalDateTime.now());
            vendaProdutoRepository.save(vendaProduto);
        });

    }

    public void removerProduto(List<Long> idProdutos, Long idVenda) {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));

        venda.checarOperacaoEmVenda();
        // criar relacao produtos com vendas

        Venda finalVenda = venda;
        idProdutos.forEach(idProduto -> {

            VendaProdutoId vendaProdutoId = new VendaProdutoId(idProduto, finalVenda.getId());

            VendaProduto vendaProduto = vendaProdutoRepository.findById(vendaProdutoId)
                    .orElseThrow(() -> new RuntimeException(String.format("venda de produto não existe para essa venda %s produto %s", finalVenda.getId(), idProduto)));

            vendaProdutoRepository.deleteById(vendaProdutoId);
        });

    }

    public void finalizarVenda(Long idVenda) {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));
        venda.checarOperacaoEmVenda();
        BigDecimal somaValorProdutos = somarValoresProdutosVenda(idVenda);

        if(!Objects.equals(venda.getValorPago(), somaValorProdutos)) {
            throw new RuntimeException("Inconsistencia nos valores da venda");
        }

        venda.setEstado(EstadoVenda.FINALIZADO);
        vendaRepository.save(venda);

    }

    private BigDecimal somarValoresProdutosVenda(Long idVenda) {
        List<VendaProduto> todasVendaProduto = vendaProdutoRepository.findAllByVendaId(idVenda);

        BigDecimal soma = BigDecimal.ZERO;
        for (VendaProduto vendaProduto : todasVendaProduto) {
            soma = soma.add(vendaProduto.getProduto().getValor());
        }
        return soma;

    }

    public void adicionarPagamento(Long idVenda, Integer valorPago, String formaPagamento) {
        Venda venda = vendaRepository.findById(idVenda)
                .orElseThrow(() -> new RuntimeException(String.format("Venda de id %s não existe", idVenda)));

        venda.checarOperacaoEmVenda();

        venda.setTipoPagamento(TipoPagamento.valueOf(formaPagamento));
        venda.setValorPago(BigDecimal.valueOf(valorPago /100));
        vendaRepository.save(venda);
    }

    public List<VendaProduto> listarVendas() {
        return vendaProdutoRepository.findAll();
    }

}
