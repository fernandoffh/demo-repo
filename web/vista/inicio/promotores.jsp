<%-- 
    Document   : promotores
    Created on : 21/05/2018, 09:43:48 AM
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
        <%@ taglib prefix="s" uri="/struts-tags" %>

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
                    <div id="modal1" class="modal">
                        <div class="modal-content">
                            <div class="row">
                                <div class="col s12 m4 l3">
                                    <form action="action">
                                        <div class="row">
                                            <from>
                                                <input type="hidden" id="idUser" name="idUser"/>
                                                <div class="input-field col s12">
                                                    <select id="encuestas" name="encuestas">                                                        
                                                    </select>
                                                </div>
                                                <a class='waves-effect waves-light btn blue' onClick="generarReportePromotor()"><i class='material-icons'>send</i></a>
                                            </from>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a href="#!" class="modal-close waves-effect waves-green btn-flat" >Cancelar</a>
                        </div>
                    </div>
                    <div class="row">

                        <!--chart dashboard start-->
                        <div id="chart-dashboard">
                            <div class="row">
                                <div class="col s12 m8 l9">
                                    <table id="data-table-simple" class="responsive-table display" cellspacing="0" id="tablaEncuesta" name="tablaEncuesta">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Nombre</th>
                                                <th>Correo</th>
                                                <th>Estatus</th>
                                                <th>Reporte</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>#</th>
                                                <th>Nombre</th>
                                                <th>Correo</th>
                                                <th>Estatus</th>
                                                <th>Reporte</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </tfoot>
                                        <tbody id="tbodypromotores">
                                            <s:iterator value="promotores" var="f" status="stat">
                                                <tr id="<s:property value="#f.id"/>">
                                                    <td><s:property value="#stat.count"/></td>
                                                    <td><s:property value="#f.persona.nombre"/> <s:property value="#f.persona.apellidoPaterno"/>  <s:property value="#f.persona.apellidoMaterno"/> </td>
                                                    <td><s:property value="#f.correo"/></td>
                                                    <td>
                                                        <div class='switch'>
                                                            <label>
                                                                <input type='checkbox'  <s:if test="#f.estatus">checked</s:if> onClick='cambiarEstatusPromotor(<s:property value="#f.id"/>)'>
                                                                    <span class='lever'></span>
                                                                </label>
                                                            </div>
                                                        </td>

                                                        <td>
                                                            <a class='waves-effect waves-light btn blue modal-trigger'   href="#modal1" onClick="asignarIdInput(<s:property value="#f.id"/>)"><i class='material-icons'>description</i></a>
                                                        </td>
                                                        <td>
                                                            <a class='waves-effect waves-light btn red lighten-1' onClick='eliminarUsuario(<s:property value="#f.persona.id"/>)'><i class='material-icons'>delete</i></a>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div> 
                        <br>
                        <div class="divider"></div> 
                    </div>
                </div>>
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
    <script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/promotores.js"></script>
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
