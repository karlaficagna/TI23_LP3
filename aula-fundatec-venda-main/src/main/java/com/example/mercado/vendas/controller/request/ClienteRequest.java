package com.example.mercado.vendas.controller.request;

import com.example.mercado.vendas.model.Cliente;
import lombok.Data;

/**
 * {
 *     "nome": "Anderson",
 *     "identidade": "00000",
 *     "cpf": "0000001",
 *     "endereco": {
 *         "logradouro": "Rua Alberto",
 *         "bairro": "Jardim Carvalho"
 *     }
 * }
 */
@Data
public class ClienteRequest {
    private String nome;
    private String identidade;;
    private String cpf;
    private EnderecoRequest endereco;

    public Cliente toModel() {
        var cliente = new Cliente();

        cliente.setNome(this.nome);
        cliente.setIdentidade(this.identidade);
        cliente.setCpf(this.cpf);
        cliente.setEndereco(this.endereco.toModel());
        return cliente;
    }

}
