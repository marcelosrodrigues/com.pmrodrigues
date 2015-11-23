<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<div class="panel panel-default panel-body">
    <h4>Titular</h4>

    <div class="row">
        <div class="col-lg-7">
            <bootstrap:textfield label="Nome" value="" id="beneficiario.nome" errorField=""/>
        </div>
        <div class="col-lg-3">
            <bootstrap:date label="Data de Nascimento" value="" id="beneficiario.dataNascimento" errorField=""
                            pattern="dd/MM/yyyy"/>
        </div>
    </div>

    <div class="panel panel-default panel-body">
        <h4>Dependentes</h4>

        <div class="row">
            <div class="col-lg-8">
                <bootstrap:textfield label="Nome" value="" id="dependente.nome" errorField=""/>
            </div>
            <div class="col-lg-3">
                <bootstrap:date label="Data de Nascimento" value="" id="dependente.dataNascimento" errorField=""
                                pattern="dd/MM/yyyy"/>
            </div>
        </div>
        <div class="form-group">
            <label>Grau de Parentesco</label>
            <label class="radio-inline">
                <input type="radio" name="grauparentesco" id="dependente.grauparentesco" value="PF" checked="">Conjugue
            </label>
            <label class="radio-inline">
                <input type="radio" name="grauparentesco" id="dependente.grauparentesco" value="PJ">Filho
            </label>
        </div>
        <div class="form-group">
            <button id="adicionar-dependente" class="btn btn-success" type="button">
                <i class="fa fa-plus"></i> Adicionar
            </button>
        </div>

        <hr/>
        <div id="dependentes">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Data de Nascimento</th>
                    <th>Grau de Parentesco</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Nome</td>
                    <td>Data de Nascimento</td>
                    <td>Grau de Parentesco</td>
                    <td width="5%">
                        <button onclick="javascript:removerTR(this)" type="button"
                                class="btn btn-danger btn-circle">
                            <i class="fa fa-times"></i></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>

    <button id="adicionar-cliente" class="btn btn-success"
            type="button">
        <i class="fa fa-plus"></i> Adicionar
    </button>

</div>

<hr/>
<div id="beneficiarios">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Data de Nascimento</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Nome</td>
            <td>Data de Nascimento</td>
            <td width="5%">
                <button onclick="javascript:removerTR(this)" type="button"
                        class="btn btn-danger btn-circle">
                    <i class="fa fa-times"></i></button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
