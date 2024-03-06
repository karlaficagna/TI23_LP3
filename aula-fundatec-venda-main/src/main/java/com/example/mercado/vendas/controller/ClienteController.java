package com.example.mercado.vendas.controller;

import com.example.mercado.vendas.controller.request.ClienteRequest;
import com.example.mercado.vendas.controller.response.ClienteResponse;
import com.example.mercado.vendas.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v0/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void criarCliente(@RequestBody ClienteRequest clienteRequest) {
        clienteService.criarNovoCliente(clienteRequest.toModel());
    }

    @GetMapping
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarClientes()
                .stream()
                .map(ClienteResponse::fromModel)
                .toList();
    }

    @DeleteMapping(path = "/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }

    @PutMapping(path = "/{id}")
    public void editarCliente(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        System.out.println("Editando: ");
        System.out.println(clienteRequest);

        clienteService.editarCliente(id, clienteRequest.toModel());
    }
}
