<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<input type="hidden" id="object.endereco.id" name="object.endereco.id"
       value="${object.endereco.id}"/>
<div class="row">
    <div class="col-lg-2">
        <bootstrap:textfield label="CEP"
                             value="${object.endereco.cep}"
                             id="object.endereco.cep"
                             errorField="endereco.cep"/>
    </div>
    <div class="col-lg-6">
        <bootstrap:textfield label="Logradouro"
                             value="${object.endereco.logradouro}"
                             id="object.endereco.logradouro"
                             errorField="endereco.logradouro"/>
    </div>

    <div class="col-lg-2">
        <bootstrap:textfield label="Numero"
                             value="${object.endereco.numero}"
                             id="object.endereco.numero"
                             errorField="endereco.numero"/>
    </div>
    <div class="col-lg-2">
        <bootstrap:textfield label="Complemento"
                             value="${object.endereco.complemento}"
                             id="object.endereco.complemento"
                             errorField="endereco.complemento"/>
    </div>

</div>


<div class="row">

    <div class="col-lg-4">
        <input type="hidden" id="object.endereco.estado" name="object.endereco.estado"
               value="${object.endereco.estado.id}"/>
        <bootstrap:textfield label="Estado"
                             value="${object.endereco.estado.nome}"
                             id="object.endereco.estado.nome"
                             errorField="endereco.estado.nome"/>
    </div>

    <div class="col-lg-4">
        <input type="hidden" id="object.endereco.cidade" name="object.endereco.cidade"
               value="${object.endereco.cidade.id}"/>
        <input type="hidden" id="object.endereco.cidade.estado" name="object.endereco.cidade.estado"
               value="${object.endereco.cidade.estado.id}"/>
        <bootstrap:textfield label="Cidade"
                             value="${object.endereco.cidade.nome}"
                             id="object.endereco.cidade.nome"
                             errorField="endereco.cidade.nome"/>
    </div>

    <div class="col-lg-4">
        <input type="hidden" id="object.endereco.bairro" name="object.endereco.bairro"
               value="${object.endereco.bairro.id}"/>
        <input type="hidden" id="object.endereco.bairro.cidade" name="object.endereco.bairro.cidade"
               value="${object.endereco.bairro.cidade.id}"/>
        <bootstrap:textfield label="Bairro"
                             value="${object.endereco.bairro.nome}"
                             id="object.endereco.bairro.nome"
                             errorField="endereco.bairro.nome"/>
    </div>

</div>