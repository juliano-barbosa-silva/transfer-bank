package com.bank.transfer_account.domain.contas;

import com.bank.transfer_account.utils.enums.TipoContaEnum;

import java.math.BigDecimal;

public record ContaDTO(String agencia, String numero, TipoContaEnum tipo, BigDecimal saldo, String idCliente) {
}
