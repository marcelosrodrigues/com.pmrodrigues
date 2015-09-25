package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.boletos.models.Boleto;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(exclude = {"nome", "convenio"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@SQLDelete(sql = "update comissao set excluido = 1 were id = ?")
@Where(clause = "excluido = 0")
public class Comissao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "regra_id")
    @Getter
    @NonNull
    private Comissionamento regra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "boleto_id")
    @Getter
    @NonNull
    private Boleto boleto;


}
