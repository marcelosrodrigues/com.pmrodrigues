<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<bootstrap:summary text="${sucesso}"/>

<form action="<c:url value="/corretor/pesquisar.do" />" method="get" role="form">
    <div class="row">

        <div class="col-lg-5">
            <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="object.nome"/>
        </div>

        <div class="col-lg-5">
            <bootstrap:textfield label="E-mail" value="${object.nome}" id="object.email" errorField="object.email"/>
        </div>

        <div class="col-lg-2" style="margin-top: 25px;">
            <bootstrap:pesquisar/>
        </div>
    </div>
</form>

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
            <td><a href='<c:url value="/corretor/${object.id}/abrir.do" />'>${object.id}</a></td>
            <td>${object.nome}</td>
            <td>${object.email}</td>
            <td align="center">
                <button type="button" class="btn btn-danger btn-circle">
                    <i class="fa fa-times"></i>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<bootstrap:paginate url="/corretor/pesquisar.do" resultList="${resultlist}"/>

<div class="row">
    <div class="col-sm-6">
        <a href="<c:url value='/corretor/novo.do' />" class="btn btn-info">Adicionar</a>
    </div>
</div>