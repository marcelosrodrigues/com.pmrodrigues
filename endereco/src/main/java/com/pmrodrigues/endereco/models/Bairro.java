package com.pmrodrigues.endereco.models;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 10/08/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString(of = {"id", "nome"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Bairro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @NotEmpty(message = "Bairro é obrigatória")
    @Column(nullable = false)
    private String nome;

    public Bairro comNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public Bairro comCidade(final Cidade cidade) {
        this.cidade = cidade;
        return this;
    }
}
