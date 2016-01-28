<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="N�o foi poss�vel trocar a senha do usu�rio"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Usuario
    </div>

    <div class="panel-body">

        <form action="<c:url value='/usuario/trocasenha.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <bootstrap:password label="Senha Antiga" value="" id="senhaAntiga" errorField="senhaAntiga"/>

            <bootstrap:password label="Nova Senha" value="" id="novaSenha" errorField="novaSenha"/>

            <bootstrap:password label="Confirma��o da nova Senha" value="" id="confirmacaoDaSenha"
                                errorField="confirmacaoDaSenha"/>

            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>