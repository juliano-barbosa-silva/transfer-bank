package com.bank.transfer_account.adapters.outbound.repositories.impl;

import com.bank.transfer_account.adapters.outbound.repositories.JpaContaRepository;
import com.bank.transfer_account.domain.contas.Conta;
import com.bank.transfer_account.domain.contas.ContaRepository;

import java.util.List;

public class JpaContaRepositoryImpl implements ContaRepository {

    private final JpaContaRepository jpaContaRepository;

    public JpaContaRepositoryImpl(JpaContaRepository jpaContaRepository) {
        this.jpaContaRepository = jpaContaRepository;
    }

    @Override
    public Conta save(Conta conta) {
        return null;
    }

    @Override
    public Conta findById(String id) {
        return null;
    }

    @Override
    public List<Conta> findAll() {
        return List.of();
    }
}
