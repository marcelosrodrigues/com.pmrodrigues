package com.pmrodrigues.pessoa.models;

import com.pmrodrigues.endereco.models.Celular;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Telefone;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(exclude = {"id","nome", "endereco", "password"})
@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Column
    @Setter
    @Getter
    private String nome;

    @Column
    @Setter
    @Getter
    private String email;

    @ManyToOne(optional = false , cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Setter
    @Getter
    private Endereco endereco;

    @Getter
    @Setter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "telefone_pessoa",
            joinColumns = {@JoinColumn(name = "pessoa_id")},
            inverseJoinColumns = {@JoinColumn(name = "telefone_id")})
    @Where(clause = "tipo = 'R'")
    private Collection<Telefone> residenciais = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "telefone_pessoa",
            joinColumns = {@JoinColumn(name = "pessoa_id")},
            inverseJoinColumns = {@JoinColumn(name = "telefone_id")})
    @Where(clause = "tipo = 'C'")
    private Collection<Celular> celulares = new HashSet<>();


    public abstract String getDocumento();

    public Pessoa comEndereco(final Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public Pessoa comNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public abstract Pessoa comDocumento(final String documento);

    public Pessoa comEmail(final String email) {
        this.email = email;
        return this;
    }

}
