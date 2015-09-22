package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.boletos.models.Cedente;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(callSuper = true , exclude = {"planos"})
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Administradora extends Cedente {

    @Getter
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "administradora_id")
    private Collection<Plano> planos = new HashSet<>();

}
