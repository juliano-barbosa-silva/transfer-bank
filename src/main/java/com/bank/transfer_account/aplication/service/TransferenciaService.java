package com.bank.transfer_account.aplication.service;

import com.bank.transfer_account.aplication.usecase.TransferenciaUseCase;
import com.bank.transfer_account.domain.transferencias.Transferencia;
import com.bank.transfer_account.domain.transferencias.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaService implements TransferenciaUseCase {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Override
    public Transferencia create(Transferencia transferencia) {
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
