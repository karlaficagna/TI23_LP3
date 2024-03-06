package com.example.mercado.vendas.repository;

import com.example.mercado.vendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
