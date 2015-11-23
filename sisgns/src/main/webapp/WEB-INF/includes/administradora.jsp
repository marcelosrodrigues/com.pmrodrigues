<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>

<input type="hidden" name="object.administradora" id="object.administradora"
       value="${object.administradora.id}"/>
<bootstrap:textfield label="Administradora" value="${object.administradora.nome}"
                     id="object.administradora.nome" errorField="object.administradora"/>