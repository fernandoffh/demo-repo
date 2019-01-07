<%-- 
    Document   : administracionEstado
    Created on : 23/05/2018, 07:54:02 PM
    Author     : alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%String context = request.getContextPath();%>   
<%@ taglib prefix="s" uri="/struts-tags" %>

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
                    <div class="row">
                        <div class='row'>
                            <br>
                            <div class="col s4 center-align"><a class="btn-floating btn-large waves-effect waves-light pink darken-1 modal-trigger" href="#modal1"><i class="material-icons">add</i></a></div>
                        </div>
                        <div id="modal1" class="modal">
                            <div class="modal-content">
                                <div class="row">
                                    <div class="col s12 m4 l3">
                                        <form action="action">
                                            <div class="row">
                                                <input type="hidden" id="idEstado" name="idEstado"/> 
                                                <div class="input-field col s12">
                                                    <input id="nombre" type="text">
                                                    <label for="nombre" class="center-align">Nombre</label>
                                                </div>
                                                <div class="input-field col s12">
                                                    <input id="codigo" type="number">
                                                    <label for="codigo">Códgio</label>
                                                </div>
                                            </div>
                                            <div class="input-field col s12">
                                                <input id="cantidadDistritos" type="number">
                                                <label for="cantidadDistritos">Cantidad de distritos</label>
                                            </div>
                                            <div class="input-field col s12">
                                                <a id="btnGuardarCambios" class="btn waves-effect waves-light col s12" onClick="acualizarEstado()">Guardar cambios</a>
                                                <a id="btnRegistrar" class="btn waves-effect waves-light col s12" onClick="registrarEstado()">Registrar</a>
                                            </div>
                                                                                </form>

                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a href="#!" class="modal-close waves-effect waves-green btn-flat" onClick="recetModalEstado()">Cancelar</a>
                            </div>  
                        </div>

                    </div>
                    <!--chart dashboard start-->
                    <div id="chart-dashboard">
                        <div class="row">
                            <div class="col s12 m8 l9">
                                <table id="data-table-simple" class="responsive-table display" cellspacing="0" id="tablaEncuesta" name="tablaEncuesta">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nombre</th>
                                            <th>Codigo</th>
                                            <th>Cantidad de distritos</th>
                                            <th>Estatus</th>
                                            <th>Editar</th>
                                            <th>Eliminar</th>
                                            <th>Distritos</th>
                                            <th>Municipios</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>#</th>
                                            <th>Nombre</th>
                                            <th>Codigo</th>
                                            <th>Cantidad de distritos</th>
                                            <th>Estatus</th>
                                            <th>Editar</th>
                                            <th>Eliminar</th>
                                            <th>Distritos</th>
                                            <th>Municipios</th>
                                        </tr>
                                    </tfoot>
                                    <tbody id="tbodyestados">
                                        <s:iterator value="estados" var="f" status="stat">
                                            <tr id="<s:property value="#f.id"/>">
                                                <td><s:property value="#stat.count"/></td>
                                                <td><s:property value="#f.nombre"/></td>
                                                <td><s:property value="#f.codigo"/></td>
                                                <td><s:property value="#f.cantidadDistritos"/></td>
                                                <td>
                                                    <div class='switch'>
                                                        <label>
                                                            <input type='checkbox'  <s:if test="#f.estatus">checked</s:if> onClick="cambiarEstatusEstado(<s:property value="#f.id"/>)">
                                                                <span class='lever'></span>
                                                            </label>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a class='waves-effect waves-light btn light-blue accent-1' onClick='consultarUnEstado(<s:property value="#f.id"/>)'><i class='material-icons'>border_color</i></a>
                                                </td>
                                                <td>
                                                    <a class='waves-effect waves-light btn red lighten-1' onClick='eliminarUnEstado(<s:property value="#f.id"/>)'><i class='material-icons'>delete</i></a>
                                                </td>
                                                <td>
                                                    <a class='waves-effect waves-light btn teal lighten-2' href='distritosMunicipiosEstado.action?id=<s:property value="#f.id"/>'><i class='material-icons'>place</i></a>
                                                </td>
                                                <td>
                                                    <a class='waves-effect waves-light btn purple lighten-3'  href='consultarMunicipiosEstado.action?id=<s:property value="#f.id"/>'><i class='material-icons'>visibility</i></a>
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
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/session.js"></script>
<script type="text/javascript" src="<%=context%>/vista/inicio/herramientas/js/estado.js"></script>
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

