package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 28/10/2015.
 */
@Entity
@Table(name = "faixa_etaria")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class FaixaEtaria implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Column
    @Setter
    @Getter
    private Integer inicio;

    @Column
    @Setter
    @Getter
    private Integer termino;

    @Column
    @Setter
    @Getter
    private String descricao;

}
