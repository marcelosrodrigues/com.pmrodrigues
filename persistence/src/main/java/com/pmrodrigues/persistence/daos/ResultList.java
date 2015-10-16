package com.pmrodrigues.persistence.daos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * Created by Marceloo on 10/12/2014.
 */
public class ResultList<E> {

    private final long recordCount;
    private final Criteria criteria;
    private long page = 0L;
    private long pageCount;
    private long pageSize;
    private static long TAMANHO_DEFAULT_PAGINA = 20;
    private boolean todos;

    public ResultList(final Criteria criteria) {

        this.recordCount = (Long) criteria.setProjection(Projections.count("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .uniqueResult();

        criteria.setProjection(null)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        this.criteria = criteria;
        calculaQuantidadeDePaginas(TAMANHO_DEFAULT_PAGINA);
    }

    public boolean estaVazio() {
        return recordCount == 0L;
    }

    private void calculaQuantidadeDePaginas(final long quantidadeMaximaRegistros) {
        if (quantidadeMaximaRegistros > 0) {
            this.pageCount = recordCount / quantidadeMaximaRegistros;
            if (recordCount % quantidadeMaximaRegistros > 0L) {
                pageCount++;
            }
        } else {
            this.pageCount = 0;
        }
        this.pageSize = quantidadeMaximaRegistros;
    }

    public ResultList<E> naPagina(final long page) {
        this.todos = false;
        this.page = page;
        return this;
    }

    public ResultList<E> comTamanhoPagina(final long quantidadeMaximaRegistros) {
        calculaQuantidadeDePaginas(quantidadeMaximaRegistros);
        return this;
    }

    public boolean temPaginaAnterior() {
        if (!this.todos && page > 0L && page < pageCount) {
            page--;
            return true;
        } else {
            return false;
        }
    }

    public boolean temProximaPagina() {
        if (!this.todos && pageCount > 1 && page < pageCount - 1) {
            page++;
            return true;
        } else {
            return false;
        }
    }

    public long getNumeroDaPagina() {
        return page;
    }

    public long getQuantidadeDePaginas() {
        return pageCount;
    }

    public List<E> getConsulta() {
        if (todos) {
            return this.criteria.list();
        } else {
            return this.criteria.setFirstResult(Long.valueOf(page * pageSize).intValue())
                    .setMaxResults(Long.valueOf(page * pageSize + pageSize).intValue())
                    .list();
        }
    }

    public long getQuantidadeRegistros() {
        return recordCount;
    }

    public ResultList<E> naUltimaPage() {
        this.page = this.pageCount - 1;
        return this;
    }

    public ResultList<E> naPrimeiraPagina() {
        this.page = 0L;
        return this;
    }

    public ResultList<E> todos() {
        this.todos = true;
        this.calculaQuantidadeDePaginas(this.recordCount);
        return this;
    }
}
