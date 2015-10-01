package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(exclude = {"nome"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Operadora implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    private String nome;

    public Operadora comNome(final String nome) {
        this.nome = nome;
        return this;
    }
}
