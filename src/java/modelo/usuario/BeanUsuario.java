/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import modelo.persona.BeanPersona;
import modelo.rol.BeanRol;

/**
 *
 * @author Fernando Fajardo Hernández
 */
public class BeanUsuario {

    private int id;
    private String correo;
    private String contrasena;
    private String codigoRecuperacion;
    private boolean estatus;
    private BeanRol rol;
    private BeanPersona persona;
    private String codigoRegistro;

    public BeanUsuario() {
    }

    public BeanUsuario(int id, String correo, String contrasena, String codigoRecuperacion, boolean estatus, BeanRol rol, BeanPersona persona) {
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;
        this.codigoRecuperacion = codigoRecuperacion;
        this.estatus = estatus;
        this.rol = rol;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public BeanRol getRol() {
        return rol;
    }

    public void setRol(BeanRol rol) {
        this.rol = rol;
    }

    public BeanPersona getPersona() {
        return persona;
    }

    public void setPersona(BeanPersona persona) {
        this.persona = persona;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

}
