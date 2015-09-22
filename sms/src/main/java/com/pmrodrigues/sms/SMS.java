package com.pmrodrigues.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Marceloo on 27/04/2015.
 */
@Component
public class SMS implements Serializable {

    private SMSService service;

    private String telefone;
    private String messagem;

    @Autowired
    public SMS(final SMSService service) {
        this.service = service;
    }

    public SMS comTelefone(final String telefone) {
        this.telefone = telefone;
        return this;
    }

    public SMS comMessagem(final String message) {
        this.messagem = message;
        return this;
    }

    public void enviar() {
        service.enviar(this);
    }

    public String getTelefone() {
        return telefone;
    }

    public String getMessagem() {
        return messagem;
    }
}
