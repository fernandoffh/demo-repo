<%-- 
    Document   : menuLateral
    Created on : 19/05/2018, 07:44:43 PM
    Author     : alex
--%>
<%String context = request.getContextPath();%>   
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!DOCTYPE html>
<aside id="left-sidebar-nav">
    <ul class="side-nav fixed leftside-navigation" id="slide-out">
        <li class="user-details blue lighten-3">
            <div class="row">
                <div class="col col s4 m4 l4">
                    <img alt="" class="circle responsive-img valign profile-image" src="<%=context%>/vista/inicio/herramientas/img/logo-cidhal.png">
                    </img>
                </div>
                <div class="col col s8 m8 l8"  id='nombreUsuario'>

                </div>
            </div>
        </li>
        <li class="bold">
            <a class="collapsible-header waves-effect waves-cyan" href="<s:url action="goInicio.action"/>">
                <i class="large material-icons">assignment</i>
                Encuestas
            </a>
        </li>
        <li class="bold">
            <a href="<s:url action="goPromotores.action"/>">
                <i class="large material-icons">assignment_ind</i>
                Promotores
            </a>
        </li>
        <li class="bold">
            <a href="<s:url action="goEstados.action"/>">
                <i class="large material-icons">location_on</i>
                Estados
            </a>
        </li>
        <li class="li-hover">
            <div class="divider">
            </div>
        </li>
        <li class="li-hover">
            <a href="<s:url value="/vista/inicio/actualizarInformacion.jsp"/>">
                <i class="large material-icons">autorenew</i>
                Actualizar mis datos
            </a>
        </li>
        <li class="li-hover">
            <a href="<s:url value="/vista/inicio/cambiarMiContrasena.jsp"/>">
                <i class="large material-icons">lock_outline</i>
                Cambiar contraseña
            </a>
        </li>
        <li class="li-hover">
            <a href="<s:url action="cerrarSesion" />">
                <i class="large material-icons">arrow_back</i>
                Salir
            </a>
        </li>
    </ul>
    <a class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only darken-2" data-activates="slide-out" href="#">
<!--        <i class="mdi-navigation-menu">
        </i>-->
        <i class="large material-icons">menu</i>
    </a>
</aside>
