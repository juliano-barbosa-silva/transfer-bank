package com.bank.transfer_account.domain.transferencias;

import com.bank.transfer_account.utils.enums.StatusTransferenciaEnum;

import java.math.BigDecimal;

public class Transferencia {

    private String idTransferencia;
    private String contaOrigemId;
    private String contaDestinoId;
    private BigDecimal valor;
    private StatusTransferenciaEnum status;  // PENDENTE, CONCLUIDA, FALHA

    public Transferencia(String idTransferencia, String contaOrigemId, String contaDestinoId, BigDecimal valor, StatusTransferenciaEnum status) {
        this.idTransferencia = idTransferencia;
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.valor = valor;
        this.status = status;
    }

    public String getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(String idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getContaOrigemId() {
        return contaOrigemId;
    }

    public void setContaOrigemId(String contaOrigemId) {
        this.contaOrigemId = contaOrigemId;
    }

    public String getContaDestinoId() {
        return contaDestinoId;
    }

    public void setContaDestinoId(String contaDestinoId) {
        this.contaDestinoId = contaDestinoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusTransferenciaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusTransferenciaEnum status) {
        this.status = status;
    }
}
