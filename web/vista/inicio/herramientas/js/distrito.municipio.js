$(document).ready(function () {
//    estilosTabla();
    llenarSelect();
});

function llenarSelect() {
//    $.ajax({
//        type: 'POST',
//        dataType: "json",
//        url: 'consultarEncuestas',
//        success: function (response) {
//            $('select').material_select('destroy');
//            var option = "<option value='0'>Selecciona una opción</option>";
//            $.each(response.list, function () {
//                option += "<option value='" + this.id + "'>" + this.nombre + "</option>";
//            });
//            $('#encuestas').html(option);
//            $('#encuestas').material_select();
//        },
//        error: function (response) {
//            console.log("error" + response);
//        }
//    });
}

function reporteEncuestaDistrito(){
    var idEncuesta = document.getElementById('encuestas').value;
    var idDistrito = document.getElementById('distritos').value;
    var idEstado = document.getElementById('idEstado').value;
    if (idEncuesta != 0) {
        if (idDistrito != 0) {
            $.ajax({
                type: 'POST',
                dataType: "json",
                url: 'reporteEncuestaDistritoJSON',
                data: "idEncuesta=" + idEncuesta + "&idDistrito=" + idDistrito+"&idEstado="+idEstado,
                success: function (response) {
                    if (response.respuesta) {
                        location.href = contextPath + "/reporteEncuestaDistrito.action?idEncuesta=" + idEncuesta + "&idDistrito=" + idDistrito+"&idEstado="+idEstado;
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
    $('#distritos').material_select();
    $('#distritos').val('0');

}

function consultarMunicipiosAsignados() {
    var idDistrito = document.getElementById('distritos').value;
    var idEstado = document.getElementById('idEstado').value;
    if (idDistrito != 0) {
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'consultarMunicipiosAsignadosAdistritos',
            data: "idDistrito=" + idDistrito + "&id=" + idEstado,
            success: function (response) {
                $('#tbodymunicipios').html('');
                var tbody = "";
                var i = 1;
                $.each(response.municipios, function () {
                    var idd = this.idDistritoMunicipio;
                    var checkar = "<a class='waves-effect waves-light btn blue'  onClick='registrarDistritoMunicipio(" + this.id + ")'><i class='material-icons'>add</i></a>";
                    if (this.estatus) {
                        checkar = "<a class='waves-effect waves-light btn red'  onClick='eliminarDistritoMunicipio(" + idd + ")' ><i class='material-icons'>delete</i></a>";
                    }
                    tbody +=
                            "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + this.nombre + "</td>" +
                            "<td>" + checkar + "</td></td>" +
                            "</tr>";
                    i++;
                });
                $('#tbodymunicipios').html(tbody);

            },
            error: function (response) {
                console.log("error" + response);
            }
        });
    } else {
        $.ajax({
            type: 'POST',
            dataType: "json",
            url: 'consultarMunicipiosEstadoJSON',
            data: "id=" + idEstado,
            success: function (response) {
                $('#tbodymunicipios').html('');
                var tbody = "";
                var i = 1;
                $.each(response.municipios, function () {
                    tbody +=
                            "<tr>" +
                            "<td>" + i + "</td>" +
                            "<td>" + this.nombre + "</td>" +
                            "<td> *</td></td>" +
                            "</tr>";
                    i++;
                });
                $('#tbodymunicipios').html(tbody);

            },
            error: function (response) {
                console.log("error" + response);
            }
        });
    }

}

function eliminarDistritoMunicipio(id) {
    swal({
        title: '¿Quieres eliminar este municipio de este distrito?',
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
            url: 'eliminarDistritoMunicipio',
            data: "id=" + id,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Municipio eliminado del distrito',
                            '',
                            'success'
                            );
                    consultarMunicipiosAsignados();
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

function registrarDistritoMunicipio(idMunicipio) {
    var idDistrito = document.getElementById('distritos').value;
    if (idDistrito != 0) {
        swal({
            title: '¿Quieres registrar este municipio de este distrito?',
            text: "",
            icon: "warning",
            showCancelButton: true,
            showConfirmButton: true,
            confirmButtonColor: '#f06292 ',
            cancelButtonColor: '#64b5f6',
            confirmButtonText: 'Sí, registrar',
            cancelButtonText: 'Cancelar'
        }).then(function () {
            $.ajax({
                type: 'POST',
                dataType: "json",
                url: 'registrarDistritoMunicipio',
                data: "idMunicipio=" + idMunicipio + "&idDistrito=" + idDistrito,
                success: function (response) {
                    if (response.respuesta) {
                        swal(
                                'Municipio registrado en el distrito',
                                '' + response.mensaje,
                                'success'
                                );
                        consultarMunicipiosAsignados();
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
        });
    } else {

    }

}