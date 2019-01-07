/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entrevista;

import java.util.List;
import modelo.distrito.BeanDistrito;
import modelo.distritomunicipio.BeanDistritoMunicipio;
import modelo.encuesta.BeanEncuesta;
import modelo.municipio.BeanMunicipio;
import modelo.usuario.BeanUsuario;

/**
 *
 * @author alex
 */
public class BeanEntrevista {

    private int id;
    private String fechaRegistro;
    private int seccion;
    private String tipoCasilla;
    private String sexo;
    private String edad;
    private int votoMujer;
    private BeanDistritoMunicipio distritoMunicipio;
    private BeanUsuario usuario;
    private BeanEncuesta encuesta;
    private BeanMunicipio municipio;
    private BeanDistrito distrito;

    public BeanEntrevista() {
    }

    public BeanEntrevista(int id, String fechaRegistro, BeanUsuario usuario) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public BeanEncuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(BeanEncuesta encuesta) {
        this.encuesta = encuesta;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public String getTipoCasilla() {
        return tipoCasilla;
    }

    public void setTipoCasilla(String tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getVotoMujer() {
        return votoMujer;
    }

    public void setVotoMujer(int votoMujer) {
        this.votoMujer = votoMujer;
    }

    public BeanDistritoMunicipio getDistritoMunicipio() {
        return distritoMunicipio;
    }

    public void setDistritoMunicipio(BeanDistritoMunicipio distritoMunicipio) {
        this.distritoMunicipio = distritoMunicipio;
    }

    public BeanMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(BeanMunicipio municipio) {
        this.municipio = municipio;
    }

    public BeanDistrito getDistrito() {
        return distrito;
    }

    public void setDistrito(BeanDistrito distrito) {
        this.distrito = distrito;
    }

}
