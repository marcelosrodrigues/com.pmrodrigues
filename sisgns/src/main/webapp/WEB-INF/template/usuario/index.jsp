<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<bootstrap:summary text="${sucesso}"/>

<div class="panel panel-default">
    <div class="panel-body">
        <form action="<c:url value="/usuario/pesquisar.do" />" method="get" role="form">
            <div class="row">

                <div class="col-lg-6">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="object.nome"/>
                </div>

                <div class="col-lg-6">
                    <bootstrap:textfield label="E-mail" value="${object.nome}" id="object.email"
                                         errorField="object.email"/>
                </div>
            </div>
            <div style="float:right">
                <bootstrap:pesquisar/>
                <bootstrap:new url="/usuario/novo.do" label="Novo"/>
            </div>
        </form>
    </div>
</div>

<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th style="width:10%">#</th>
        <th>Nome</th>
        <th>E-mail</th>
        <th style="width:5%"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultlist.consulta}" var="object">
        <tr>
            <td><a href='<c:url value="/usuario/${object.id}/abrir.do" />'>${object.id}</a></td>
            <td>${object.nome}</td>
            <td>${object.email}</td>
            <td align="center">
                <bootstrap:delete action="/usuario/excluir.do" fieldName="object" value="${object.id}"
                                  message="Confirma a exclusão do usuário ${object.nome}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<bootstrap:paginate url="/usuario/pesquisar.do" resultList="${resultlist}"/>