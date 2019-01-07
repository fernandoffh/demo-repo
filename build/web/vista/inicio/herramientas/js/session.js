//var contextPath = "http://node16707-env-2670408.jelastic.saveincloud.net";
var contextPath = window.location.origin+"/ObservacionCIDHAL";

$(document).ready(function () {
    cargarUsuarioSession();
});


function cargarUsuarioSession() {
    var nombreUsuario = document.getElementById('nombreUsuario');
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'cargarUsuarioSession',
        data: "",
        success: function (response) {
            if (response.respuesta) {
                var nombreCompleto = response.usuario.persona.nombre + " " + response.usuario.persona.apellidoPaterno + " " + response.usuario.persona.apellidoMaterno + "";
                var nombreUsuario = "<a class='btn-flat dropdown-button waves-effect waves-light white-text profile-btn'>" + nombreCompleto + "</a>'";
                $('#nombreUsuario').html(nombreUsuario);
            } else {
                swal(
                        'Sesión cerrada',
                        '' + response.mensaje,
                        'error'
                        );
                setTimeout("location.href ='" + contextPath + "/vista/login.jsp';", 2000);
            }
        },
        error: function (response) {
            console.log("error" + response);
        }
    });
}