/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.encuesta;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.encuesta.BeanEncuesta;
import modelo.encuesta.DaoEncuesta;
import modelo.estado.BeanEstado;
import modelo.estado.DaoEstado;

/**
 *
 * @author alex
 */
public class ControlEncuesta extends ActionSupport {

    private boolean respuesta;
    private boolean estatus;
    private int id;
    private String mensaje;
    private DaoEncuesta DAO = new DaoEncuesta();
    private DaoEstado daoEstado = new DaoEstado();
    private List<BeanEncuesta> list;
    private BeanEncuesta encuesta;
    private List<BeanEstado> estados;

    public String consultarEncuestas() {
        list = DAO.consultarEncuestas();
        return SUCCESS;
    }
    public String eliminarEncuesta() {
        respuesta = DAO.eliminarEncuesta(id);
        return SUCCESS;
    }
    
    public String goInicio() {
        list = DAO.consultarEncuestas();
        return SUCCESS;
    }

    public String consultaGeneralUnaEncuesta() {
        encuesta = DAO.consultaGeneralUnaEncuesta(id);
        respuesta = encuesta != null;
        return SUCCESS;
    }

    public String consultarEncuestasActivas() {
        list = DAO.consultarEncuestasActivas();
        return SUCCESS;
    }

    public String cambiarEstatusEncuesta() {
        respuesta = DAO.cambiarEstatusEncuesta(id);
        return SUCCESS;
    }

    public String consultarUnaEncuesta() {
        encuesta = DAO.consultarUnaEncuesta(id);
        respuesta = encuesta != null;
        return SUCCESS;
    }

    public String acualizarEncuesta() {
        if (encuesta == null) {
            mensaje = "Ocurrio un error en la solicitud, intenta más tarde.";
        } else {
            respuesta = DAO.acualizarEncuesta(encuesta);
            mensaje = respuesta ? "Los cambios han sido guardados correctamente" : "Los cambios no han sido guardados, intenta más tarde.";
        }
        return SUCCESS;
    }

    public String registarEncuesta() {
        if (encuesta == null) {
            mensaje = "Ocurrio un error en la solicitud, intenta más tarde.";
        } else {
            respuesta = DAO.registarEncuesta(encuesta);
            mensaje = respuesta ? "Registro correcto" : "La información no han sido guardada, intenta más tarde.";
        }
        return SUCCESS;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<BeanEncuesta> getList() {
        return list;
    }

    public void setList(List<BeanEncuesta> list) {
        this.list = list;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public BeanEncuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(BeanEncuesta encuesta) {
        this.encuesta = encuesta;
    }

    public List<BeanEstado> getEstados() {
        return estados;
    }

    public void setEstados(List<BeanEstado> estados) {
        this.estados = estados;
    }

}
