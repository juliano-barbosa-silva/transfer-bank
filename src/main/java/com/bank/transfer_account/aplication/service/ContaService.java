package com.bank.transfer_account.aplication.service;

import com.bank.transfer_account.domain.contas.Conta;
import com.bank.transfer_account.domain.contas.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService implements ContaRepository {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
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
