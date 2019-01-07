/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estado;

import java.util.List;
import modelo.municipio.BeanMunicipio;

/**
 *
 * @author CDS-
 */
public class BeanEstado {
    private int id;
    private String  nombre;
    private int codigo;
    private int cantidadDistritos;
    private boolean estatus;
    
    private List<BeanMunicipio> municipios;

    public BeanEstado() {
    }

    public BeanEstado(int id, String nombre, int codigo, List<BeanMunicipio> municipios) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.municipios = municipios;
    }

    public BeanEstado(int id, String nombre, int codigo, int cantidadDistritos, boolean estatus, List<BeanMunicipio> municipios) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidadDistritos = cantidadDistritos;
        this.estatus = estatus;
        this.municipios = municipios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<BeanMunicipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<BeanMunicipio> municipios) {
        this.municipios = municipios;
    }

    public int getCantidadDistritos() {
        return cantidadDistritos;
    }

    public void setCantidadDistritos(int cantidadDistritos) {
        this.cantidadDistritos = cantidadDistritos;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
}
