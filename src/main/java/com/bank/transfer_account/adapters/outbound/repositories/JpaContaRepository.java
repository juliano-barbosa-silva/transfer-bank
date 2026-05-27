package com.bank.transfer_account.adapters.outbound.repositories;

import com.bank.transfer_account.domain.contas.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaContaRepository extends JpaRepository<Conta, String> {
}
