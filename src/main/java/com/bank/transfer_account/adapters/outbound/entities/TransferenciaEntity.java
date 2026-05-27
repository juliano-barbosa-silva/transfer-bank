package com.bank.transfer_account.adapters.outbound.entities;

import com.bank.transfer_account.utils.enums.StatusTransferenciaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transferencias")
public class TransferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTransferencia;

    @NotNull
    @JoinColumn(name = "conta_origem_id", nullable = false)
    private String contaOrigemId;

    @NotNull
    @JoinColumn(name = "conta_destino_id", nullable = false)
    private String contaDestinoId;

    @NotNull
    @Positive(message = "O valor deve ser maior que zero")
    @Column(name = "valor", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusTransferenciaEnum status;  // PENDENTE, CONCLUIDA, FALHA

    @Column(name = "realizada_em", nullable = false)
    private LocalDateTime realizadaEm = LocalDateTime.now();

    public TransferenciaEntity(String contaOrigemId, String contaDestinoId, BigDecimal valor,
                               StatusTransferenciaEnum status) {
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.valor = valor;
        this.status = status;
    }

    public UUID getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(UUID idTransferencia) {
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

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(LocalDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
}
