package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.boletos.models.Cedente;
import com.pmrodrigues.endereco.models.Celular;
import com.pmrodrigues.endereco.models.Telefone;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
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
public class Administradora extends Cedente {

    @Getter
    @Setter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(name = "telefone_administrador",
            joinColumns = {@JoinColumn(name = "administradora_id")},
            inverseJoinColumns = {@JoinColumn(name = "telefone_id")})
    @Where(clause = "tipo = 'R'")
    @NotEmpty(message = "Deve existir pelo menos um telefone cadastrado para a Administradora")
    private List<Telefone> residenciais = new ArrayList<>();


    @Setter
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

}
