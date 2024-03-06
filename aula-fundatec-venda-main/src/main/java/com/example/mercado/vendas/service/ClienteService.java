package com.example.mercado.vendas.service;

import com.example.mercado.vendas.model.Cliente;
import com.example.mercado.vendas.model.Endereco;
import com.example.mercado.vendas.repository.ClienteRepository;
import com.example.mercado.vendas.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public void criarNovoCliente(Cliente cliente) {
        Endereco endereco = cliente.getEndereco();
        enderecoRepository.save(endereco);
        clienteRepository.save(cliente);
    }

    public void editarCliente(Long id, Cliente cliente) {
        validarId(id);
        cliente.setId(id);

        Endereco enderecoExistente = enderecoRepository.findByClienteId(id);

        if(enderecoExistente != null) {
            cliente.getEndereco().setId(enderecoExistente.getId());
            if(!enderecoExistente.getBairro()
                    .equalsIgnoreCase(cliente.getEndereco().getBairro())) {
                throw new RuntimeException("Não pode trocar o bairro de um endereço existente");
            }
        }


        enderecoRepository.save(cliente.getEndereco());

        clienteRepository.save(cliente);
    }

    private void validarId(Long id) {
        clienteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException(String.format("Cliente de id %s não existe", id)));
    }

    public void deletarCliente(Long id) {
        validarId(id);
        clienteRepository.deleteById(id);
    }

}
