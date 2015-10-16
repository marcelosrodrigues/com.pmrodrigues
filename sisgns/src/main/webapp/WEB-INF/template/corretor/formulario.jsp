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

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <jsp:include page="/WEB-INF/includes/administradora.jsp"/>

            <jsp:include page="/WEB-INF/includes/usuario.jsp"/>

            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>