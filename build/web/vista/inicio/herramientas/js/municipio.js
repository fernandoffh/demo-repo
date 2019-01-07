//var contextPath = "http://node16707-env-2670408.jelastic.saveincloud.net";
var contextPath = window.location.origin+"/ObservacionCIDHAL";

$(document).ready(function () {
//	consultarEstados();
    $('#btnGuardarCambios').hide();
    estilosTabla();
    llenarSelect();
});

function llenarSelect() {
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'consultarEncuestas',
        success: function (response) {
            $('select').material_select('destroy');
            var option = "<option value='0'>Selecciona una opción</option>";
            $.each(response.list, function () {
                option += "<option value='" + this.id + "'>" + this.nombre + "</option>";
            });
            $('#encuestas').html(option);
            $('#encuestas').material_select();
        },
        error: function (response) {
            console.log("error" + response);
        }
    });
}

function asginaridInput(idMunicipio) {
    $("#idMunicipio").val(idMunicipio);
}

function reporteEncuestaMunicipio() {
    var idEncuesta = document.getElementById('encuestas').value;
    var idMunicipio = document.getElementById('idMunicipio').value;
    if (idEncuesta !== 0) {
        if (idMunicipio !== 0) {
            $.ajax({
                type: 'POST',
                dataType: "json",
                url: 'reporteEncuestaMunicipioJSON',
                data: "idEncuesta=" + idEncuesta + "&idMunicipio=" + idMunicipio,
                success: function (response) {
                    if (response.respuesta) {
                        location.href = contextPath + "/reporteEncuestaMunicipio.action?idEncuesta=" + idEncuesta + "&idMunicipio=" + idMunicipio;
                    } else {
                        swal(
                                'Lo sentimos',
                                'No hay registros para este reporte',
                                'error'
                                );
                    }
                },
                error: function (response) {
                    console.log("error" + response);
                }
            });
        } else {
            swal(
                    'No se han cargado los datos del municipio',
                    '',
                    'error'
                    );
        }
    } else {
        swal(
                'Seleciona una encuesta',
                '',
                'error'
                );
    }
}
function registrarMunicipio() {
    var nombre = document.getElementById('nombre').value;
    var codigo = document.getElementById('codigo').value;
    var idEstado = document.getElementById('idEstado').value;
    if (isEmpty(nombre) == "no" || isEmpty(codigo) == "no" || isEmpty(idEstado) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        var datos = "";
        datos += "municipio.nombre=" + nombre + "&";
        datos += "municipio.codigo=" + codigo + "&";
        datos += "municipio.estado.id=" + idEstado;
        $.ajax({
            type: 'POST',
            url: 'registrarMunicipio',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Registro correcto',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/consultarMunicipiosEstado.action?id=" + idEstado + "';", 2000);
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
function cambiarEstatusMunicipio(id) {
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'cambiarEstatusMunicipio',
        data: "id=" + id,
        success: function (response) {
            if (!response.respuesta) {
                swal(
                        'Lo sentimos',
                        'Ocurrio un error en la acción ' + response.mensaje,
                        'error'
                        );
            }
        },
        error: function (response) {
            console.log("error" + response);
        }
    });
}
function acualizarMunicipio() {
    var nombre = document.getElementById('nombre').value;
    var codigo = document.getElementById('codigo').value;
    var id = document.getElementById('idMunicipio').value;
    var idEstado = document.getElementById('idEstado').value;
    if (isEmpty(nombre) == "no" || isEmpty(codigo) == "no" || isEmpty(id) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        var datos = "";
        datos += "municipio.id=" + id + "&";
        datos += "municipio.nombre=" + nombre + "&";
        datos += "municipio.codigo=" + codigo;
        $.ajax({
            type: 'POST',
            url: 'acualizarMunicipio',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Actualización completada',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/consultarMunicipiosEstado.action?id=" + idEstado + "';", 2000);

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
function eliminarMunicipio(id) {
    var idEstado = document.getElementById('idEstado').value;
    if (id != 0) {
        swal({
            title: '¿Quieres eliminar este municipio?',
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
                url: 'eliminarMunicipio',
                data: "id=" + id,
                success: function (response) {
                    if (response.respuesta) {
                        swal(
                                'Municipio eliminado',
                                '',
                                'success'
                                );
                        setTimeout("location.href ='" + contextPath + "/consultarMunicipiosEstado.action?id=" + idEstado + "';", 2000);
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
function recetModalMunicipio() {
    $("#idMunicipio").val("");
    $("#nombre").val("");
    $("#codigo").val("");
    $('#btnGuardarCambios').hide();
    $('#btnRegistrar').show();
}
function consultarMunicipio(id) {
    if (id != 0) {
        $.ajax({
            type: 'POST',
            url: 'consultarMunicipio',
            data: "id=" + id,
            success: function (response) {
                if (response.respuesta) {
                    var municipio = response.municipio;
                    document.getElementById('nombre').value = municipio.nombre;
                    document.getElementById('codigo').value = municipio.codigo;
                    document.getElementById('idMunicipio').value = municipio.id;
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


function isEmpty(data) {
    if (data == "" || data == " " || data == "  " || data.length == 0) {
        return "no";
    } else {
        return $.trim(data);
    }
}