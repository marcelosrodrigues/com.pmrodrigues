package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "operadora_id")
    @Getter
    @Setter
    private Operadora operadora;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "plano_id")
    @Getter
    @Setter
    @OrderBy("ordem")
    private Collection<Comissionamento> regra = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "administradora_id")
    @Getter
    @Setter
    private Administradora administradora;


}
