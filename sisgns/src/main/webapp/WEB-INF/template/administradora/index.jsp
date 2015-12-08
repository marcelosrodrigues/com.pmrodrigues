<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<bootstrap:summary text="${sucesso}"/>

<div class="panel panel-default">
    <div class="panel-body">
        <form action="<c:url value="/administradora/pesquisar.do" />" method="get" role="form">
            <div class="row">
                <div class="col-lg-4">
                    <bootstrap:textfield label="CNPJ"
                                         value="${object.numeroDocumento}"
                                         id="object.numeroDocumento"
                                         errorField="object.numeroDocumento"/>

                </div>
                <div class="col-lg-8">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="object.nome"/>
                </div>
            </div>

            <div style="float:right">
                <bootstrap:pesquisar/>
                <bootstrap:new url="/administradora/novo.do" label="Novo"/>
            </div>

        </form>
    </div>
</div>

<table class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
        <th style="width:10%">#</th>
        <th>CNPJ</th>
        <th>Nome</th>
        <th style="width:5%"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultlist.consulta}" var="object">
        <tr>
            <td><a href='<c:url value="/administradora/${object.id}/abrir.do" />'>${object.id}</a></td>
            <td>${object.numeroDocumento}</td>
            <td>${object.nome}</td>
            <td align="center">
                <bootstrap:delete action="/administradora/excluir.do"
                                  fieldName="object" value="${object.id}"
                                  message="Confirma a exclusão da Administradora ${object.nome}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<bootstrap:paginate url="/administradora/pesquisar.do" resultList="${resultlist}"/>