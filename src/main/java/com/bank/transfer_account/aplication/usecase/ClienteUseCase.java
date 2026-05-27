package com.bank.transfer_account.aplication.usecase;

import com.bank.transfer_account.domain.clientes.Cliente;

import java.util.List;

public interface ClienteUseCase {

    Cliente create(Cliente cliente);
    Cliente findById(String id);
    List<Cliente> findAll();
}
