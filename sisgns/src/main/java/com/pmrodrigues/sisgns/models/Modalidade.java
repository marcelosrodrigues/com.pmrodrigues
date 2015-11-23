package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 23/11/2015.
 */
@Entity
@Table
@EqualsAndHashCode(of = {"codigo"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Modalidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    private String codigo;

    @Getter
    @Setter
    @Column
    private String nome;

}
