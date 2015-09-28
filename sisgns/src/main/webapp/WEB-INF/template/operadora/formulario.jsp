<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível salvar a administradora de planos de saúde"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Operadora de planos de saúde
    </div>

    <div class="panel-body">

        <form action="<c:url value='/operadora/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="object.id" value="${object.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>


            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>