package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "nome", "ordem", "percentual"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Comissionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

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
