<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>


<bootstrap:summary text="Não foi possível salvar o plano de planos de saúde"/>
<div class="panel panel-default">

    <div class="panel-heading">
        Plano de saúde
    </div>

    <div class="panel-body">

        <form action="<c:url value='/plano/salvar.do' />" method="post" accept-charset="utf-8" role="form">

            <input type="hidden" name="object.id" value="${object.id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <input type="hidden" name="object.administradora" id="object.administradora"
                   value="${object.administradora.id}"/>
            <bootstrap:textfield label="Administradora" value="${object.administradora.nome}"
                                 id="object.administradora.nome" errorField="nome"/>

            <div class="row">

                <div class="col-lg-6">
                    <input type="hidden" name="object.operadora" id="object.operadora" value="${object.operadora.id}"/>
                    <bootstrap:textfield label="Operadora" value="${object.operadora.nome}" id="object.operadora.nome"
                                         errorField="nome"/>
                </div>

                <div class="col-lg-6">
                    <bootstrap:textfield label="Nome" value="${object.nome}" id="object.nome" errorField="nome"/>
                </div>

            </div>
            <hr/>
            <h4>Regras de comissionamento</h4>

            <div class="row">
                <div class="col-lg-4">
                    <bootstrap:textfield label="Regra" value="" id="comissionamento.nome"
                                         errorField="comissionamento.nome"/>
                </div>
                <div class="col-lg-4">
                    <bootstrap:textfield label="Percentual" value="" id="comissionamento.percentual"
                                         errorField="comissionamento.percentual"/>
                </div>
                <div class="col-lg-2">
                    <bootstrap:textfield label="Ordem" value="" id="comissionamento.ordem"
                                         errorField="comissionamento.ordem"/>
                </div>
                <div class="col-lg-1">
                    <button id="adicionar-regra" class="btn btn-success btn-circle" style="margin-top:25px;"
                            type="button">
                        <i class="fa fa-plus"></i>
                    </button>
                </div>
            </div>

            <div id="regras-de-comissionamento">
                <table class="table table-striped table-bordered table-hover">
                    <tbody>
                    </tbody>
                </table>
            </div>


            <bootstrap:salvar label="Salvar"/>
        </form>

    </div>

</div>