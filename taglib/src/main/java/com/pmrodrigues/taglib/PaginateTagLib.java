package com.pmrodrigues.taglib;

import com.pmrodrigues.persistence.daos.ResultList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Enumeration;

import static java.lang.String.format;

/**
 * Created by Marceloo on 02/03/2015.
 */
public class PaginateTagLib extends SimpleTagSupport {

    private ResultList resultList;
    private String URL;

    @Override
    public void doTag() throws JspException, IOException {

        final JspWriter out = this.getJspContext().getOut();

        out.print("<div class=\"row\">");
        out.print("<div class=\"col-sm-5\">");
        out.print(format("<div class=\"dataTables_info\" id=\"dataTables-example_info\" role=\"alert\" " +
                        "aria-live=\"polite\" aria-relevant=\"all\">Pagina %s de %s</div></div>",
                resultList.getNumeroDaPagina() + 1, resultList.getQuantidadeDePaginas()));

        out.print("<div class=\"col-sm-7\" style=\"top: -30px;text-align:right;\"><div class=\"dataTables_paginate paging_simple_numbers\">");
        out.print("<ul class=\"pagination\">");

        out.print(this.adicionarBotaoPrevious());

        out.print(adicionaLinhaCentralPaginacao());

        out.print(this.adicionarBotaoNext());

        out.print("</ul></div></div></div>");


    }

    public String adicionarBotaoNext() {
        final StringBuffer out = new StringBuffer();
        final boolean temProximaPagina = resultList.temProximaPagina();
        out.append(format("<li class=\"paginate_button next %s\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_next\">", temProximaPagina ? "" : "disabled"));
        out.append(format("<a href=\"%s?page=%s%s\">&gt;&gt;</a></li>", getURL(), temProximaPagina ? this.resultList.getNumeroDaPagina() + 1 : resultList.getQuantidadeDePaginas(), recuperaParametrosConsulta()));

        return out.toString();
    }

    public String adicionarBotaoPrevious() {

        final StringBuffer out = new StringBuffer();
        final boolean temPaginaAnterior = resultList.temPaginaAnterior();
        out.append(format("<li class=\"paginate_button previous %s\" aria-controls=\"dataTables-example\" tabindex=\"0\" id=\"dataTables-example_previous\">",
                temPaginaAnterior ? "" : "disabled"));
        out.append(format("<a href=\"%s?page=%s%s\">&lt;&lt;</a></li>", getURL(), temPaginaAnterior ? resultList.getQuantidadeDePaginas() - 1 : 0, recuperaParametrosConsulta()));
        return out.toString();

    }

    public String adicionaLinhaCentralPaginacao() {

        final StringBuffer out = new StringBuffer();

        long pagecount = resultList.getQuantidadeDePaginas();
        long index = 0L;

        if (resultList.getQuantidadeDePaginas() > 10L) {
            pagecount = resultList.getNumeroDaPagina() + 10L;
            index = resultList.getNumeroDaPagina();
        }

        for (; index < pagecount; index++) {
            out.append(format("<li class=\"paginate_button\" tabindex=\"%s\"><a href=\"%s?page=%s%s\">%s</a></li>", index, getURL(), index, recuperaParametrosConsulta(), index + 1));
        }

        return out.toString();
    }

    public void setResultList(final ResultList resultList) {
        this.resultList = resultList;
    }

    public void setUrl(final String URL) {
        this.URL = URL;
    }

    public String recuperaParametrosConsulta() {
        final StringBuffer parameters = new StringBuffer();
        final PageContext context = (PageContext) this.getJspContext();
        final HttpServletRequest request = (HttpServletRequest) context.getRequest();


        for (final Enumeration<String> names = request.getParameterNames(); names.hasMoreElements(); ) {
            final String name = names.nextElement();
            if (!"page".equalsIgnoreCase(name)) {
                parameters.append(format("&%s=%s", name, request.getParameter(name)));
            }
        }

        return parameters.toString();
    }

    public String getURL() {
        final PageContext pageContext = (PageContext) this.getJspContext();
        final String context = ((HttpServletRequest) pageContext.getRequest()).getContextPath();

        if ("/".equalsIgnoreCase(context)) {
            return format("%s", URL);
        } else {
            return format("%s%s", context, URL);
        }

    }

}


