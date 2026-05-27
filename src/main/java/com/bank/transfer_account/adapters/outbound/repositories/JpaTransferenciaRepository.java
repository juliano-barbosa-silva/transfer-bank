package com.bank.transfer_account.adapters.outbound.repositories;

import com.bank.transfer_account.domain.transferencias.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransferenciaRepository extends JpaRepository<Transferencia, String> {
}
