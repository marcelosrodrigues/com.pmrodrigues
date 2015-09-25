<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>
<div class="row">
    <div class="col-lg-2">
        <bootstrap:textfield label="CEP"
                             value="${object.endereco.cep}"
                             id="object.endereco.cep"
                             errorField="object.endereco.cep"/>
    </div>
    <div class="col-lg-6">
        <bootstrap:textfield label="Logradouro"
                             value="${object.endereco.logradouro}"
                             id="object.endereco.logradouro"
                             errorField="object.endereco.logradouro"/>
    </div>

    <div class="col-lg-2">
        <bootstrap:textfield label="Numero"
                             value="${object.endereco.numero}"
                             id="object.endereco.numero"
                             errorField="object.endereco.numero"/>
    </div>
    <div class="col-lg-2">
        <bootstrap:textfield label="Complemento"
                             value="${object.endereco.complemento}"
                             id="object.endereco.complemento"
                             errorField="object.endereco.complemento"/>
    </div>

</div>


<div class="row">

    <div class="col-lg-4">
        <bootstrap:textfield label="Estado"
                             value="${object.endereco.estado.nome}"
                             id="object.endereco.estado.nome"
                             errorField="object.endereco.estado.nome"/>
    </div>

    <div class="col-lg-4">
        <bootstrap:textfield label="Cidade"
                             value="${object.endereco.cidade.nome}"
                             id="object.endereco.cidade.nome"
                             errorField="object.endereco.cidade.nome"/>
    </div>

    <div class="col-lg-4">
        <bootstrap:textfield label="Bairro"
                             value="${object.endereco.bairro.nome}"
                             id="object.endereco.bairro.nome"
                             errorField="object.endereco.bairro.nome"/>
    </div>

</div>