package com.bank.transfer_account.utils.enums;

public enum StatusTransferenciaEnum {

    PENDENTE("pendente"),
    CONCLUIDA("concluida"),
    FALHA("falha");

    private final String descricao;

    StatusTransferenciaEnum(String descricao) {
        this.descricao = descricao;
    }
}
