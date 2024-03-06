package com.example.mercado.vendas.repository;

import com.example.mercado.vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
