/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.reporte;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.encuesta.DaoEncuesta;
import modelo.usuario.DaoUsuario;
import static org.apache.struts2.ServletActionContext.getServletContext;
import utilidades.ConexionBD;

/**
 *
 * @author alex
 */
public class ControlReporte extends ActionSupport {

    private static String ID_ENCUESTA = "idEncuesta";
    private static String ID_MUNICIPIO = "idMunicipio";
    private static String ID_DISTRITO = "idDistrito";
    private static String ID_ESTADO = "idEstado";
    private static String ID_ENCUESTA_2 = "id_encuesta";
    private static String ID_USUARIO = "id_usuario";
    private static String NOTREPORT = "notReport";
    private List mySource;
    private boolean respuesta;
    private Connection conexion;
    private Map parametro;
    private int id;
    private int idUsuario;
    private int idEncuesta;
    private int idMunicipio;
    private int idDistrito;
    private int idEstado;
    private String nombreReporte;
    private static final DaoEncuesta DAO = new DaoEncuesta();

    public String reporteEntrevistasEstado() throws SQLException {
        mySource = new ArrayList();
        parametro = new HashMap();
        parametro.put(ID_ENCUESTA, id);
        nombreReporte = "Reporte de encuesta.xls";
        conexion = ConexionBD.getConexion();
        return SUCCESS;
    }

    public String reporteEncuestaMunicipio() throws SQLException {
        mySource = new ArrayList();
        parametro = new HashMap();
        parametro.put(ID_ENCUESTA, idEncuesta);
        parametro.put(ID_MUNICIPIO, idMunicipio);
        nombreReporte = "Reporte por municipio.xls";
        conexion = ConexionBD.getConexion();
        return SUCCESS;
    }
    public String reporteEncuestaDistrito() throws SQLException {
        mySource = new ArrayList();
        parametro = new HashMap();
        parametro.put(ID_ENCUESTA, idEncuesta);
        parametro.put(ID_ESTADO, idEstado);
        parametro.put(ID_DISTRITO, idDistrito);
        nombreReporte = "Reporte por distrito.xls";
        conexion = ConexionBD.getConexion();
        return SUCCESS;
    }

    public String reporteEntrevistasEstadoJSON() {
        String consulta = "SELECT  \n"
                + "	encuesta.nombre as nombre_encuesta,  \n"
                + "    encuesta.descripcion as descripcion_encuesta, \n"
                + "	entrevista.*, \n"
                + "	municipio.nombre AS nombre_municipio, \n"
                + "	municipio.codigo AS codigo_municipio, \n"
                + "    estado.nombre as nombre_estado, \n"
                + "	distrito.codigo AS codigo_distrito, \n"
                + "	persona.nombre AS nombre_promotor, \n"
                + "	persona.apellido_paterno AS apellido_paterno_promotor, \n"
                + "	persona.apellido_materno AS apellido_materno_promotor \n"
                + "FROM entrevista \n"
                + "	inner join distrito_municipio ON  \n"
                + "	 distrito_municipio.id = entrevista.distrito_municipio_id  \n"
                + "	inner join municipio ON  \n"
                + "	 distrito_municipio.municipio_id = municipio.id \n"
                + "     inner join estado	ON \n"
                + "     estado.id = municipio.estado_id \n"
                + "	INNER JOIN distrito ON \n"
                + "	 distrito_municipio.distrito_id = distrito.id \n"
                + "	inner join usuario ON \n"
                + "	 entrevista.usuario_id = usuario.id \n"
                + "	inner join persona ON \n"
                + "	 persona.id = usuario.persona_id \n"
                + "	 inner join encuesta \n"
                + "on entrevista.encuesta_id = encuesta.id \n"
                + "where entrevista.encuesta_id =" + id + ";";
        respuesta = DAO.existeSentencia(consulta);
        System.out.println(consulta);
        return SUCCESS;
    }

    public String reporteEncuestaDistritoJSON() {
        String consulta = "SELECT  \n"
                + "	encuesta.nombre as nombre_encuesta,  \n"
                + "    encuesta.descripcion as descripcion_encuesta, \n"
                + "	entrevista.*, \n"
                + "	municipio.nombre AS nombre_municipio, \n"
                + "	municipio.codigo AS codigo_municipio, \n"
                + "    estado.nombre as nombre_estado, \n"
                + "	distrito.codigo AS codigo_distrito, \n"
                + "	persona.nombre AS nombre_promotor, \n"
                + "	persona.apellido_paterno AS apellido_paterno_promotor, \n"
                + "	persona.apellido_materno AS apellido_materno_promotor \n"
                + "FROM entrevista \n"
                + "	inner join distrito_municipio ON \n"
                + "	 distrito_municipio.id = entrevista.distrito_municipio_id \n"
                + "	inner join municipio ON \n"
                + "	 distrito_municipio.municipio_id = municipio.id \n"
                + "     inner join estado	ON \n"
                + "     estado.id = municipio.estado_id\n"
                + "	INNER JOIN distrito ON  \n"
                + "	 distrito_municipio.distrito_id = distrito.id \n"
                + "	inner join usuario ON  \n"
                + "	 entrevista.usuario_id = usuario.id  \n"
                + "	inner join persona ON  \n"
                + "	 persona.id = usuario.persona_id \n"
                + "	 inner join encuesta \n"
                + "on entrevista.encuesta_id = encuesta.id \n"
                + "where entrevista.encuesta_id =  "+idEncuesta+" \n"
                + " and distrito.id  = "+idDistrito+" \n"
                + "  AND municipio.estado_id =  "+idEstado+";";
        respuesta = DAO.existeSentencia(consulta);
        System.out.println(consulta);
        return SUCCESS;
    }

