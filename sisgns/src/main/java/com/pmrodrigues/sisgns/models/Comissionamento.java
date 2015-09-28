package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Comissionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_id")
    @Getter
    @Setter
    private Plano plano;


    @ManyToOne(optional = false)
    @JoinColumn(name = "administradora_id")
    @Getter
    @Setter
    private Administradora administradora;

    @Column
    @Getter
    @Setter
    private String nome;

    @Column
    @Getter
    @Setter
    private BigDecimal percentual;

    @Column
    @Getter
    @Setter
    private Integer ordem;
}
