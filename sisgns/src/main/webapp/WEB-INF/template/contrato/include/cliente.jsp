<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<input type="hidden" name="pessoa.id" value="${object.cliente.id}"/>

<div class="row">
    <div class="col-lg-8">
        <bootstrap:textfield label="Nome" value="${object.cliente.nome}" id="pessoa.nome" errorField="nome"/>
    </div>
    <div id="datanascimento">
        <div class="col-lg-4">
            <bootstrap:date label="Data de Nascimento" value="${pessoa.dataNascimento}" id="pessoa.dataNascimento"
                            errorField="dataNascimento"/>
        </div>
    </div>
</div>


<div class="form-group">

    <label class="radio-inline">
        <input type="radio" name="tipopessoa" id="tipopessoa" value="PF" onclick="javascript:setMascara(this);">Pessoa
        Fisica
    </label>
    <label class="radio-inline">
        <input type="radio" name="tipopessoa" id="tipopessoa" value="PJ" onclick="javascript:setMascara(this);">Pessoa
        Jurídica
    </label>
</div>

<bootstrap:textfield label="Documento" value="${object.cliente.documento}" id="pessoa.documento"
                     errorField="documento"/>

<bootstrap:textfield label="E-mail" value="${object.cliente.email}" id="pessoa.email" errorField="email"/>

<jsp:include page="/WEB-INF/template/contrato/include/endereco.jsp"/>

<hr/>

<h4>Telefones</h4>

<div class="row">

    <div class="col-lg-1">
        <bootstrap:textfield label="DDD"
                             value=""
                             id="residencial.ddd"
                             errorField="residencial.ddd"/>
    </div>

    <div class="col-lg-4">
        <bootstrap:textfield label="Telefone"
                             value=""
                             id="residencial.numero"
                             errorField="residencial.numero"/>
    </div>

    <div class="col-lg-1">
        <button id="adicionar-telefone-cliente" class="btn btn-success btn-circle" style="margin-top:25px;"
                type="button">
            <i class="fa fa-plus"></i>
        </button>
    </div>

</div>


<ul id="telefones" class="list-group">

    <c:forEach items="${object.cliente.residenciais}" var="telefone" varStatus="loop">
        <li class=list-group-item>(${telefone.ddd}) ${telefone.numero}
            <input type="hidden" name="pessoa.residenciais[${loop.index}].id" value="${telefone.id}"/>
            <input type="hidden" name="pessoa.residenciais[${loop.index}].ddd" value="${telefone.ddd}"/>
            <input type="hidden" name="pessoa.residenciais[${loop.index}].numero"
                   value="${telefone.numero}"/>
            <button id="remover-telefone" onclick='javascript:removerLI(this);'
                    class="btn btn-danger btn-circle" style='float: right; margin-top: -5px;' type="button"
                    telefone="(${telefone.ddd}) ${telefone.numero}">
                <i class="fa fa-minus"></i>
            </button>
        </li>
    </c:forEach>

</ul>