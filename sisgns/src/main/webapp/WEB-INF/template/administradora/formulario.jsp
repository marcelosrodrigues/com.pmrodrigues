<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível salvar a administradora de planos de saúde"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Administradora de planos de saúde
    </div>

    <div class="panel-body">

        <form action="<c:url value='/administradora/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="object.id" value="${object.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="row">
                <div class="col-lg-5">
                    <bootstrap:textfield label="CNPJ"
                                         value="${object.numeroDocumento}"
                                         id="object.numeroDocumento"
                                         errorField="numeroDocumento"/>

                </div>
                <div class="col-lg-7">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>
                </div>
            </div>

            <hr/>

            <h4>Endereço</h4>
            <jsp:include page="/WEB-INF/includes/endereco.jsp"/>

            <hr/>

            <h4>Telefones</h4>

            <div class="row">

                <div class="col-lg-1">
                    <bootstrap:textfield label="DDD"
                                         value=""
                                         id="residencial.ddd"
                                         errorField="residencial.ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value=""
                                         id="residencial.numero"
                                         errorField="residencial.numero"/>
                </div>

                <div class="col-lg-1">
                    <button id="adicionar-telefone" class="btn btn-success btn-circle" style="margin-top:25px;"
                            type="button">
                        <i class="fa fa-plus"></i>
                    </button>
                </div>

            </div>


            <ul id="telefones" class="list-group">

                <c:forEach items="${object.residenciais}" var="telefone" varStatus="loop">
                    <li class=list-group-item>(${telefone.ddd}) ${telefone.numero}
                        <input type="hidden" name="object.residenciais[${loop.index}].id" value="${telefone.id}"/>
                        <input type="hidden" name="object.residenciais[${loop.index}].ddd" value="${telefone.ddd}"/>
                        <input type="hidden" name="object.residenciais[${loop.index}].numero"
                               value="${telefone.numero}"/>
                        <button id="remover-telefone" onclick='javascript:removerLI(this);'
                                class="btn btn-danger btn-circle" style='float: right; margin-top: -5px;' type="button">
                            <i class="fa fa-minus"></i>
                        </button>
                    </li>
                </c:forEach>

            </ul>


            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>