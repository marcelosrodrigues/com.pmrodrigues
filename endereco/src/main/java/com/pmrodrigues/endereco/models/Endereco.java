package com.pmrodrigues.endereco.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "logradouro_id")
    private Logradouro logradouro;

    private String numero;

    private String complemento;

    public Endereco comLogradouro(final Logradouro logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public Endereco comNumero(final String numero) {
        this.numero = numero;
        return this;
    }

    public Endereco comComplemento(final String complemento) {
        this.complemento = complemento;
        return this;
    }
}
