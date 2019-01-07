<%-- 
    Document   : cambiarMiContrasena
    Created on : 21/05/2018, 12:21:34 PM
    Author     : CDS-
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String context = request.getContextPath();%>   

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <title>
            Observación CIDHAL Manager
        </title>
        <!-- CORE CSS-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="<%=context%>/vista/inicio/herramientas/css/materialize.css" media="screen,projection" rel="stylesheet" type="text/css">
        <link href="<%=context%>/vista/inicio/herramientas/css/style.css" media="screen,projection" rel="stylesheet" type="text/css">
        <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/perfect-scrollbar/perfect-scrollbar.css" media="screen,projection" rel="stylesheet" type="text/css">
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/jvectormap/jquery-jvectormap.css" media="screen,projection" rel="stylesheet" type="text/css">
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/chartist-js/chartist.min.css" media="screen,projection" rel="stylesheet" type="text/css">
        <link href="<%=context%>/vista/inicio/herramientas/css/sweetalert.all.css" type="text/css" rel="stylesheet" media="screen,projection">

        <!--        tablas -->
        <link href="<%=context%>/vista/inicio/herramientas/css/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/data-tables/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet" media="screen,projection">
        <link href="<%=context%>/vista/inicio/herramientas/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
        <!--fin tablas-->

    </head>
    <body>
        <!-- START HEADER -->
        <jsp:include page="/vista/menu/menuSuperior.jsp" flush="true" />

        <!-- END HEADER -->

        <!-- START MAIN -->
        <div id="main">
            <!-- START WRAPPER -->
            <div class="wrapper">
                <!-- START LEFT SIDEBAR NAV-->
                <jsp:include page="/vista/menu/menuLateral.jsp" flush="true" />
                <!-- END LEFT SIDEBAR NAV-->
                <!-- //////////////////////////////////////////////////////////////////////////// -->
                <!-- START CONTENT -->
                <!--start container-->
                <div class="container">
                    <h4 class="header">Actualizar mi información</h4>
                    <div class="row">
                        <div class="col s12 m4 l3">
                        </div>
                        <div class="col s12 m8 l9">
                            <form class="col s12">
                                <div class="row">
                                    <div class="input-field col s6 center">
                                        <input id="contrasenaNuevaUno" type="password" placeholder="Contraseña nueva" class="validate">
                                        <label for="contrasenaNuevaUno">Intrega la nueva contraseña</label>
                                    </div>
                                    <div class="input-field col s6 center">
                                        <input id="contrasenaNuevaDos" type="password" placeholder="Ingresa de nuevo la contraseña" class="validate">
                                        <label for="contrasenaNuevaDos">Repite la contraseña</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s3 center">
                                    </div>
                                    <div class="input-field col s6 center">
                                        <input id="contrasenaAnterior" type="password" placeholder="contraseña anterior" class="validate">
                                        <label for="contrasenaAnterior">Ingresa tu anterior contraseña</label>
                                    </div>
                                    <div class="input-field col s3 center">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s4">
                                    </div>
                                    <div class="input-field col s4 center">
                                        <a class="btn waves-effect waves-light "  onClick="cambiarMiContrasena()">Guardar cambios
                                            <i class="mdi-content-send right"></i>
                                        </a>
                                    </div>
                                    <div class="input-field col s4">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--end container-->
        <!-- END CONTENT -->
    </div>
    <!-- END WRAPPER -->
</div>
<!-- END MAIN -->
<!-- START FOOTER -->
<!-- END FOOTER -->
<!-- ================================================
Scripts
================================================ -->
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/sweetalert.all.js"></script>
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/usuario.js"></script>
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/session.js"></script>
<!-- jQuery Library -->
<script src="<%=context%>/vista/inicio/herramientas/js/jquery-1.11.2.min.js" type="text/javascript">
</script>
<!--materialize js-->
<script src="<%=context%>/vista/inicio/herramientas/js/materialize.min.js" type="text/javascript">
</script>
<!--scrollbar-->
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js" type="text/javascript">
</script>
<!-- chartist -->
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/chartist-js/chartist.min.js" type="text/javascript">
</script>
<!-- chartjs -->
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/chartjs/chart.min.js" type="text/javascript">
</script>
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/chartjs/chart-script.js" type="text/javascript">
</script>
<!-- sparkline -->
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript">
</script>
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/sparkline/sparkline-script.js" type="text/javascript">
</script>
<!--jvectormap-->
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript">
</script>
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript">
</script>
<script src="<%=context%>/vista/inicio/herramientas/js/plugins/jvectormap/vectormap-script.js" type="text/javascript">
</script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script src="<%=context%>/vista/inicio/herramientas/js/plugins.js" type="text/javascript">
</script>

<!--scrollbar-->
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<!-- data-tables -->
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/plugins/data-tables/data-tables-script.js"></script>


</body>
</html>
