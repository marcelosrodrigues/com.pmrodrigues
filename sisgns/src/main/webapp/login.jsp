<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bootstrap" uri="http://com.pmrodrigues.biz/taglib/bootstrap" %>
<fmt:setBundle basename="messages" scope="application" var="messages"/>
<fmt:requestEncoding value="ISO-8859-1"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message bundle="${messages}" key="sistema"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value='/css/plugins/metisMenu/metisMenu.min.css' />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/css/sb-admin-2.css' />" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/font-awesome-4.1.0/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-5 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <fmt:message bundle="${messages}" key="sistema"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <c:if test="${requestScope['SPRING_SECURITY_403_EXCEPTION']!=null}">
                        <div class="alert alert-danger alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button>
                                ${requestScope['SPRING_SECURITY_403_EXCEPTION']}
                        </div>
                    </c:if>
                    <form role="form" action="<c:url value='/j_spring_security_check' />" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <fieldset>
                            <bootstrap:email label="E-mail" value="${j_username}" id="j_username"
                                             errorField="j_username" showLabel="false"/>
                            <bootstrap:password label="Senha" value="" id="j_password" errorField="j_password"
                                                showLabel="false"/>

                            <!-- Change this goTo a button or input when using this as a form -->
                            <bootstrap:login label="Acessar"/>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="<c:url value='/js/jquery.js' />"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value='/js/bootstrap.min.js' />"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value='/js/plugins/metisMenu/metisMenu.min.js' />"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value='/js/sb-admin-2.js' />"></script>

</body>

</html>
