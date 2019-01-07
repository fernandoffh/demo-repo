/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author alex
 */
public class Herramientas {

//    palabras estaticas del sistema
    public static final String PROMOTOR = "promotor";
    public static final String SESSION_USUARIO = "sessionUsuario";
    public static final String CODIGO_UNICO = "980216";
    public static final String CORREO = "observacioncidhal@gmail.com";
    public static final String CONTRASENA = "domingo10062018";

//    procedimientos almacenados 
    public static final String REGISTRAR_USUARIO_ADMINISTRADOR = "{call registrar_usuario_administrador(?,?,?,?,?)}";
    public static final String ACTUALIZAR_DATOS_USUARIO = "{call actualizar_datos_usuario(?,?,?,?,?)}";
    public static final String REGISTRAR_PROMOTOR = "{call registrar_promotor(?,?,?,?,?)}";
    public static final String CORREO_DISPONIBLE = "{call correo_disponible(?)}";
    public static final String ASIGNAR_CODIGO_RECUPERACION = "{call asignar_codigo_recuperacion(?,?)}";
    public static final String EXISTE_CODIGO_RECUPERACION = "{call existe_codigo_recuperacion(?)}";
    public static final String CAMBIAR_CONTRASENA = "{call cambiar_contrasena(?,?)}";
    public static final String INICIAR_SESION = "{call iniciar_sesion(?,?)}";
    public static final String REGISTRAR_ENCUESTA = "{call registrar_encuesta(?,?)}";
    public static final String CAMBIAR_ESTATUS_ENCUESTA = "{call cambiar_estatus_encuesta(?,?)}";
    public static final String CAMBIAR_MI_CONTRASENA = "{call cambiar_mi_contrasena(?,?)}";
    public static final String CAMBIAR_CONTRASENA_APP = "{call cambiar_contrasena_app(?,?)}";
    public static final String CAMBIAR_ESTATUS_PROMOTOR = "{call cambiar_estatus_promotor(?,?)}";
    public static final String CONSULTAR_UNA_ENCUESTA = "{call consultar_una_encuesta(?)}";
    public static final String ACTUALIZAR_ENCUESTA = "{call actualizar_encuesta(?,?,?)}";
    public static final String CONSULTAR_ESTATUS_ENCUESTA = "{call consultar_estatus_encuesta(?)}";
    public static final String REGISTRAR_ENTREVISTA = "{call registrar_entrevista(?,?,?,?,?,?,?,?,?)}";
    public static final String REGISTRAR_RESPUESTA = "{call registrar_respuesta(?,?,?)}";
    public static final String REGISTRAR_PREGUNTA = "{call registrar_pregunta(?,?,?,?,?)}";
    public static final String CONSULTAR_GENERAL_ENCUESTA = "{call consulta_general_encuesta(?)}";
    public static final String SUB_CONSULTAR_GENERAL_ENCUESTA = "{call sub_consulta_general_encuesta(?)}";
    public static final String CONSULTA_GENERAL_UNA_ENTREVISTA = "{call consulta_general_una_entrevista(?)}";
    public static final String CONSULTAR_ESTADO = "{call consultar_estados()}";
    public static final String CONSULTAR_PROMOTORES = "{call consultar_promotores()}";
    public static final String CONSULTAR_ENTREVISTAS_ENCUESTA = "{call consultar_entrevistas_encuesta(?)}";
    public static final String CONSULTAR_ESTATUS_PROMOTOR = "{call consultar_estatus_promotor(?)}";
    public static final String CONSULTAR_MUNICIPIOS_ESTADO = "{call consultar_municipios_estado(?)}";
    public static final String CONSULTAR_PREGUNTAS_ENCUESTA = "{call consultar_preguntas_encuesta(?)}";
    public static final String CONSULTAR_OPCIONES_PREGUNTA = "{call consultar_opciones_pregunta(?)}";
    public static final String REGISTRAR_ESTADO = "{call registrar_estado(?, ?, ?)}";
    public static final String CONSULTAR_ENCUESTAS = "SELECT * FROM encuesta";
    public static final String CONSULTAR_ENCUESTAS_ACTIVAS = "SELECT * FROM encuesta WHERE estatus = 1;";
    public static final String ELIMINAR_ENCUESTA = "DELETE FROM encuesta WHERE id = ?;";
    public static final String ELIMINAR_USUARIO = "DELETE FROM persona WHERE id = ?;";
    public static final String PREGUNTA_CERRADA = "cerrada";
    public static final String PREGUNTA_ABIERTA= "abierta";

//    metodos estaticos para el sistema
    public static String cadenaRandom() {
        return "" + (int) (Math.random() * 99999) + 1;
    }
}
