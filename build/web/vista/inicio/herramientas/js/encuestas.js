//var contextPath = "http://node16707-env-2670408.jelastic.saveincloud.net";
var contextPath = window.location.origin+"/ObservacionCIDHAL";

$(document).ready(function () {
    consultarEncuestas();
    $('#btnGuardarCambios').hide();
    estilosTabla();
});
function estilosTabla() {
    $('#data-table-simple').DataTable();
    var espaniol = {
        "sProcessing": "Procesando...",
        "sLengthMenu": "Mostrar _MENU_ registros",
        "sZeroRecords": "No se encontraron resultados",
        "sEmptyTable": "Ningún dato disponible en esta tabla",
        "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
        "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
        "sInfoPostFix": "",
        "sSearch": "Buscar:",
        "sUrl": "",
        "sInfoThousands": ",",
        "sLoadingRecords": "Cargando...",
        "oPaginate": {
            "sFirst": "Primero",
            "sLast": "Último",
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
        },
        "oAria": {
            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
        }
    }
//    $('#data-table-simple').DataTable({
//        "language": espaniol
//    });

    $("select").val('10');
    $('select').addClass("browser-default");
    $('select').material_select();

}

function consultarEncuestas() {
    /* $.ajax({
     type: 'POST',
     dataType: "json",
     url: 'consultarEncuestas',
     data: "",
     success: function (response) {
     var tbody = "";
     var i = 1;
     $.each(response.list, function () {
     var checkar = "";
     if (this.estatus !== null) {
     checkar = this.estatus ? "checked" : "";
     }
     tbody +=
     "<tr>" +
     "<td>" + i + "</td>" +
     "<td>" + this.nombre + "</td>" +
     "<td>" + this.descripcion + "</td>" +
     "<td>" + this.fechaCreacion + "</td>" +
     "<td>" +
     "<div class='switch'>" +
     "<label>" +
     "<input type='checkbox' " + checkar + "  onclick='cambiarEstatusEncuesta(" + this.id + ")'>" +
     "<span class='lever'></span>" +
     "</label>" +
     "</div>" +
     "</td>" +
     "<td><a class='waves-effect waves-light btn light-blue accent-1' onclick='consultarUnaEncuesta(" + this.id + ")'><i class='material-icons'>border_color</i></a></td>'" +
     "<td><a class='waves-effect waves-light btn blue'  href='reporteEntrevistasEstado.action?id=" + this.id + "'><i class='material-icons'>description</i></a></td>" +
     "<td><a class='waves-effect waves-light btn purple lighten-3'  href='consultarEntrevistasDeEncuesta.action?id=" + this.id + "'><i class='material-icons'>visibility</i></a></td></td>" +
     "</tr>";
     i++;
     });
     $('#tbodyencuestas').html(tbody);
     
     },
     error: function (response) {
     console.log("error" + response);
     }
     });*/
}


function cambiarEstatusEncuesta(id) {
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'cambiarEstatusEncuesta',
        data: "id=" + id,
        success: function (response) {
            if (!response.respuesta) {
                swal(
                        'Lo sentimos',
                        'Ocurrio un error en la acción',
                        'error'
                        );
            }
        },
        error: function (response) {
            console.log("error" + response);
        }
    });
}
function consultarUnaEncuesta(id) {
    swal({
        title: '¿Quieres modificar este registro?',
        text: "Esta opción permite hacer cambios en los datos",
        icon: "warning",
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonColor: '#f06292 ',
        cancelButtonColor: '#64b5f6',
        confirmButtonText: 'Continuar',
        cancelButtonText: 'Cancelar'
    }).then(function () {
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'consultarUnaEncuesta',
            data: "id=" + id,
            success: function (response) {
                if (response.respuesta) {
                    $("#idEncuesta").val(response.encuesta.id);
                    $("#nombre").val(response.encuesta.nombre);
                    $("#descripcion").val(response.encuesta.descripcion);
                    $('#btnGuardarCambios').show();
                    $('#btnRegistrar').hide();
                    $('#modal1').openModal();
                } else {
                    swal(
                            'Lo sentimos',
                            'Ocurrio un error en la acción',
                            'error'
                            );
                }
            },
            error: function (response) {
                console.log("error" + response);
            }
        });
    });
}

function acualizarEncuesta() {
    var id = document.getElementById('idEncuesta').value;
    var nombre = document.getElementById('nombre').value;
    var descripcion = document.getElementById('descripcion').value;
    if (isEmpty(nombre) == "no" || isEmpty(descripcion) == "no") {
        swal(
                'Todos los campos con necesarios',
                '',
                'warning'
                );
    } else {
        var datos = "encuesta.id=" + id + "&encuesta.nombre=" + nombre + "&encuesta.descripcion=" + descripcion;
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'acualizarEncuesta',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    consultarEncuestas();
                    swal(
                            'Cambios guardados',
                            '' + response.mensaje,
                            'success'
                            );
                    recetModal();
                    setTimeout("location.href ='" + contextPath + "/goInicio.action';", 2000);
                } else {
                    swal(
                            'Lo sentimos',
                            '' + response.mensaje,
                            'error'
                            );
                }
            },
            error: function (response) {
                console.log("error" + response);
            }
        });
    }

}
function recetModal() {
    $("#idEncuesta").val("");
    $("#nombre").val("");
    $("#descripcion").val("");
    $('#btnGuardarCambios').hide();
    $('#btnRegistrar').show();
}

function eliminarEncuesta(id) {
    swal({
        title: '¿Quieres eliminar esta encuesta?',
        text: "Al eliminar este registro no habrá forma de recuperar los datos. \nToda la información relacionada con este registro también será borrada.",
        icon: "warning",
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonColor: '#f06292 ',
        cancelButtonColor: '#64b5f6',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then(function () {
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'eliminarEncuesta',
            data: "id=" + id,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Encuesta eliminada',
                            '',
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/goInicio.action';", 1000);
                } else {
                    swal(
                            'Lo sentimos',
                            'Ocurrio un error en la acción',
                            'error'
                            );
                }
            },
            error: function (response) {
                console.log("error" + response);
            }
        });
    });
}

function registrarEncuesta() {
    var nombre = document.getElementById('nombre').value;
    var descripcion = document.getElementById('descripcion').value;
    if (isEmpty(nombre) == "no" || isEmpty(descripcion) == "no") {
        swal(
                'Todos los campos con necesarios',
                '',
                'warning'
                );
    } else {
        var datos = "encuesta.nombre=" + nombre + "&encuesta.descripcion=" + descripcion;
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'registarEncuesta',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    consultarEncuestas();
                    swal(
                            'Encuesta registrada',
                            '' + response.mensaje,
                            'success'
                            );
                    $("#idEncuesta").val("");
                    $("#nombre").val("");
                    $("#descripcion").val("");
                    setTimeout("location.href ='" + contextPath + "/goInicio.action';", 2000);
                } else {
                    swal(
                            'Lo sentimos',
                            '' + response.mensaje,
                            'error'
                            );
                }
            },
            error: function (response) {
                console.log("error" + response);
            }
        });
    }
}

function isEmpty(data) {
    if (data == "" || data == " " || data == "  " || data.length == 0) {
        return "no";
    } else {
        return $.trim(data);
    }
}

