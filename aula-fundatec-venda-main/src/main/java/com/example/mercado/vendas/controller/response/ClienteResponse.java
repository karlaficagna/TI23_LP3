package com.example.mercado.vendas.controller.response;

import com.example.mercado.vendas.controller.request.ClienteRequest;
import com.example.mercado.vendas.model.Cliente;
import lombok.Data;

@Data
public class ClienteResponse {
    private Long id;
    private String nome;
    private String identidade;;
    private String cpf;
    private EnderecoResponse endereco;

    public static ClienteResponse fromModel(Cliente cliente) {
        var clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setIdentidade(cliente.getIdentidade());
        clienteResponse.setCpf(cliente.getCpf());
        clienteResponse.setEndereco(EnderecoResponse.fromModel(cliente.getEndereco()));

        return clienteResponse;
    }

}
