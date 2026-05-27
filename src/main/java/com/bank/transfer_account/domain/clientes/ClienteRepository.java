package com.bank.transfer_account.domain.clientes;

import java.util.List;

public interface ClienteRepository {

    Cliente save(Cliente cliente);
    Cliente findById(String id);
    List<Cliente> findAll();
}
