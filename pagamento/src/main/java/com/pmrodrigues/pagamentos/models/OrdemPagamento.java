package com.pmrodrigues.pagamentos.models;


import com.pmrodrigues.pagamentos.TipoPagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public interface OrdemPagamento extends Serializable {

    BigDecimal getValor();

    String getDescricao();

    TipoPagamento getMeioPagamento();

    String getTransacao();

    String getStatus();

    String getMotivo();

    String getDocumento();

    Portador getPortador();

    String getCodigoReferencia();

    Integer getNumeroPrestacoes();

    OrdemPagamento comValor(final BigDecimal valor);

    OrdemPagamento comMeioPagamento(final TipoPagamento tipoPagamento);

    OrdemPagamento comDescricao(final String descricao);

    OrdemPagamento comPortador(final Portador portador);

    OrdemPagamento comTransacao(final String transacao);

    OrdemPagamento comDocumento(final String documento);

    OrdemPagamento comReferencia(final String referencia);

    OrdemPagamento comNumeroPrestacoes(final Integer numeroPrestacoes);

    OrdemPagamento status(final String status, final String description);

    Collection<Item> getItens();

    OrdemPagamento adicionar(final Collection<? extends Item> itens);

}
