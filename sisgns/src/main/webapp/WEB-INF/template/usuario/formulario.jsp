<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="N�o foi poss�vel salvar o usu�rio de planos de sa�de"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Usu�rio
    </div>

    <div class="panel-body">

        <form action="<c:url value='/usuario/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <jsp:include page="/WEB-INF/includes/administradora.jsp"/>

            <jsp:include page="/WEB-INF/includes/usuario.jsp"/>

            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>