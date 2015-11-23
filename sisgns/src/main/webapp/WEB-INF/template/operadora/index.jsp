<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<bootstrap:summary text="${sucesso}"/>
<div class="panel panel-default">
    <div class="panel-body">
        <form action="<c:url value="/operadora/pesquisar.do" />" method="get" role="form">

            <jsp:include page="_form.jsp"/>

            <div style="float:right">
                <bootstrap:pesquisar/>
                <bootstrap:new url="/operadora/novo.do" label="Novo"/>
            </div>
        </form>
    </div>
</div>
<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th style="width:10%">#</th>
        <th>Nome</th>
        <th>Modalidade</th>
        <th style="width:5%"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultlist.consulta}" var="object">
        <tr>
            <td><a href='<c:url value="/operadora/${object.id}/abrir.do" />'>${object.id}</a></td>
            <td>${object.codigo} - ${object.nome}</td>
            <td>${object.modalidade.codigo} - ${object.modalidade.nome}</td>
            <td align="center">
                <bootstrap:delete action="/operadora/excluir.do" fieldName="object" value="${object.id}"
                                  message="Confirmar a exclusão da Operadora ${object.nome}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<bootstrap:paginate url="/operadora/pesquisar.do" resultList="${resultlist}"/>
