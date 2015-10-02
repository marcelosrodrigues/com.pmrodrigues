package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode()
@ToString(of = {"id", "nome", "comissionamento"})
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

    @Getter
    @Setter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "plano_id", nullable = false)
    @OrderBy("ordem")
    private List<Comissionamento> regra = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "administradora_id")
    @Getter
    @Setter
    private Administradora administradora;


}
