package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Marceloo on 07/12/2015.
 */
@Entity
@Table(name = "regra_comissionamento")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "nome", "ordem"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RegraComissionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    @NonNull
    private String nome;

    @Column
    @Getter
    @Setter
    @NonNull
    private Integer ordem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administradora_id")
    @Getter
    @Setter
    @NonNull
    private Administradora administradora;
}
