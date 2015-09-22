package com.pmrodrigues.boletos.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@XmlType(name = "AgenciaType", namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Agencia implements Serializable {

    @XmlElement(name = "numero"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(name = "numero_agencia")
    @Setter
    @Getter
    private Integer numero;

    @XmlElement(name = "digito"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
    @Column(name = "digito_agencia")
    @Setter
    @Getter
    private String digito;

    public Agencia comNumero(final Integer numero) {
        this.numero = numero;
        return this;
    }

    public Agencia comDigito(final String digito) {
        this.digito = digito;
        return this;
    }
}
