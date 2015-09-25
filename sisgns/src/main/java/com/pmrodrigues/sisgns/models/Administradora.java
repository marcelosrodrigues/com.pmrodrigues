package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.boletos.models.Cedente;
import com.pmrodrigues.endereco.models.Celular;
import com.pmrodrigues.endereco.models.Telefone;
import lombok.*;
import org.apache.commons.collections.Predicate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.select;
import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(callSuper = true, exclude = {"planos", "nome"})
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Administradora extends Cedente {

    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "administradora_id")
    private Collection<Plano> planos = new HashSet<>();

    @Getter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "administradora_id")
    private Collection<Comissionamento> comissionamentos = new HashSet<>();


    @Getter
    @Setter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "telefone_administrador",
            joinColumns = {@JoinColumn(name = "administradora_id")},
            inverseJoinColumns = {@JoinColumn(name = "telefone_id")})
    @Where(clause = "tipo = 'R'")
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

    public void setResidenciais(final Collection<Telefone> residenciais) {
        this.residenciais.clear();
        this.residenciais.addAll(select(residenciais, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                final Telefone telefone = (Telefone) object;
                return !isBlankOrNull(telefone.getDdd()) && !isBlankOrNull(telefone.getNumero());
            }
        }));
    }

    public void setCelulares(final Collection<Celular> celulares) {
        this.celulares.clear();
        this.celulares.addAll(select(celulares, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                final Celular telefone = (Celular) object;
                return !isBlankOrNull(telefone.getDdd()) && !isBlankOrNull(telefone.getNumero());
            }
        }));
    }

}
