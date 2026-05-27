package com.bank.transfer_account.domain.clientes;

import java.util.UUID;

public class Cliente {

    private String idCliente;
    private String nome;
    private String tipoPessoa;  // // grava "F" (fisica) ou "J" (juridica) via converter
    private boolean ativo;

    public Cliente(String idCliente, String nome, String tipoPessoa, boolean ativo) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.ativo = ativo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
