package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Marceloo on 17/11/2015.
 */
@Entity
@Table
@ToString(exclude = "dependentes")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    private String nome;

    @Getter
    @Setter
    @Column
    private Date dataNascimento;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "pai_id")
    @Getter
    private Collection<Beneficiario> dependentes = new ArrayList<>();

    @ManyToOne()
    @Setter
    @Getter
    private Beneficiario pai;

    public Beneficiario comNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public Beneficiario comDataNascimento(final Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public void setDependentes(final Collection<Beneficiario> dependentes) {
        for (final Beneficiario dependente : dependentes) {
            dependente.setPai(this);
            if (!this.dependentes.contains(dependente)) {
                this.dependentes.add(dependente);
            }
        }
    }
}
