<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>
<form action="<c:url value='/modalidade/salvar.do' />" method="post" role="form" accept-charset="utf-8">
    <div class="panel panel-default">

        <div class="panel-heading">
            Modalidade
        </div>

        <div class="panel-body">


            <input type="hidden" name="object.id" value="${object.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="row">
                <div class="col-lg-12">
                    <jsp:include page="/WEB-INF/includes/administradora.jsp"/>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4">
                    <bootstrap:textfield label="Codigo" value="${object.codigo}" id="object.codigo"
                                         errorField="codigo"/>
                </div>
                <div class="col-lg-8">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>
                </div>
            </div>

            <div style="float:right">
                <bootstrap:salvar label="Salvar"/>
            </div>


        </div>
    </div>
</form>