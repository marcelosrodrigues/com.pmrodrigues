package com.pmrodrigues.sisgns.models.security;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 11/12/2015.
 */
@Entity
@Table(name = "filtro_de_dados")
@EqualsAndHashCode(of = {"id"})
@ToString
@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Filtro implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Perfil perfil;
}
