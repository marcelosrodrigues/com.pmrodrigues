package com.pmrodrigues.sisgns.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table(name = "comissionamento")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "percentual", "regra"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.JOINED)
public class Comissionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    @NonNull
    private BigDecimal percentual;

    @ManyToOne(optional = false)
    @JoinColumn(name = "regra_id")
    @Getter
    @Setter
    @NonNull
    private RegraComissionamento regra;

}
