<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>
<h4>Endereço</h4>
<input type="hidden" id="pessoa.endereco.id" name="pessoa.endereco.id"
       value="${object.endereco.id}"/>

<div class="row">
    <div class="col-lg-2">
        <bootstrap:textfield label="CEP"
                             value="${object.cliente.endereco.cep}"
                             id="pessoa.endereco.cep"
                             errorField="endereco.cep"/>
    </div>
    <div class="col-lg-6">
        <bootstrap:textfield label="Logradouro"
                             value="${object.cliente.endereco.logradouro}"
                             id="pessoa.endereco.logradouro"
                             errorField="endereco.logradouro"/>
    </div>

    <div class="col-lg-2">
        <bootstrap:textfield label="Numero"
                             value="${object.cliente.endereco.numero}"
                             id="pessoa.endereco.numero"
                             errorField="endereco.numero"/>
    </div>
    <div class="col-lg-2">
        <bootstrap:textfield label="Complemento"
                             value="${object.cliente.endereco.complemento}"
                             id="pessoa.endereco.complemento"
                             errorField="endereco.complemento"/>
    </div>

</div>


<div class="row">

    <div class="col-lg-4">
        <input type="hidden" id="pessoa.endereco.estado" name="pessoa.endereco.estado"
               value="${object.cliente.endereco.estado.id}"/>
        <bootstrap:textfield label="Estado"
                             value="${object.cliente.endereco.estado.nome}"
                             id="pessoa.endereco.estado.nome"
                             errorField="endereco.estado.nome"/>
    </div>

    <div class="col-lg-4">
        <input type="hidden" id="pessoa.endereco.cidade" name="pessoa.endereco.cidade"
               value="${object.cliente.endereco.cidade.id}"/>
        <input type="hidden" id="pessoa.endereco.cidade.estado" name="pessoa.endereco.cidade.estado"
               value="${object.endereco.cidade.estado.id}"/>
        <bootstrap:textfield label="Cidade"
                             value="${object.cliente.endereco.cidade.nome}"
                             id="pessoa.endereco.cidade.nome"
                             errorField="endereco.cidade.nome"/>
    </div>

    <div class="col-lg-4">
        <input type="hidden" id="pessoa.endereco.bairro" name="pessoa.endereco.bairro"
               value="${object.cliente.endereco.bairro.id}"/>
        <input type="hidden" id="pessoa.endereco.bairro.cidade" name="pessoa.endereco.bairro.cidade"
               value="${object.endereco.bairro.cidade.id}"/>
        <bootstrap:textfield label="Bairro"
                             value="${object.cliente.endereco.bairro.nome}"
                             id="endereco.bairro.nome"
                             errorField="endereco.bairro.nome"/>
    </div>

</div>