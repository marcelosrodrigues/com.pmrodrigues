package com.pmrodrigues.pagamentos.models;

import java.math.BigDecimal;

/**
 * Created by Marceloo on 31/08/2015.
 */
public interface Item {

    String getId();

    String getDescricao();

    BigDecimal getPreco();

    BigDecimal getQuantidade();

}
