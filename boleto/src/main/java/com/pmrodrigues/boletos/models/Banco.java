package com.pmrodrigues.boletos.models;

import org.jrimum.bopepo.BancosSuportados;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Created by Marceloo on 16/04/2015.
 */
@XmlEnum
public enum Banco {
    ITAU(BancosSuportados.BANCO_ITAU.create()), BRADESCO(BancosSuportados.BANCO_BRADESCO.create()),
    BANCO_DO_BRASIL(BancosSuportados.BANCO_DO_BRASIL.create()), SANTANDER(BancosSuportados.BANCO_SANTANDER.create()),
    HSBC(BancosSuportados.HSBC.create()), SAFRA(BancosSuportados.BANCO_SAFRA.create()), CREDICOR(BancosSuportados.BANCOOB.create()),
    CAIXA(BancosSuportados.CAIXA_ECONOMICA_FEDERAL.create()), BANCOOB(BancosSuportados.BANCOOB.create());

    private final org.jrimum.domkee.financeiro.banco.febraban.Banco banco;

    private Banco(org.jrimum.domkee.financeiro.banco.febraban.Banco banco) {
        this.banco = banco;
    }

    public org.jrimum.domkee.financeiro.banco.febraban.Banco novoBanco() {
        return banco;
    }
}
