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
@XmlType(name = "NossoNumeroType"
        , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
@EqualsAndHashCode()
@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class NossoNumero implements Serializable {

    @XmlElement(name = "numero"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(name = "nossonumero")
    @Setter
    @Getter
    private String numero;

    @XmlElement(name = "digito"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
    @Column(name = "digito_nossonumero")
    @Setter
    @Getter
    private String digito;

    public NossoNumero comNumero(final String numero) {
        this.numero = numero;
        return this;
    }

    public NossoNumero comDigito(final String digito) {
        this.digito = digito;
        return this;
    }
}
