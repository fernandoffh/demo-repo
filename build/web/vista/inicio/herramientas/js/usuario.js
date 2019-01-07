//var contextPath = "http://node16707-env-2670408.jelastic.saveincloud.net";
var contextPath = window.location.origin+"/ObservacionCIDHAL";


function registrarUsuario() {
    var nombre = document.getElementById('nombre').value;
    var apellidoPaterno = document.getElementById('apellidoPaterno').value;
    var apellidoMaterno = document.getElementById('apellidoMaterno').value;
    var correo = document.getElementById('correo').value;
    var contrasena = document.getElementById('contrasena').value;
    var contrasenaDos = document.getElementById('contrasenaDos').value;
    var codigoRegistro = document.getElementById('codigoRegistro').value;
    if (isEmpty(codigoRegistro) == "no" || isEmpty(nombre) == "no" || isEmpty(apellidoPaterno) == "no" || isEmpty(apellidoMaterno) == "no" || isEmpty(correo) == "no" || isEmpty(contrasena) == "no" || isEmpty(contrasenaDos) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        if (contrasena == contrasenaDos) {
            if (contrasena.length > 6) {
                var datos = "";
                datos += "usuario.persona.nombre=" + nombre + "&";
                datos += "usuario.persona.apellidoPaterno=" + apellidoPaterno + "&";
                datos += "usuario.persona.apellidoMaterno=" + apellidoMaterno + "&";
                datos += "usuario.correo=" + correo + "&";
                datos += "usuario.contrasena=" + contrasena + "&";
                datos += "codigoRegistro=" + codigoRegistro;
                $.ajax({
                    type: 'POST',
                    url: 'registrarUsuarioAdministrador',
                    data: datos,
                    success: function (response) {
                        if (response.respuesta) {
                            swal(
                                    'Registro correcto',
                                    '' + response.mensaje,
                                    'success'
                                    );
                            setTimeout("location.href ='" + contextPath + "/vista/login.jsp';", 3000);

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
            } else {
                swal(
                        'Tu contraseña debe ser mayor a 6 caracteres',
                        '',
                        'error'
                        );
            }
        } else {
            swal(
                    'Tus contraseñas no son iguales',
                    'Por favor verificalas',
                    'error'
                    );
        }
    }
}

function enviarCodigoRecuperacion() {
    var correo = document.getElementById('correo').value;
    if (isEmpty(correo) == "no") {
        swal(
                'Ingresa el correo',
                '',
                'error'
                );
    } else {
        var datos = "correo=" + correo;
        $.ajax({
            type: 'POST',
            url: 'enviarCodigoRecuperacion',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Solicitud realizada',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/vista/cambiarContrasena.jsp';", 3000);

                } else {
                    swal(
                            'Error en el correo',
                            '' + response.mensaje,
                            'error'
                            );
                }
            },
            error: function (response) {
                console.log("error" + response);
                console.log("datos: " + datos);
            }
        });
    }
}
function iniciarSesion() {
    var correo = document.getElementById('correo').value;
    var contrasena = document.getElementById('contrasena').value;
    if (isEmpty(contrasena) == "no" || isEmpty(correo) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        var datos = "usuario.correo=" + correo + "&usuario.contrasena=" + contrasena;
        $.ajax({
            type: 'POST',
            url: 'iniciarSesion',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Iniciando sesión... ',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='"+contextPath+"/goInicio.action';", 2000);
                } else {
                    swal(
                            'Lo sentimos',
                            '' + response.mensaje,
                            'warning'
                            );
                }
            },
            error: function (response) {
                console.log("error" + response);
                console.log("datos: " + datos);
            }
        });
    }
}
function cambiarMiContrasena() {
    var contrasenaNuevaUno = document.getElementById('contrasenaNuevaUno').value;
    var contrasenaNuevaDos = document.getElementById('contrasenaNuevaDos').value;
    var contrasenaAnterior = document.getElementById('contrasenaAnterior').value;
    if (isEmpty(contrasenaNuevaUno) == "no" || isEmpty(contrasenaNuevaDos) == "no" || isEmpty(contrasenaAnterior) == "no") {
        swal(
                'Todos los datos son necesarios',
                '',
                'error'
                );
    } else {
        if (contrasenaNuevaUno == contrasenaNuevaDos) {
            if (contrasenaNuevaUno.length > 6) {
                var datos = "contrasenaNueva=" + contrasenaNuevaUno + "&contrasenaAnterior=" + contrasenaAnterior;
                $.ajax({
                    type: 'POST',
                    url: 'cambiarMiContrasena',
                    data: datos,
                    success: function (response) {
                        if (response.respuesta) {
                            swal(
                                    'Nueva contraseña asignada',
                                    '',
                                    'success'
                                    );
                            setTimeout("location.href ='" + contextPath + "/goInicio.action';", 3000);

                        } else {
                            swal(
                                    'No se ha actualizado la contraseña',
                                    '' + response.mensaje,
                                    'error'
                                    );
                        }
                    },
                    error: function (response) {
                        console.log("error" + response);
                        console.log("datos: " + datos);
                    }
                });
            } else {
                swal(
                        'Tu contraseña debe ser mayor a 6 caracteres',
                        '',
                        'error'
                        );
            }
        } else {
            swal(
                    'Tus contraseñas no son iguales',
                    'Por favor verificalas',
                    'error'
                    );
        }
    }
}


