package com.bank.transfer_account.aplication.usecase;

import com.bank.transfer_account.domain.transferencias.Transferencia;

import java.util.List;

public interface TransferenciaUseCase {

    Transferencia create(Transferencia transferencia);
    Transferencia findById(String id);
    List<Transferencia> findAll();
}
