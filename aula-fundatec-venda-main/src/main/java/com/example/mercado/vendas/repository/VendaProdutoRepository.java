package com.example.mercado.vendas.repository;

import com.example.mercado.vendas.model.Venda;
import com.example.mercado.vendas.model.VendaProduto;
import com.example.mercado.vendas.model.VendaProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, VendaProdutoId> {

    @Query("select v from VendaProduto v where v.id.vendaId = :idVenda")
    List<VendaProduto> findAllByVendaId(Long idVenda);
}
