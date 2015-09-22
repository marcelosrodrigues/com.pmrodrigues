package com.pmrodrigues.pagamentos.models;

import java.io.Serializable;

/**
 * Created by Marceloo on 15/04/2015.
 */
public class Portador implements Serializable {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;
    private CartaoCredito cartao;

    public String getNome() {
        return this.nome;
    }

    public String getCPF() {
        return this.cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public CartaoCredito getCartaoCredito() {
        return this.cartao;
    }

    public Portador comNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public Portador comCPF(final String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Portador comTelefone(final String telefone) {
        this.telefone = telefone;
        return this;
    }

    public Portador getEmail(final String email) {
        this.email = email;
        return this;
    }

    public Portador comEndereco(final Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public Portador comCartaoCredito(final CartaoCredito cartaoCredito) {
        this.cartao = cartaoCredito;
        return this;
    }

    public Portador comEmail(final String email) {
        this.email = email;
        return this;
    }
}
