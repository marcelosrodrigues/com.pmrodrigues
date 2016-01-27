package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.boletos.models.Cedente;
import com.pmrodrigues.endereco.models.Celular;
import com.pmrodrigues.endereco.models.Telefone;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(callSuper = true, exclude = {"planos", "nome", "residenciais", "celulares"})
@ToString(callSuper = true, exclude = {"planos", "comissionamento"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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
                        "           where filtro.administradora_id = id " +
                        "             and filtro.corretor_id = :usuario )")
})
public class Administradora extends Cedente {

    @Getter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "telefone_administrador",
            joinColumns = {@JoinColumn(name = "administradora_id")},
            inverseJoinColumns = {@JoinColumn(name = "telefone_id")})
    @Where(clause = "tipo = 'R'")
    @NotEmpty(message = "Deve existir pelo menos um telefone cadastrado para a Administradora")
    private List<Telefone> residenciais = new ArrayList<>();

    @Getter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "telefone_administrador",
            joinColumns = {@JoinColumn(name = "administradora_id")},
            inverseJoinColumns = {@JoinColumn(name = "telefone_id")})
    @Where(clause = "tipo = 'C'")
    private List<Celular> celulares = new ArrayList<>();

    public void adicionar(final Telefone telefone) {
        if (!this.residenciais.contains(telefone)) {
            this.residenciais.add(telefone);
        }
    }

    public void setResidenciais(final List<Telefone> telefones) {
        this.residenciais.clear();
        this.residenciais.addAll(telefones);
    }

    public void setCelulares(final List<Celular> telefones) {
        this.celulares.clear();
        this.celulares.addAll(telefones);
    }
}
