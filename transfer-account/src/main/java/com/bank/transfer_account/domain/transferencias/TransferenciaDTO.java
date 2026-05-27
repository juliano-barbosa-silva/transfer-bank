package com.bank.transfer_account.domain.transferencias;

import java.math.BigDecimal;

public record TransferenciaDTO(String contaOrigemId, String contaDestinoId, BigDecimal valor) {
}
