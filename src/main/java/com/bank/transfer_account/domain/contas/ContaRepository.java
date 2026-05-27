package com.bank.transfer_account.domain.contas;

import java.util.List;

public interface ContaRepository {

    Conta save(Conta conta);
    Conta findById(String id);
    List<Conta> findAll();
}
