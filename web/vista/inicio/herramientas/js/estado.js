//var contextPath = "http://node16707-env-2670408.jelastic.saveincloud.net";
var contextPath = window.location.origin+"/ObservacionCIDHAL";

$(document).ready(function () {
//	consultarEstados();
    $('#btnGuardarCambios').hide();
    estilosTabla();
});
function registrarEstado() {
    var nombre = document.getElementById('nombre').value;
    var codigo = document.getElementById('codigo').value;
    var cantidadDistritos = document.getElementById('cantidadDistritos').value;
    if (isEmpty(nombre) == "no" || isEmpty(codigo) == "no" || isEmpty(cantidadDistritos) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        var datos = "";
        datos += "estado.nombre=" + nombre + "&";
        datos += "estado.codigo=" + codigo + "&";
        datos += "estado.cantidadDistritos=" + cantidadDistritos;
        $.ajax({
            type: 'POST',
            url: 'registrarEstado',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Registro correcto',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/goEstados.action';", 3000);

                } else {
                    swal(
                            'Registro incorrecto',
                            '' + response.mensaje,
                            'error'
                            );
                }
            },
            error: function (response, error) {
                console.log("error 2" + error);
                console.log("error 1" + response);
                console.log("datos: " + datos);
            }
        });
    }
}
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
function cambiarEstatusEstado(id) {
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'cambiarEstatusEstado',
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
function acualizarEstado() {
    var nombre = document.getElementById('nombre').value;
    var codigo = document.getElementById('codigo').value;
    var cantidadDistritos = document.getElementById('cantidadDistritos').value;
    var id = document.getElementById('idEstado').value;
    if (isEmpty(nombre) == "no" || isEmpty(codigo) == "no" || isEmpty(cantidadDistritos) == "no" || isEmpty(idEstado) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        var datos = "";
        datos += "estado.id=" + id + "&";
        datos += "estado.nombre=" + nombre + "&";
        datos += "estado.codigo=" + codigo + "&";
        datos += "estado.cantidadDistritos=" + cantidadDistritos;
        $.ajax({
            type: 'POST',
            url: 'actualizarEstado',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Actualización completada',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/goEstados.action';", 3000);

                } else {
                    swal(
                            'Ocurrio un error en el proceso',
                            '' + response.mensaje,
                            'error'
                            );
                }
            },
            error: function (response, error) {
                console.log("error 2" + error);
                console.log("error 1" + response);
                console.log("datos: " + datos);
            }
        });
    }
}
function eliminarUnEstado(id) {
    if (id != 0) {
        swal({
            title: '¿Quieres eliminar este estado?',
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
                url: 'eliminarEstado',
                data: "id=" + id,
                success: function (response) {
                    if (response.respuesta) {
                        swal(
                                'Encuesta eliminada',
                                '',
                                'success'
                                );
                        setTimeout("location.href ='" + contextPath + "/goEstados.action';", 1000);
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
    } else {
        swal(
                'No se puede eliminar este registro por el momento',
                '',
                'error'
                );
    }
}
function recetModalEstado() {
    $("#idEstado").val("");
    $("#nombre").val("");
    $("#codigo").val("");
    $("#cantidadDistritos").val("");
    $('#btnGuardarCambios').hide();
    $('#btnRegistrar').show();
}
function consultarUnEstado(id) {
    if (id != 0) {
        $.ajax({
            type: 'POST',
            url: 'consultarUnEstado',
            data: "id=" + id,
            success: function (response) {
                if (response.respuesta) {
                    var estado = response.estado;
                    document.getElementById('nombre').value = estado.nombre;
                    document.getElementById('codigo').value = estado.codigo;
                    document.getElementById('cantidadDistritos').value = estado.cantidadDistritos;
                    document.getElementById('idEstado').value = estado.id;
                    $('#btnRegistrar').hide();
                    $('#btnGuardarCambios').show();
                    $('#modal1').openModal();
                } else {
                    swal(
                            'No se pudieron cargar los datos',
                            '' + response.mensaje,
                            'error'
                            );
                }
            },
            error: function (response, error) {
                console.log("error 2" + error);
                console.log("error 1" + response);
            }
        });
    } else {
        swal(
                'Lo sentimos',
                'No se han cargado los datos',
                'error'
                );
    }
}

function recetModal() {
    document.getElementById('nombre').value = estado.nombre;
    document.getElementById('codigo').value = estado.codigo;
    document.getElementById('cantidadDistritos').value = estado.cantidadDistritos;
    document.getElementById('idEstado').value = estado.idEstado;
    $('#btnRegistrar').show();
    $('#btnGuardarCambios').hide();

}
function consultarEstados() {
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'consultarEstados',
        data: "",
        success: function (response) {
            var tbody = "";
            var i = 1;
            $.each(response.estados, function () {
                tbody +=
                        "<tr>" +
                        "<td>" + i + "</td>" +
                        "<td>" + this.nombre + "</td>" +
                        "<td>" + this.codigo + "</td>" +
                        "<td><a class='waves-effect waves-light btn blue' onclick='consultarUnEstado(" + this.id + ")'>Editar</a></td>" +
                        "<td><a class='waves-effect waves-light btn blue' onclick='eliminarUnEstado(" + this.id + ")'>Generar</a></td>" +
                        "<td><a class='waves-effect waves-light btn red' href='consultarMunicipiosEstado.action?id=" + this.id + "'>Ver</a></td>" +
                        "</tr>";
                i++;
            });
            $('#tbodyestados').html(tbody);

        },
        error: function (response) {
            console.log("error" + response);
        }
    });
}
function isEmpty(data) {
    if (data == "" || data == " " || data == "  " || data.length == 0) {
        return "no";
    } else {
        return $.trim(data);
    }
}