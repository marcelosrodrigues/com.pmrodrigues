<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <%--<jsp:include page="search-formulario.jsp"/>--%>
            <!--<li>
                <a class="active" href="<c:url value='/index.do' />"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Relatórios<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="flot.html">Flot Charts</a>
                    </li>
                    <li>
                        <a href="morris.html">Morris.js Charts</a>
                    </li>
                </ul>

            </li>-->

                <li>
                    <a href="#"><i class="fa fa-dollar fa-fw"></i> Financeiro<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">

                            <li>
                                <a href="<c:url value='/boleto/index.do' />">Boletos</a>
                            </li>
                            <li>
                                <a href="<c:url value='/boletos/upload.do' />">Carregar arquivo de boletos</a>
                            </li>

                    </ul>
                </li>

            <li>
                <a href="#"><i class="fa fa-edit fa-fw"></i> Cadastro<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">

                    <li>
                        <a href="<c:url value='/cliente/index.do' />">Clientes</a>
                    </li>

                </ul>
            </li>

                <li>
                    <a href="#"><i class="fa fa-edit fa-fw"></i> Administração<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">

                        <li>
                            <a href="<c:url value='/administradora/index.do' />">Administradoras de plano de saúde</a>
                        </li>

                        <li>
                            <a href="<c:url value='/operadora/index.do' />">Operadoras de plano de saúde</a>
                        </li>

                    </ul>
                </li>

        </ul>
    </div>

</div>