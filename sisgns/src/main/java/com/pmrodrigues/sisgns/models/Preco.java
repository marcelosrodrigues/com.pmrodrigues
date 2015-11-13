package com.pmrodrigues.sisgns.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Marceloo on 28/10/2015.
 */
@Entity
@Table(name = "preco_plano_saude")
@EqualsAndHashCode(of = {"id"})
@ToString
public class Preco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "faixa_etaria_id")
    private FaixaEtaria faixaEtaria;

    @Column(name = "preco")
    @Setter
    @Getter
    private BigDecimal valor;
}
