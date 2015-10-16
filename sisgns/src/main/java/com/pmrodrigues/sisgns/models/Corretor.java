package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.sisgns.models.security.Usuario;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Corretor extends Usuario {

    @Setter
    @Getter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinTable(name = "corretores_administradora",
            joinColumns = {@JoinColumn(name = "corretor_id")},
            inverseJoinColumns = {@JoinColumn(name = "administradora_id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"corretor_id", "administradora_id"})})
    private Administradora adminstradora;
}
