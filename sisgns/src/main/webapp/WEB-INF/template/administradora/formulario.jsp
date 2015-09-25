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
                    <input type="hidden" name="object.residenciais[0].id" id="object.residenciais[0].id" value=""/>
                    <bootstrap:textfield label="DDD"
                                         value=""
                                         id="object.residenciais[0].ddd"
                                         errorField="object.residenciais[0].ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value=""
                                         id="object.residenciais[0].numero"
                                         errorField="object.residenciais[0].numero"/>
                </div>

            </div>

            <div class="row">

                <div class="col-lg-1">
                    <input type="hidden" name="object.residenciais[1].id" id="object.residenciais[1].id" value=""/>
                    <bootstrap:textfield label="DDD"
                                         value=""
                                         id="object.residenciais[1].ddd"
                                         errorField="object.residenciais[1].ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value=""
                                         id="object.residenciais[1].numero"
                                         errorField="object.residenciais[1].numero"/>
                </div>

            </div>

            <div class="row">

                <div class="col-lg-1">
                    <input type="hidden" name="object.residenciais[2].id" id="object.residenciais[2].id" value=""/>
                    <bootstrap:textfield label="DDD"
                                         value=""
                                         id="object.residenciais[2].ddd"
                                         errorField="object.residenciais[2].ddd"/>
                </div>

                <div class="col-lg-4">
                    <bootstrap:textfield label="Telefone"
                                         value=""
                                         id="object.residenciais[2].numero"
                                         errorField="object.residenciais[2].numero"/>
                </div>

            </div>


            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>