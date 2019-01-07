<%-- 
    Document   : menuSuperior
    Created on : 19/05/2018, 07:54:31 PM
    Author     : alex
--%>
<%String context = request.getContextPath();%>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags" %>
              <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<header class="page-topbar" id="header">
    <!-- start header nav-->
    <div class="navbar-fixed">
        <nav class="pink lighten-3">
            <div class="nav-wrapper">
                <h1 class="logo-wrapper">
                    <a class="brand-logo darken-1" href="index.html">
                        <img alt="" src="<%=context%>/vista/inicio/herramientas/images/fepade.png"/>
                    </a>
                    <span class="logo-text">
                        Observación CIDHAL
                    </span>
                </h1>
                <ul class="right hide-on-med-and-down">

                    <li>
                        <a class="waves-effect waves-block waves-light show-search" href="<s:url action="cerrarSesion" />">
                           Salir
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<!-- end header nav-->
</header>