package com.bank.transfer_account.adapters.outbound.entities;

import com.bank.transfer_account.utils.enums.TipoContaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "contas")
public abstract class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "agencia", nullable = false, length = 4)
    private String agencia;

    @NotNull
    @Column(name = "numero", nullable = false, unique = true, length = 10)
    private String numero;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoContaEnum tipo;  // CONTA_CORRENTE, CONTA_PJ

    @NotNull
    @Column(name = "saldo", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private String idCliente;

    @Column(name = "ativa", nullable = false)
    private boolean ativa = true;

    public ContaEntity(String id, String agencia, String numero, TipoContaEnum tipo, BigDecimal saldo,
                       String idCliente, boolean ativa) {
        this.id = id;
        this.agencia = agencia;
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = saldo;
        this.idCliente = idCliente;
        this.ativa = ativa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    // Define se este tipo de conta pode enviar/receber transferências
    public abstract boolean permiteTransferencia();

    public void setSaldo(BigDecimal saldo) {
        if (saldo != null && saldo.compareTo(BigDecimal.ZERO) >= 0)
            this.saldo = saldo;
    }

    public boolean temSaldoSuficiente(BigDecimal valor) {
        return this.saldo.compareTo(valor) >= 0;
    }


}
