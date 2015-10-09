<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível salvar o corretor de planos de saúde"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Corretor
    </div>

    <div class="panel-body">

        <form action="<c:url value='/corretor/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="object.id" value="${object.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <input type="hidden" name="object.administradora" id="object.administradora"
                   value=""/>

            <bootstrap:textfield label="Administradora" value=""
                                 id="object.administradora.nome" errorField="administradora.nome"/>

            <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>

            <bootstrap:textfield label="E-mail" value="${object.email}" id="object.email" errorField="nome"/>

            <bootstrap:password label="Senha" value="" id="object.password" errorField="password"/>

            <div class="form-group">
                <label class="checkbox-inline">
                    <c:choose>
                        <c:when test="${object.bloqueado}">
                            <input type="checkbox" id="object.bloqueado" name="object.bloqueado" value="true"
                                   checked="checked"/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="object.bloqueado" name="object.bloqueado" value="true"/>
                        </c:otherwise>
                    </c:choose>
                    Bloqueado
                </label>
            </div>

            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>