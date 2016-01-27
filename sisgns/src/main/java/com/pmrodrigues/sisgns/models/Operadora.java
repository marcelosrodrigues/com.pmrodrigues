package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.sisgns.models.enums.QuantidadeParcelas;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
@FilterDefs(
        {
                @FilterDef(name = "administradora", parameters = {@ParamDef(
                        name = "usuario",
                        type = "long"
                )})
        }
)
@Filters({
        @Filter(name = "administradora",
                condition = " administradora_id in ( select filtro.administradora_id from corretores_administradora filtro where filtro.corretor_id = :usuario )")
})
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

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "operadora_id", nullable = false)
    private Collection<ComissionamentoPorOperadora> regrasDeComissionamento = new ArrayList<>();

    public Operadora(String codigo, String nome, Modalidade modalidade, Administradora administradora) {
        this();
        this.codigo = codigo;
        this.nome = nome;
        this.modalidade = modalidade;
        this.administradora = administradora;
    }

    public Operadora comNome(final String nome) {
        this.nome = nome;
        return this;
    }

}
