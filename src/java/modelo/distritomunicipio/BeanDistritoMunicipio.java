/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.distritomunicipio;

import modelo.distrito.BeanDistrito;
import modelo.municipio.BeanMunicipio;

/**
 *
 * @author CDS-
 */
public class BeanDistritoMunicipio {
    private int id;
    private boolean estatus;
    private BeanDistrito distrito;
    private BeanMunicipio municipio;

    public BeanDistritoMunicipio() {
    }

    public BeanDistritoMunicipio(int id, boolean estatus, BeanDistrito distrito, BeanMunicipio municipio) {
        this.id = id;
        this.estatus = estatus;
        this.distrito = distrito;
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public BeanDistrito getDistrito() {
        return distrito;
    }

    public void setDistrito(BeanDistrito distrito) {
        this.distrito = distrito;
    }

    public BeanMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(BeanMunicipio municipio) {
        this.municipio = municipio;
    }

    
   
    
    
}
