package com.pmrodrigues.endereco.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 23/09/2015.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("R")
@NoArgsConstructor()
@EqualsAndHashCode
@ToString
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @Column
    private String ddd;

    @Setter
    @Getter
    @Column
    private String numero;

}
