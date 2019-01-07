/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.sesion;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.usuario.BeanUsuario;
import modelo.usuario.DaoUsuario;
import utilidades.Herramientas;

/**
 *
 * @author alex
 */
public class ControlSesion extends ActionSupport implements SessionAware {

    private Map session;// o utilza Struts por defecto (varibale que uyiliza la interfaz)
    private boolean respuesta;
    private String mensaje;
    private BeanUsuario usuario;
    private final DaoUsuario DAO = new DaoUsuario();

    public String iniciarSesion() {
        respuesta = false;
        mensaje = "No puedes iniciar sesión por ahora, intenta más tarde.";
        if (usuario != null) {
            if (DAO.correoDisponible(usuario.getCorreo())) {
                usuario = DAO.iniciarSesion(usuario.getCorreo(), usuario.getContrasena());
                if (usuario != null) {
                    if (!usuario.getRol().getRol().equalsIgnoreCase(Herramientas.PROMOTOR)) {
                        session = ActionContext.getContext().getSession();
                        session.put(Herramientas.SESSION_USUARIO, usuario);
                        session.put("logueado", true);
                        respuesta = true;
                        mensaje = "Bienvenido(a) " + usuario.getPersona().getNombre();
                    } else {
                        mensaje = "Tú tipo de cuenta no permite el acceso.";
                    }
                } else {
                    mensaje = "Contraseña incorrecta.";
                }
            } else {
                mensaje = "El correo " + usuario.getCorreo() + " no esta registrado.";
            }
        }
        return SUCCESS;
    }

    public String iniciarSesionApp() {
        respuesta = false;
        mensaje = "No puedes iniciar sesión por ahora, intenta más tarde.";
        if (usuario != null) {
            if (DAO.correoDisponible(usuario.getCorreo())) {
                usuario = DAO.iniciarSesion(usuario.getCorreo(), usuario.getContrasena());
                if (usuario != null) {
                    if (usuario.isEstatus()) {
                        respuesta = true;
                        mensaje = "Bienvenido(a) " + usuario.getPersona().getNombre();
                    }else{
                        mensaje = "Tu cuenta ha sido desactivada";
                    }
                } else {
                    mensaje = "Contraseña incorrecta.";
                }
            } else {
                mensaje = "El correo " +usuario.getCorreo() + " no esta registrado.";
            }
        }
        return SUCCESS;
    }

    public String cerrarSesion() {
        session = ActionContext.getContext().getSession();
        session.clear();
        return SUCCESS;
    }

    public String cargarUsuarioSession() {
        session = ActionContext.getContext().getSession();
        usuario = (BeanUsuario) session.get(Herramientas.SESSION_USUARIO);
        respuesta = usuario != null;

        if (!respuesta) {
            mensaje = "Lo sentimos tu sesión ha expirado, por favor vuelve a iniciar sesión.";
        }
        return SUCCESS;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

}
