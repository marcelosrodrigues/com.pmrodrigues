package com.pmrodrigues.sisgns.models;

import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Marceloo on 23/11/2015.
 */
@Entity
@Table
@EqualsAndHashCode(of = {"codigo"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SQLDelete(sql = "update modalidade set excluido = 1 where id = ?")
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
                condition = " exists ( select 1 from corretores_administradora filtro " +
                        "          where administradora_id = filtro.administradora_id " +
                        "            and filtro.corretor_id = :usuario )")
})
public class Modalidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    @NotEmpty(message = "Código é obrigatório")
    private String codigo;

    @Getter
    @Setter
    @Column
    @NotEmpty(message = "Nome é obrigatório")
    private String nome;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Administradora administradora;

    public Modalidade(final String codigo, final String nome) {
        this();
        this.codigo = codigo;
        this.nome = nome;
    }
}
