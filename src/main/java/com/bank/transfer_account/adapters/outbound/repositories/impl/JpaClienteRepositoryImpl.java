package com.bank.transfer_account.adapters.outbound.repositories.impl;

import com.bank.transfer_account.adapters.outbound.repositories.JpaClienteRepository;
import com.bank.transfer_account.domain.clientes.Cliente;
import com.bank.transfer_account.domain.clientes.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository jpaClienteRepository;

    public JpaClienteRepositoryImpl(JpaClienteRepository jpaClienteRepository) {
        this.jpaClienteRepository = jpaClienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
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
