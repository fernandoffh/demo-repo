/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.municipio;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Container;
import java.util.List;
import modelo.municipio.BeanMunicipio;
import modelo.municipio.DaoMunicipio;

/**
 *
 * @author alex
 */
public class ControlMunicipio extends ActionSupport {

    private List<BeanMunicipio> municipios;
    private DaoMunicipio dao = new DaoMunicipio();
    private int id;
    private String mensaje;
    private boolean respuesta;
    private BeanMunicipio municipio;

    public String consultarMunicipios() {
        municipios = dao.consultarMunicipios();
        return SUCCESS;
    }

    public String cambiarEstatusMunicipio() {
        if (id <= 0) {
            mensaje = "No se ha cargado el estatus del municipio";
            return SUCCESS;
        }
        respuesta = dao.cambiarEstatusMunicipio(id);
        if (!respuesta) {
            mensaje = "No se pudo cambiar el estatus del municipio";
        }
        return SUCCESS;
    }

    public String registrarMunicipio() {
        if (municipio == null) {
            mensaje = "Los datos no estan llegando de manera correcta, intentalo más tarde";
        } else {
            if (municipio.getCodigo()== 0 || municipio.getCodigo() < 0) {
                mensaje = "El código no es correcto";
                return  SUCCESS;
            }
            respuesta = dao.registrarMunicipio(municipio);
            if (respuesta) {
                mensaje = municipio.getNombre() + " fue agregado.";
            } else {
                mensaje = "No se ha poddio realizar el registro por ahora, intenta mas tarde.";
            }
        }
        return SUCCESS;
    }

    public String consultarMunicipio() {
        municipio = dao.consultarMunicipio(id);
        if (municipio == null) {
            mensaje = "No se han cargado los datos del municipio";
        }
        respuesta = municipio != null;
        return SUCCESS;
    }

    public String eliminarMunicipio() {
        respuesta = dao.eliminarMunicipio(id);
        return SUCCESS;
    }

    public String acualizarMunicipio() {
        respuesta = false;
        if (municipio == null) {
            mensaje = "Los datos no estan llegando de manera correcta";
            return SUCCESS;
        }
        if (municipio.getId() <= 0) {
            mensaje = "No se puede actualizar la información del estado por el momento.";
            return SUCCESS;
        }
         if (municipio.getCodigo()== 0 || municipio.getCodigo() < 0) {
                mensaje = "El código no es correcto";
                return  SUCCESS;
            }
        respuesta = dao.acualizarMunicipio(municipio);
        if (respuesta) {
            mensaje = "Los datos fueron modificados correctamente";
        }
        return SUCCESS;
    }

    public List<BeanMunicipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<BeanMunicipio> municipios) {
        this.municipios = municipios;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
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

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public BeanMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(BeanMunicipio municipio) {
        this.municipio = municipio;
    }

}
