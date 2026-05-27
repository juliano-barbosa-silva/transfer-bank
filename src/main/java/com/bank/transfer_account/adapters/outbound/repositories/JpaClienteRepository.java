package com.bank.transfer_account.adapters.outbound.repositories;

import com.bank.transfer_account.adapters.outbound.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClienteRepository extends JpaRepository<ClienteEntity, String> {
}
