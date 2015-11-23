package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.sisgns.models.enums.QuantidadeParcelas;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(of = {"codigo"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SQLDelete(sql = "update operadora set excluido = 1 where id = ?")
@Where(clause = "excluido = 0")
public class Operadora implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    private String codigo;

    @Getter
    @Setter
    @Column
    private String nome;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Modalidade modalidade;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Administradora administradora;

    @Getter
    @Setter
    @Column
    private Boolean qualicorp = null;

    @Getter
    @Setter
    @Column
    private Boolean usaPrimeiraParcela = null;

    @Getter
    @Setter
    @Column
    @Enumerated(EnumType.ORDINAL)
    private QuantidadeParcelas quantidadeParcelas;

    public Operadora comNome(final String nome) {
        this.nome = nome;
        return this;
    }
}
