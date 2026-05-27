package com.bank.transfer_account.aplication.usecase;

import com.bank.transfer_account.domain.contas.Conta;

import java.util.List;

public interface ContaUseCase {

    Conta create(Conta conta);
    Conta findById(String id);
    List<Conta> findAll();
}
