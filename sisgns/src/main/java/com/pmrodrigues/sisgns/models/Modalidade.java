package com.pmrodrigues.sisgns.models;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@SQLDelete(sql = "update modalidade set excluido = 1 where id = ?")
@Where(clause = "excluido = 0")
public class Modalidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private String codigo;

    @Getter
    @Setter
    @Column
    @NonNull
    private String nome;

}
