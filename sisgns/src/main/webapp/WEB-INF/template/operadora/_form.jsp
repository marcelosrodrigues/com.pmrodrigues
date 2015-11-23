<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<div class="row">
    <div class="col-lg-12">
        <jsp:include page="/WEB-INF/includes/administradora.jsp"/>
    </div>
</div>

<div class="row">
    <div class="col-lg-4">
        <bootstrap:textfield label="Codigo" value="${object.codigo}" id="object.codigo" errorField="codigo"/>
    </div>
    <div class="col-lg-8">
        <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/modalidade.jsp"/>

<div class="form-group">

    <label class="radio-inline">
        <c:set var="checked" value=""/>
        <c:if test="${object.quantidadeParcelas == 'COM_24_PARCELAS'}">
            <c:set var="checked" value="checked=\"checked\""/>
        </c:if>
        <input type="radio" name="object.quantidadeParcelas" id="object.quantidadeParcelas" value="0" ${checked}>24
        parcelas
    </label>
    <label class="radio-inline">
        <c:set var="checked" value=""/>
        <c:if test="${object.quantidadeParcelas == 'COM_36_PARCELAS'}">
            <c:set var="checked" value="checked=\"checked\""/>
        </c:if>
        <input type="radio" name="object.quantidadeParcelas" id="object.quantidadeParcelas" value="1" ${checked}>36
        parcelas
    </label>
</div>


<div class="form-group">
    <label class="checkbox-inline">
        <c:set var="checked" value=""/>
        <c:if test="${object.usaPrimeiraParcela}">
            <c:set var="checked" value="checked=\"checked\""/>
        </c:if>
        <input type="hidden" id="object.usaPrimeiraParcela" name="object.usaPrimeiraParcela"
               value="${object.usaPrimeiraParcela}"/>
        <input type="checkbox" name="usaPrimeiraParcela" id="usaPrimeiraParcela" value="true" ${checked}>Deve usar a
        primeira parcela para o pagamento ao corretor
    </label>
</div>


<div class="form-group">
    <label class="checkbox-inline">
        <c:set var="checked" value=""/>
        <c:if test="${object.qualicorp}">
            <c:set var="checked" value="checked=\"checked\""/>
        </c:if>
        <input type="hidden" id="object.qualicorp" name="object.qualicorp" value="${object.qualicorp}"/>
        <input type="checkbox" name="qualicorp" id="qualicorp" value="true" ${checked}>Qualicorp
    </label>
</div>