/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.estado;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.distrito.BeanDistrito;
import modelo.distrito.DaoDistrito;
import modelo.estado.BeanEstado;
import modelo.estado.DaoEstado;
import modelo.municipio.BeanMunicipio;

/**
 *
 * @author CDS-
 */
public class ControlEstado extends ActionSupport {

    private final DaoEstado DAO = new DaoEstado();
    private final DaoDistrito DAO_DISTRITO = new DaoDistrito();
    private boolean respuesta;
    private boolean estatus;
    private int id;
    private int idDistrito;
    private String mensaje;
    private List<BeanEstado> estados;
    private List<BeanMunicipio> municipios;
    private BeanEstado estado;
    private List<BeanDistrito> distritos;

    public String consultarUnEstado() {
        if (id == 0) {
            mensaje = "No se cargarón los datos del estado";
            return SUCCESS;
        }
        estado = DAO.consultarUnEstado(id);
        if (estado == null) {
            mensaje = "Ya que no hay un problema al consultarlos";
        } else {
            mensaje = "Los datos han sido modificados";
        }
        respuesta = estado != null;
        return SUCCESS;
    }

    public String eliminarEstado() {
        if (id == 0) {
            mensaje = "No se cargarón los datos del estado para eliminar";
            return SUCCESS;
        }

        respuesta = DAO.eliminarEstado(id);
        mensaje = respuesta ? "Estado eliminado correctamente" : "Ocurrio un problema al eliminarlo";
        return SUCCESS;
    }

    public String actualizarEstado() {
        respuesta = false;
        if (estado == null) {
            mensaje = "Los datos no estan llegando de manera correcta";
            return SUCCESS;
        }
        if (estado.getId() <= 0) {
            mensaje = "No se puede actualizar la información del estado por el momento.";
            return SUCCESS;
        }
        if (estado.getCantidadDistritos() == 0 || estado.getCantidadDistritos() < 0 || estado.getCantidadDistritos() > DAO_DISTRITO.totalDistritos()) {
            mensaje = "La cantidad de distritos no es correcta";
            return SUCCESS;
        }
        respuesta = DAO.actualizarEstado(estado);
        if (respuesta) {
            mensaje = "Los datos fueron modificados correctamente";
        }
        return SUCCESS;
    }

    public String distritosMunicipiosEstado() {
        if (id != 0) {
            estado = DAO.consultarUnEstado(id);
            if (estado != null) {
                municipios = DAO.consultarMunicipiosEstado(id);
                distritos = DAO_DISTRITO.consultarDistritosDeEstado(estado.getCantidadDistritos());
            }
        }
        return SUCCESS;
    }

    public String consultarEstadosConMunicipios() {
        estados = DAO.consultarEstadosConMunicipios();
        return SUCCESS;
    }

    public String cambiarEstatusEstado() {
        respuesta = DAO.cambiarEstatusEstado(id);
        return SUCCESS;
    }

    public String consultarEstados() {
        estados = DAO.consultarEstados();
        return SUCCESS;
    }

    public String goEstados() {
        estados = DAO.consultarEstados();
        return SUCCESS;
    }

    public String consultarMunicipiosEstado() {
        estado = DAO.consultarUnEstado(id);
        municipios = DAO.consultarMunicipiosEstado(id);
        return SUCCESS;
    }
    public String consultarMunicipiosEstadoJSON() {
        estado = DAO.consultarUnEstado(id);
        municipios = DAO.consultarMunicipiosEstado(id);
        return SUCCESS;
    }
    public String consultarMunicipiosAsignadosAdistritos() {
        municipios = DAO.consultarMunicipiosAsignadosAdistritos(idDistrito, id);
        return SUCCESS;
    }

    public String registrarEstado() {
        if (estado == null) {
            mensaje = "Los datos no estan llegando de manera correcta, intentalo más tarde";
        } else {
            if (estado.getCantidadDistritos() == 0 || estado.getCantidadDistritos() < 0 || estado.getCantidadDistritos() > DAO_DISTRITO.totalDistritos()) {
                mensaje = "La cantidad de distritos no es correcta";
            } else {
                respuesta = DAO.registrarEstado(estado);
                if (respuesta) {
                    mensaje = estado.getNombre() + " fue agregado.";
                } else {
                    mensaje = "No se ha poddio realizar el registro por ahora, intenta mas tarde.";
                }
            }
        }
        return SUCCESS;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
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

    public List<BeanEstado> getEstados() {
        return estados;
    }

    public void setEstados(List<BeanEstado> estados) {
        this.estados = estados;
    }

    public List<BeanMunicipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<BeanMunicipio> municipios) {
        this.municipios = municipios;
    }

    public BeanEstado getEstado() {
        return estado;
    }

    public void setEstado(BeanEstado estado) {
        this.estado = estado;
    }

    public List<BeanDistrito> getDistritos() {
        return distritos;
    }

    public void setDistritos(List<BeanDistrito> distritos) {
        this.distritos = distritos;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

}