    public String reporteEntrevistasPromotorEncuestaJSON() {
        String consulta
                = "SELECT encuesta.nombre AS nombre_encuesta,\n"
                + "	encuesta.descripcion AS descripcion_encuesta, \n"
                + "	entrevista.*, \n"
                + "	municipio.nombre AS nombre_municipio,\n"
                + "	municipio.codigo AS codigo_municipio, \n"
                + "	distrito.codigo AS codigo_distrito, \n"
                + "	persona.nombre AS nombre_promotor, \n"
                + "	persona.apellido_paterno AS apellido_paterno_promotor, \n"
                + "	persona.apellido_materno AS apellido_materno_promotor \n"
                + "FROM entrevista \n"
                + "	inner join distrito_municipio ON \n"
                + "	 distrito_municipio.id = entrevista.distrito_municipio_id \n"
                + "	inner join municipio ON \n"
                + "	 distrito_municipio.municipio_id = municipio.id \n"
                + "	INNER JOIN distrito ON \n"
                + "	 distrito_municipio.distrito_id = distrito.id \n"
                + "	inner join usuario ON \n"
                + "	 entrevista.usuario_id = usuario.id \n"
                + "	inner join persona ON \n"
                + "	 persona.id = usuario.persona_id \n"
                + "	inner join encuesta ON \n"
                + "	 entrevista.encuesta_id = encuesta.id\n"
                + "	 where entrevista.usuario_id =  " + idUsuario + " \n"
                + "	 and entrevista.encuesta_id =  " + idEncuesta + ";";
        respuesta = DAO.existeSentencia(consulta);
        System.out.println(consulta);

        return SUCCESS;
    }

    public String reporteEncuestaMunicipioJSON() {
        String consulta = "SELECT  \n"
                + "	encuesta.nombre as nombre_encuesta,  \n"
                + "    encuesta.descripcion as descripcion_encuesta, \n"
                + "	entrevista.*, \n"
                + "	municipio.nombre AS nombre_municipio, \n"
                + "	municipio.codigo AS codigo_municipio, \n"
                + "    estado.nombre as nombre_estado, \n"
                + "	distrito.codigo AS codigo_distrito, \n"
                + "	persona.nombre AS nombre_promotor, \n"
                + "	persona.apellido_paterno AS apellido_paterno_promotor, \n"
                + "	persona.apellido_materno AS apellido_materno_promotor \n"
                + "FROM entrevista \n"
                + "	inner join distrito_municipio ON \n"
                + "	 distrito_municipio.id = entrevista.distrito_municipio_id \n"
                + "	inner join municipio ON \n"
                + "	 distrito_municipio.municipio_id = municipio.id \n"
                + "     inner join estado	ON \n"
                + "     estado.id = municipio.estado_id \n"
                + "	INNER JOIN distrito ON \n"
                + "	 distrito_municipio.distrito_id = distrito.id \n"
                + "	inner join usuario ON \n"
                + "	 entrevista.usuario_id = usuario.id \n"
                + "	inner join persona ON  \n"
                + "	 persona.id = usuario.persona_id \n"
                + "	 inner join encuesta \n"
                + "on entrevista.encuesta_id = encuesta.id \n"
                + "where entrevista.encuesta_id = " + idEncuesta + " \n"
                + "and municipio.id =" + idMunicipio + ";";
        respuesta = DAO.existeSentencia(consulta);
        System.out.println(consulta);

        return SUCCESS;
    }

    public String reporteEntrevistasPromotorEncuesta() throws SQLException {
        mySource = new ArrayList();
        conexion = ConexionBD.getConexion();
        parametro = new HashMap();
        parametro.put(ID_ENCUESTA_2, idEncuesta);
        parametro.put(ID_USUARIO, idUsuario);
        nombreReporte = "Encuestas de un promotor.xls";
        return SUCCESS;
    }

    public List getMySource() {
        return mySource;
    }

    public void setMySource(List mySource) {
        this.mySource = mySource;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Map getParametro() {
        return parametro;
    }

    public void setParametro(Map parametro) {
        this.parametro = parametro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

}
