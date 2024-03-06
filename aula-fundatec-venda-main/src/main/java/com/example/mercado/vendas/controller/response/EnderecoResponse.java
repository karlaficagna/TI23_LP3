package com.example.mercado.vendas.controller.response;

import com.example.mercado.vendas.model.Endereco;
import lombok.Data;

@Data
public class EnderecoResponse {
    private Long id;
    private String logradouro;
    private String bairro;

    public static EnderecoResponse fromModel(Endereco endereco) {
        var enderecoResponse = new EnderecoResponse();
        enderecoResponse.setId(endereco.getId());
        enderecoResponse.setLogradouro(endereco.getLogradouro());
        enderecoResponse.setBairro(endereco.getBairro());
        return enderecoResponse;
    }
}