function cambiarContrasena() {
    var codigoRecuperacion = document.getElementById('codigoRecuperacion').value;
    var contrasena = document.getElementById('contrasena').value;
    var contrasenaDos = document.getElementById('contrasenaDos').value;
    if (isEmpty(codigoRecuperacion) == "no" || isEmpty(contrasena) == "no" || isEmpty(contrasenaDos) == "no") {
        swal(
                'Todos los datos son necesarios',
                '',
                'error'
                );
    } else {
        if (contrasena == contrasenaDos) {
            if (contrasena.length > 6) {
                var datos = "codigoRecuperacion=" + codigoRecuperacion + "&contrasena=" + contrasena;
                $.ajax({
                    type: 'POST',
                    url: 'cambiarContrasena',
                    data: datos,
                    success: function (response) {
                        if (response.respuesta) {
                            swal(
                                    'Nueva contraseña asignada',
                                    '' + response.mensaje,
                                    'success'
                                    );
                            setTimeout("location.href ='" + contextPath + "/vista/login.jsp';", 3000);

                        } else {
                            swal(
                                    'No se ha actualizado la contraseña',
                                    '' + response.mensaje,
                                    'error'
                                    );
                        }
                    },
                    error: function (response) {
                        console.log("error" + response);
                        console.log("datos: " + datos);
                    }
                });
            } else {
                swal(
                        'Tu contraseña debe ser mayor a 6 caracteres',
                        '',
                        'error'
                        );
            }
        } else {
            swal(
                    'Tus contraseñas no son iguales',
                    'Por favor verificalas',
                    'error'
                    );
        }
    }
}
function actualizarDatosUsuario() {
    var nombre = document.getElementById('nombre').value;
    var apellidoPaterno = document.getElementById('apellidoPaterno').value;
    var apellidoMaterno = document.getElementById('apellidoMaterno').value;
    var correo = document.getElementById('correo').value;
    var contrasena = document.getElementById('contrasena').value;
    if (isEmpty(nombre) == "no" || isEmpty(apellidoPaterno) == "no" || isEmpty(apellidoMaterno) == "no" || isEmpty(correo) == "no" || isEmpty(contrasena) == "no") {
        swal(
                'Todos los datos son necesarios',
                'Por favor verifica que campos no estan completados',
                'error'
                );
    } else {
        var datos = "";
        datos += "usuario.persona.nombre=" + nombre + "&";
        datos += "usuario.persona.apellidoPaterno=" + apellidoPaterno + "&";
        datos += "usuario.persona.apellidoMaterno=" + apellidoMaterno + "&";
        datos += "usuario.correo=" + correo + "&";
        datos += "usuario.contrasena=" + contrasena + "";
        $.ajax({
            type: 'POST',
            url: 'actualizarDatosUsuario',
            data: datos,
            success: function (response) {
                if (response.respuesta) {
                    swal(
                            'Datos acualizados',
                            '' + response.mensaje,
                            'success'
                            );
                    setTimeout("location.href ='" + contextPath + "/goInicio.action';", 3000);
                    

                } else {
                    swal(
                            'No se guardaron los cambios',
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


function isEmpty(data) {
    if (data == "" || data == " " || data == "  " || data.length == 0) {
        return "no";
    } else {
        return $.trim(data);
    }
}

function idRol(rol) {
    var idRol = 0;
    $.ajax({
        type: 'POST',
        dataType: "json",
        url: 'obtenerIdRol',
        data: "rol=" + rol,
        success: function (response) {
            idRol = response.idRol;
        },
        error: function (response) {
            console.log("error" + response);
        }
    });
    return rol;
}


