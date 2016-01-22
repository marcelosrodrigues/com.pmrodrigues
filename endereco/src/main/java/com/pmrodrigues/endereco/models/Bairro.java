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
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "nome"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Bairro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    @Setter
    @Getter
    private Cidade cidade;

    @NotEmpty(message = "Bairro é obrigatória")
    @Column(nullable = false)
    @Setter
    @Getter
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
