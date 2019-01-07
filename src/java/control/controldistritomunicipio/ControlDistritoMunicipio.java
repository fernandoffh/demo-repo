/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.controldistritomunicipio;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.distrito.DaoDistrito;
import modelo.distritomunicipio.BeanDistritoMunicipio;
import modelo.distritomunicipio.DaoDistritoMunicipio;

/**
 *
 * @author CDS-
 */
public class ControlDistritoMunicipio extends ActionSupport {

    private List<BeanDistritoMunicipio> distritoMunicipios;
    private DaoDistritoMunicipio dao = new DaoDistritoMunicipio();
    private String mensaje;
    private boolean respuesta;
    private int id;
    private int idMunicipio;
    private int idDistrito;

    public String consultarDistritoMunicipiosId() {
        distritoMunicipios = dao.consultarDistritoMunicipiosId();
        return SUCCESS;
    }

    public String eliminarDistritoMunicipio() {
        respuesta = dao.eliminarDistritoMunicipio(id);
        return SUCCESS;
    }

    public String registrarDistritoMunicipio() {
        respuesta = false;
        if (idDistrito == 0) {
            mensaje = "No se tienen datos del distrito";
            return SUCCESS;
        }
        if (idMunicipio == 0) {
            mensaje = "No se tienen datos del municipio";
            return SUCCESS;

        }
        respuesta = dao.existeMunicipioDistrito(idMunicipio, idDistrito);
        if (respuesta) {
            mensaje = "Este municipio ya esta registrado en el municipio";
            return SUCCESS;
        }
        respuesta = dao.registrarDistritoMunicipio(idDistrito, idMunicipio);
        if (respuesta) {
            mensaje = " ";
        }else{
            mensaje = "No se pudo asignar el municipio al distrito, intenta mas tarde";
        }
        return SUCCESS;
    }

    public List<BeanDistritoMunicipio> getDistritoMunicipios() {
        return distritoMunicipios;
    }

    public void setDistritoMunicipios(List<BeanDistritoMunicipio> distritoMunicipios) {
        this.distritoMunicipios = distritoMunicipios;
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

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

}
