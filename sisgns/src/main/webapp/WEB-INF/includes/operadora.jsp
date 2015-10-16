<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<input type="hidden" name="object.operadora" id="object.operadora" value="${object.operadora.id}"/>
<bootstrap:textfield label="Operadora" value="${object.operadora.nome}" id="object.operadora.nome"
                     errorField="nome"/>