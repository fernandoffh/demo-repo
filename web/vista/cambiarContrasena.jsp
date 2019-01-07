<%-- 
    Document   : cambiarContrasena
    Created on : 19/05/2018, 08:17:32 AM
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%String context = request.getContextPath();%>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resgistro usuario</title>
        <!-- CORE CSS-->

        <link href="<%=context%>/vista/inicio/herramientas/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="<%=context%>/vista/inicio/herramientas/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="<%=context%>/vista/inicio/herramientas/css/page-center.css" type="text/css" rel="stylesheet" media="screen,projection">

        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href="<%=context%>/vista/inicio/herramientas/css/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">

        <link href="<%=context%>/vista/inicio/herramientas/css/sweetalert.all.css" type="text/css" rel="stylesheet" media="screen,projection">

    </head>
    <body class="cyan">


        <div id="login-page" class="row">
            <div class="col s12 z-depth-4 card-panel">
                <form class="login-form">
                    <div class="row">
                        <div class="input-field col s12 center">
                            <h4>Enviar código de recupación</h4>
                        </div>
                    </div>
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="mdi-social-person-outline prefix"></i>
                            <input id="codigoRecuperacion" type="text">
                            <label for="codigoRecuperacion" class="center-align">Código de recuperación</label>
                        </div>
                    </div>
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="mdi-communication-email prefix"></i>
                            <input id="contrasena" type="password">
                            <label for="contrasena" class="center-align">Contraseña</label>
                        </div>
                    </div>
                    <div class="row margin">
                        <div class="input-field col s12">
                            <i class="mdi-action-lock-outline prefix"></i>
                            <input id="contrasenaDos" type="password">
                            <label for="contrasenaDos">Repite tu contraseña:</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <a class="btn waves-effect waves-light col s12" onClick="cambiarContrasena()">Cambiar contraseña</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- ================================================
   Scripts
   ================================================ -->
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/jquery.min.js"></script>
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/sweetalert.all.js"></script>
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/usuario.js"></script>
        
        
        <!-- jQuery Library -->
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/jquery-1.11.2.min.js"></script>
        <!--materialize js-->
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/materialize.js"></script>
        <!--prism-->
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/prism.js"></script>
        <!--scrollbar-->
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <!--plugins.js - Some Specific JS codes for Plugin Settings-->
        <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/plugins.js"></script>
    </body>
</html>

