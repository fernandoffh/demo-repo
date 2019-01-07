/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.municipio;

import modelo.estado.BeanEstado;

/**
 *
 * @author CDS-
 */
public class BeanMunicipio {

    private int id;
    private String nombre;
    private int codigo;
    private BeanEstado estado;
    private boolean estatus;
    private int idDistritoMunicipio;
    
    public BeanMunicipio() {
    }

    public BeanMunicipio(int id, String nombre, int codigo, BeanEstado estado) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
    }

    public BeanMunicipio(int id, String nombre, int codigo, BeanEstado estado, boolean estatus) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
        this.estatus = estatus;
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

    public BeanEstado getEstado() {
        return estado;
    }

    public void setEstado(BeanEstado estado) {
        this.estado = estado;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public int getIdDistritoMunicipio() {
        return idDistritoMunicipio;
    }

    public void setIdDistritoMunicipio(int idDistritoMunicipio) {
        this.idDistritoMunicipio = idDistritoMunicipio;
    }

}
