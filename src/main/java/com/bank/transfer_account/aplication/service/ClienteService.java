package com.bank.transfer_account.aplication.service;

import com.bank.transfer_account.aplication.usecase.ClienteUseCase;
import com.bank.transfer_account.domain.clientes.Cliente;
import com.bank.transfer_account.domain.clientes.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente create(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente findById(String id) {
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        return List.of();
    }
}
