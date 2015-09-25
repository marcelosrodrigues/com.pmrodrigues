<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível importar o boleto"/>
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
                                         errorField="object.numeroDocumento"/>

                </div>
                <div class="col-lg-7">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="object.nome"/>
                </div>
            </div>

            <hr/>

            <h4>Endereço</h4>
            <jsp:include page="/WEB-INF/includes/endereco.jsp"/>

            <hr/>

            <h4>Telefones</h4>

            <div class="row">

                <div class="col-lg-1">
                    <input type="hidden" name="telefone1.id" id="telefone1.id" value="${telefone1.id}"/>
                    <bootstrap:textfield label="DDD"
                                         value="${telefone1.ddd}"
                                         id="telefone1.ddd"
                                         errorField="telefone1.ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value="${telefone1.numero}"
                                         id="telefone1.numero"
                                         errorField="telefone1.numero"/>
                </div>

            </div>

            <div class="row">

                <div class="col-lg-1">
                    <input type="hidden" name="telefone2.id" id="telefone2.id" value="${telefone2.id}"/>
                    <bootstrap:textfield label="DDD"
                                         value="${telefone2.ddd}"
                                         id="telefone2.ddd"
                                         errorField="telefone2.ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value="${telefone2.numero}"
                                         id="telefone2.numero"
                                         errorField="telefone2.numero"/>
                </div>

            </div>

            <div class="row">

                <div class="col-lg-1">
                    <input type="hidden" name="telefone3.id" id="telefone3.id" value="${telefone3.id}"/>
                    <bootstrap:textfield label="DDD"
                                         value="${telefone3.ddd}"
                                         id="telefone3.ddd"
                                         errorField="telefone3.ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value="${telefone3.numero}"
                                         id="telefone3.numero"
                                         errorField="telefone3.numero"/>
                </div>

            </div>


            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>