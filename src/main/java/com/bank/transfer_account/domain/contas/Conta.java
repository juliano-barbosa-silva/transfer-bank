package com.bank.transfer_account.domain.contas;

import com.bank.transfer_account.utils.enums.TipoContaEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class Conta {

    private String idConta;
    private String agencia;
    private String numero;
    private TipoContaEnum tipo;  // CONTA_CORRENTE, CONTA_PJ
    private BigDecimal saldo = BigDecimal.ZERO;
    private String idCliente;
    private boolean ativa;

    public Conta(String idConta, String agencia, String numero, TipoContaEnum tipo, BigDecimal saldo,
                 String idCliente, boolean ativa) {
        this.idConta = idConta;
        this.agencia = agencia;
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.idCliente = idCliente;
        this.ativa = ativa;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoContaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoContaEnum tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
