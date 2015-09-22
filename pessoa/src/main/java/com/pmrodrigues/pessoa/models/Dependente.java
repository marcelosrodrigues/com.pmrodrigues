package com.pmrodrigues.pessoa.models;

import lombok.*;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Marceloo on 22/09/2015.
 */
@DiscriminatorFormula("pai_id is not null")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Dependente extends PessoaFisica {

    @ManyToOne(optional = true , fetch = FetchType.LAZY)
    @Setter
    @Getter
    private PessoaFisica responsavel;
}
