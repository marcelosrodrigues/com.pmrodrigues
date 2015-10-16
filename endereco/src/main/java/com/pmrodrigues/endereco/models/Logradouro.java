package com.pmrodrigues.endereco.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 10/08/2015.
 */
@Entity
@Table
@EqualsAndHashCode(of = "cep")
@ToString(of = {"id", "logradouro", "cep"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Logradouro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;

    @NonNull
    @Column(nullable = false)
    private String logradouro;

    @NonNull
    @Column(nullable = false)
    private String cep;

    public Logradouro comCEP(final String cep) {
        this.cep = cep;
        return this;
    }

    public Logradouro comLogradouro(final String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public Logradouro comBairro(final Bairro bairro) {
        this.bairro = bairro;
        return this;
    }
}
