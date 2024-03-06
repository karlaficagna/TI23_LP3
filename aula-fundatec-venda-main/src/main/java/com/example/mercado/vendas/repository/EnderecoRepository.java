package com.example.mercado.vendas.repository;

import com.example.mercado.vendas.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Cliente c join c.endereco e where c.id = :id")
    Endereco findByClienteId(Long id);
}
