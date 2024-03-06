package com.example.mercado.vendas.controller.request;

import com.example.mercado.vendas.model.Endereco;
import lombok.*;

@Data
public class EnderecoRequest {
    private String logradouro;
    private String bairro;

    public Endereco toModel() {
        var endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairro);
        return endereco;
    }
}
