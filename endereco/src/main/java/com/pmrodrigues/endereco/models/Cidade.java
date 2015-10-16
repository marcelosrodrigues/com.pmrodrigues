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
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @NotEmpty(message = "Cidade é obrigatória")
    @Column(nullable = false)
    @NonNull
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
