$(document).ready(function(){
	$.ajax({
        type: 'POST',
        url: '/cargarUsuarioSession',
        data: "",
        success: function (response) {
            if (response.respuesta) {
                var per = response.usuario.persona;
                document.getElementById('nombre').value = per.nombre;
                document.getElementById('apellidoPaterno').value = per.apellidoPaterno;
                document.getElementById('apellidoMaterno').value = per.apellidoMaterno;
                document.getElementById('correo').value = response.usuario.correo;
            }
        },
        error: function (response, error) {
            console.log("error 2" + error);
        }
    });
});