package com.pmrodrigues.endereco.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 10/08/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @Column(nullable = false)
    private String nome;

    public Cidade comNome(final String cidade) {
        this.nome = cidade;
        return this;
    }

    public Cidade comEstado(final Estado estado) {
        this.estado = estado;
        return this;
    }
}
