<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível salvar o plano de planos de saúde"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Plano de saúde
    </div>

    <div class="panel-body">

        <form action="<c:url value='/plano/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="object.id" value="${object.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <jsp:include page="/WEB-INF/includes/administradora.jsp"/>

            <div class="row">

                <div class="col-lg-6">
                    <jsp:include page="/WEB-INF/includes/operadora.jsp"/>
                </div>

                <div class="col-lg-6">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>
                </div>

            </div>

            <hr/>
            <h4>Preços</h4>

            <div id="precos">
                <table class="table table-striped table-bordered table-hover">
                    <tbody>

                    <c:choose>
                        <c:when test="${object.id == null or object.id == 0}">
                            <c:forEach items="${faixa_etaria.consulta}" var="faixa" varStatus="loop">
                                <tr>
                                    <td width="75%">
                                            ${faixa.descricao}
                                        <input type="hidden" name="object.precos[${loop.index}].faixaEtaria"
                                               id="object.precos[${loop.index}].faixaEtaria"
                                               value="${faixa.id}"/>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" name="object.precos[${loop.index}].valor"
                                                   id="object.precos[${loop.index}].valor" class="form-control"
                                                   pattern="###.##"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${object.precos}" var="preco" varStatus="loop">
                                <tr>
                                    <td width="75%">
                                            ${preco.faixaEtaria.descricao}
                                        <input type="hidden" name="object.precos[${loop.index}].id"
                                               id="object.precos[${loop.index}].id"
                                               value="${preco.id}"/>
                                        <input type="hidden" name="object.precos[${loop.index}].faixaEtaria"
                                               id="object.precos[${loop.index}].faixaEtaria"
                                               value="${preco.faixaEtaria.id}"/>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <fmt:formatNumber value="${preco.valor}" pattern="###.##" var="valor"
                                                              maxFractionDigits="2" minFractionDigits="2"/>
                                            <input type="text" name="object.precos[${loop.index}].valor"
                                                   id="object.precos[${loop.index}].valor"
                                                   value="${valor}" class="form-control" pattern="###,##"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    </tbody>
                </table>
            </div>

            <hr/>
            <h4>Regras de comissionamento</h4>

            <div class="row">
                <div class="col-lg-4">
                    <bootstrap:textfield label="Regra" value="" id="comissionamento.nome"
                                         errorField="comissionamento.nome"/>
                </div>
                <div class="col-lg-4">
                    <bootstrap:number label="Percentual" value="" id="comissionamento.percentual"
                                      errorField="comissionamento.percentual"/>
                </div>
                <div class="col-lg-2">
                    <bootstrap:number label="Ordem" value="" id="comissionamento.ordem"
                                      errorField="comissionamento.ordem"/>
                </div>
                <div class="col-lg-1">
                    <button id="adicionar-regra" class="btn btn-success btn-circle" style="margin-top:25px;"
                            type="button">
                        <i class="fa fa-plus"></i>
                    </button>
                </div>
            </div>

            <div id="regras-de-comissionamento">
                <table class="table table-striped table-bordered table-hover">
                    <tbody>
                    <c:forEach items="${object.regra}" var="regra" varStatus="loop">
                        <tr>
                            <td>${regra.nome}
                                <input type="hidden" name="object.regra[${loop.index}].id" value="${regra.id}"/>
                                <input type="hidden" name="object.regra[${loop.index}].nome" value="${regra.nome}"/>
                                <input type="hidden" name="object.regra[${loop.index}].percentual"
                                       value="${regra.percentual}"/>
                                <input type="hidden" name="object.regra[${loop.index}].ordem" value="${regra.ordem}"/>
                            </td>
                            <td align="center" width="5%"><fmt:formatNumber value="${regra.percentual}"
                                                                            pattern="#"/></td>
                            <td align="center" width="5%">${regra.ordem}</td>
                            <td align="center" width="5%">
                                <button onclick="javascript:removerTR(this)" type="button"
                                        class="btn btn-danger btn-circle">
                                    <i class="fa fa-times"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>