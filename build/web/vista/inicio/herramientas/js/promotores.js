//var contextPath = "http://node16707-env-2670408.jelastic.saveincloud.net";
var contextPath = window.location.origin+"/ObservacionCIDHAL";

$(document).ready(function () {
    consultarPromotores();
    estilosTabla();
    $('select').material_select();
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
            $('select').material_select();
        },
        error: function (response) {
            console.log("error" + response);
        }
    });
}
function asignarIdInput(id) {
    document.getElementById('idUser').value = id;
}
function generarReportePromotor() {
    var idEncuesta = document.getElementById('encuestas').value;
    var idUsuario = document.getElementById('idUser').value;
    if (idEncuesta != 0) {
        if (idUsuario != 0) {
            $.ajax({
                type: 'POST',
                dataType: "json",
                url: 'reporteEntrevistasPromotorEncuestaJSON',
                data: "idEncuesta=" + idEncuesta + "&idUsuario=" + idUsuario,
                success: function (response) {
                    if (response.respuesta) {
                        location.href = contextPath + "/reporteEntrevistasPromotorEncuesta.action?idEncuesta=" + idEncuesta + "&idUsuario=" + idUsuario;
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
                    'No se han cargado los datos del promotor',
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

function consultarPromotores() {
//    $.ajax({
//        type: 'POST',
//        dataType: "json",
//        url: 'consultarPromotores',
//        data: "",
//        success: function (response) {
//            var tbody = "";
//            var i = 1;
//            $.each(response.promotores, function () {
//                var nombreCompleto = this.persona.nombre;
//                nombreCompleto += " " + this.persona.apellidoPaterno;
//                nombreCompleto += " " + this.persona.apellidoMaterno;
//
//                var checkar = this.estatus ? "checked" : "";
//                tbody +=
//                        "<tr>" +
//                        "<td>" + i + "</td>" +
//                        "<td>" + nombreCompleto + "</td>" +
//                        "<td>" + this.correo + "</td>" +
//                        "<td>" +
//                        "<div class='switch'>" +
//                        "<label>" +
//                        "<input type='checkbox' " + checkar + "  onclick='cambiarEstatusPromotor(" + this.id + ")'>" +
//                        "<span class='lever'></span>" +
//                        "</label>" +
//                        "</div>" +
//                        "</td>" +
//                        "</tr>";
//                i++;
//            });
//            $('#tbodypromotores').html(tbody);
//
//        },
//        error: function (response) {
//            console.log("error" + response);
//        }
//    });
}
function eliminarUsuario(id) {
    swal({
        title: '¿Quieres eliminar este promotor?',
        text: "Al eliminar este registro no habrá forma de recuperar los datos. \nToda la información relacionada con este promotor también será borrada.",
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
            url: 'eliminarUsuario',
            data: "id=" + id,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Promotor eliminado',
                            '',
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/goPromotores.action';", 1000);
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
function cambiarEstatusPromotor(id) {
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'cambiarEstatusPromotor',
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
function estilosTabla() {
    $('#data-table-simple').DataTable();
    $("select").val('10');
    $('select').addClass("browser-default");
    $('select').material_select();

}