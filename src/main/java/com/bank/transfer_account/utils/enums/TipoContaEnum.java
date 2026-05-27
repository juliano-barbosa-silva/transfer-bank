package com.bank.transfer_account.utils.enums;

public enum TipoContaEnum {

    CONTA_CORRENTE("Conta Corrente", true),
    CONTA_PJ("Conta Pessoa Jurídica", true),
    CONTA_INVESTIMENTO("Conta Investimento", false),
    CONTA_CREDITO("Conta Crédito", false);

    private final String descricao;
    private final boolean permiteTransferencia;

    TipoContaEnum(String descricao, boolean permiteTransferencia) {
        this.descricao = descricao;
        this.permiteTransferencia = permiteTransferencia;
    }

    public boolean permiteTransferencia() {
        return permiteTransferencia;
    }
}
