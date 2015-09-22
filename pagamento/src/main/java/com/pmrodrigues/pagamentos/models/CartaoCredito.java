package com.pmrodrigues.pagamentos.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marceloo on 15/04/2015.
 */
public class CartaoCredito implements Serializable {

    private String numero;

    private String codigoSeguranca;

    private Date dataExpiracao;

    public String getNumero() {
        return numero;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public CartaoCredito comNumero(final String numero) {
        this.numero = numero;
        return this;
    }

    public CartaoCredito comCodigoSeguranca(final String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
        return this;
    }

    public CartaoCredito comDataExpiracao(final Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
        return this;
    }
}
