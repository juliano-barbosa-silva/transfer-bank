package com.bank.transfer_account.domain.transferencias;

import java.util.List;

public interface TransferenciaRepository {

    Transferencia save(Transferencia transferencia);
    Transferencia findById(String id);
    List<Transferencia> findAll();
}
