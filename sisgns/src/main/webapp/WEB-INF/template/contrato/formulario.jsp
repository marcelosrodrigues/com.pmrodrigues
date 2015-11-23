<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível salvar o contrato"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Contrato
    </div>

    <div class="panel-body">

        <form action="<c:url value='/contrato/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <jsp:include page="/WEB-INF/includes/administradora.jsp"/>

            <div class="row">
                <div class="col-lg-6">
                    <bootstrap:textfield label="N° do Contrato" value="${object.numeroContrato}"
                                         id="object.numeroContrato" errorField="numeroContrato"/>
                </div>
                <div class="col-lg-3">
                    <bootstrap:date label="Data de assinatura" value="${object.dataInicio}"
                                    id="object.dataInicio" errorField="dataInicio" pattern="dd-mm-yyyy"/>
                </div>
                <div class="col-lg-3">
                    <bootstrap:date label="Data de término" value="${object.dataTermino}"
                                    id="object.dataTermino" errorField="dataTermino" pattern="dd-mm-yyyy"/>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6">
                    <input type="hidden" name="object.operadora" id="object.operadora"
                           value="${object.plano.operadora.id}"/>
                    <bootstrap:textfield label="Operadora" value="${object.plano.operadora.nome}"
                                         id="object.operadora.nome" errorField="operadora.nome"/>
                </div>
                <div class="col-lg-6">
                    <input type="hidden" name="object.plano" id="object.plano"
                           value="${object.plano.id}"/>
                    <bootstrap:textfield label="Plano" value="${object.plano.nome}"
                                         id="object.plano.nome" errorField="plano.nome"/>
                </div>
            </div>

            <input type="hidden" name="object.vendidoPor" id="object.vendidoPor"
                   value="${object.plano.id}"/>
            <bootstrap:textfield label="Corretor" value="${object.vendidoPor.nome}"
                                 id="object.vendidoPor.nome" errorField="vendidoPor.nome"/>

            <hr/>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#cliente" data-toggle="tab">Cliente</a>
                </li>
                <li>
                    <a href="#beneficiarios" data-toggle="tab">Beneficiarios</a>
                </li>
                <li>
                    <a href="#financeiro" data-toggle="tab">Financeiro</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="cliente" style="padding-top: 10px;">
                    <jsp:include page="/WEB-INF/template/contrato/include/cliente.jsp"/>
                </div>
                <div class="tab-pane fade in" id="beneficiarios" style="padding-top: 10px;">
                    <jsp:include page="/WEB-INF/template/contrato/include/beneficiario.jsp"/>
                </div>
                <div class="tab-pane fade in" id="financeiro" style="padding-top: 10px;">
                    &nbsp;
                </div>
            </div>

            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>