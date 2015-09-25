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
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column
    private String logradouro;

    @Column
    private String cep;

    public Endereco comCEP(final String cep) {
        this.cep = cep;
        return this;
    }

    public Endereco comLogradouro(final String logradouro) {
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

    public Endereco comCidade(final Cidade cidade) {
        this.cidade = cidade;
        return this;
    }

    public Endereco comEstado(final Estado estado) {
        this.estado = estado;
        return this;
    }

    public Endereco comBairro(final Bairro bairro) {
        this.bairro = bairro;
        return this;
    }
}
