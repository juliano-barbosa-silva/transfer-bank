package com.bank.transfer_account.adapters.outbound.repositories.impl;

import com.bank.transfer_account.adapters.outbound.repositories.JpaTransferenciaRepository;
import com.bank.transfer_account.domain.transferencias.Transferencia;
import com.bank.transfer_account.domain.transferencias.TransferenciaRepository;

import java.util.List;

public class JpaTransferenciaRepositoryImpl implements TransferenciaRepository {

    private final JpaTransferenciaRepository jpaTransferenciaRepository;

    public JpaTransferenciaRepositoryImpl(JpaTransferenciaRepository jpaTransferenciaRepository) {
        this.jpaTransferenciaRepository = jpaTransferenciaRepository;
    }

    @Override
    public Transferencia save(Transferencia transferencia) {
        return null;
    }

    @Override
    public Transferencia findById(String id) {
        return null;
    }

    @Override
    public List<Transferencia> findAll() {
        return List.of();
    }
}
