package com.pmrodrigues.sisgns.models;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Marceloo on 07/12/2015.
 */
@Entity
@Table(name = "comissionamento_por_operadora")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ComissionamentoPorOperadora extends Comissionamento {
    public ComissionamentoPorOperadora(final BigDecimal percentual, final RegraComissionamento regra) {
        super(percentual, regra);
    }
}
