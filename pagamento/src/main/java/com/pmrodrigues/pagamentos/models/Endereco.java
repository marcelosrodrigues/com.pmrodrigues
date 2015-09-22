package com.pmrodrigues.pagamentos.models;

import com.pmrodrigues.endereco.models.Estado;

import java.io.Serializable;


public class Endereco implements Serializable {

    private String logradouro;

    private String bairro;

    private String cep;

    private String cidade;

    private String telefone;

    private String celular;

    private Estado estado;

    private String complemento;

    private Integer numero;

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(final Estado estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCEP() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setCEP(final String CEP) {
        this.cep = CEP;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(final String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public Endereco comLogradouro(final String logradouro) {
        this.logradouro = logradouro;
        return this;
    }


    public Endereco comBairro(final String bairro) {
        this.bairro = bairro;
        return this;
    }

    public Endereco comCEP(final String cep) {
        this.cep = cep;
        return this;
    }


    public Endereco comNumero(final Integer numero) {
        this.numero = numero;
        return this;
    }


    public Endereco comCidade(final String cidade) {
        this.cidade = cidade;
        return this;
    }


    public Endereco comComplento(final String complemento) {
        this.complemento = complemento;
        return this;
    }


    public Endereco comEstado(final Estado estado) {
        this.estado = estado;
        return this;
    }
}
