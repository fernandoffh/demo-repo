/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.usuario;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import modelo.usuario.BeanUsuario;
import modelo.usuario.DaoUsuario;
import utilidades.Herramientas;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author alex
 */
public class ControlUsuario extends ActionSupport implements SessionAware {

    private final DaoUsuario DAO = new DaoUsuario();
    private Map session;// o utilza Struts por defecto (varibale que uyiliza la interfaz)
    private int id;
    private BeanUsuario usuario;
    private boolean respuesta;
    private String correo;
    private String mensaje;
    private String codigoRecuperacion;
    private String contrasena;
    private String codigoRegistro;
    private List<BeanUsuario> promotores;
    private String contrasenaNueva;
    private String contrasenaAnterior;
    private InputStream inputStream;

    public String registrarUsuarioAdministrador() {
        mensaje = "Estas dado de alta en el sistema.";
        respuesta = false;
        if (usuario == null) {
            mensaje = "Por favor intenta más tarde tu registro, Gracias.";
        } else {
            if (codigoRegistro.equalsIgnoreCase(Herramientas.CODIGO_UNICO)) {
                if (!DAO.correoDisponible(usuario.getCorreo())) {
                    respuesta = DAO.registrarUsuarioAdministrador(usuario);
                } else {
                    mensaje = "El correo que ingresaste no esta disponible, ingresa otro.";
                }
            } else {
                mensaje = "El código de registro es incorrecto.";
            }
        }
        System.out.println("control.usuario.ControlUsuario.actualizarDatosUsuario() mensaje: " + mensaje);
        System.out.println("control.usuario.ControlUsuario.actualizarDatosUsuario() respuesta: " + respuesta);
        return SUCCESS;
    }

    public String registrarPromotor() {
        respuesta = false;
        if (usuario == null) {
            mensaje = "Por favor intenta más tarde tu registro, Gracias.";
        } else {
            if (!DAO.correoDisponible(usuario.getCorreo())) {
                respuesta = DAO.registrarPromotor(usuario);
                mensaje = SUCCESS;
            } else {
                mensaje = "El correo que ingresaste no esta disponible, ingresa otro.";
            }
        }
        inputStream = new StringBufferInputStream(mensaje);
        return SUCCESS;
    }

    public String consultarPromotores() {
        promotores = DAO.consultarPromotores();
        return SUCCESS;
    }

    public String goPromotores() {
        promotores = DAO.consultarPromotores();
        System.out.println("promotores size "+promotores.size());
        System.out.println("promotores size "+promotores.toString());
        return SUCCESS;
    }
    public String eliminarUsuario(){
        respuesta = DAO.eliminarUsuario(id);
        return SUCCESS;
    }

    public String cambiarContrasena() {
        mensaje = "El código ingresado es incorrecto, favor de rectificar.";
        respuesta = false;
        if (!codigoRecuperacion.equals("0")) {
            if (DAO.existeCodigo(codigoRecuperacion)) {
                if (DAO.cambiarContrasena(contrasena, codigoRecuperacion)) {
                    respuesta = true;
                    mensaje = "Tu contraseña ha sido cambiada correctamente, puedes ingresar.";
                } else {
                    mensaje = "Hubo un problema al cambiar tu contrasena, intentalo más tarde.";
                }
            }
        }
        return SUCCESS;
    }

    public String cambiarMiContrasenaApp() {
        respuesta = false;
        if (DAO.cambiarMiContrasena(contrasena, id)) {
            respuesta = true;
            mensaje = "Tu contraseña ha sido cambiada correctamente";
        } else {
            mensaje = "Hubo un problema al cambiar tu contrasena, intentalo más tarde.";
        }

        return SUCCESS;
    }

    public String cambiarMiContrasena() {
        mensaje = "La contraseña actual es incorrecta";
        respuesta = false;
        BeanUsuario user = (BeanUsuario) session.get(Herramientas.SESSION_USUARIO);
        if (user.getContrasena().equals(contrasenaAnterior)) {
            if (DAO.cambiarMiContrasena(contrasenaNueva, user.getId())) {
                usuario = DAO.iniciarSesion(user.getCorreo(), contrasenaNueva);
                session = ActionContext.getContext().getSession();
                session.put(Herramientas.SESSION_USUARIO, usuario);
                respuesta = true;
            } else {
                mensaje = "Hubo un problema al cambiar tu contrasena, intentalo más tarde.";
            }
        }
        return SUCCESS;
    }

    public String actualizarDatosUsuario() {
        session = ActionContext.getContext().getSession();
        BeanUsuario user = (BeanUsuario) session.get(Herramientas.SESSION_USUARIO);
        respuesta = false;
        if (usuario != null) {
            if (user.getContrasena().equals(usuario.getContrasena())) {
                if (!DAO.correoDisponible(usuario.getCorreo().equals(user.getCorreo()) ? "" : usuario.getCorreo())) {
                    usuario.getPersona().setId(user.getPersona().getId());
                    respuesta = DAO.actualizarDatosUsuario(usuario);
                    if (respuesta) {
                        usuario = DAO.iniciarSesion(usuario.getCorreo(), user.getContrasena());
                        session = ActionContext.getContext().getSession();
                        session.put(Herramientas.SESSION_USUARIO, usuario);
                        mensaje = "Los cambios han sido guardados correctamente";
                    } else {
                        mensaje = "No se han acualizado los datos, intenta más tarde.";
                    }
                } else {
                    mensaje = "El correo que ingresaste no esta disponible, ingresa otro.";
                }
            } else {
                mensaje = "La contraseña es incorrecta";
            }
        } else {
            mensaje = "Los datos no se han acualizado, intenta más tarde.";
        }

        return SUCCESS;
    }
    public String actualizarDatosUsuarioApp() {     
        if (usuario != null) {
                if (!DAO.correoDisponible(usuario.getCorreo().equals(correo) ? "" : usuario.getCorreo())) {
                    respuesta = DAO.actualizarDatosUsuario(usuario);
                    if (respuesta) {
                        mensaje = SUCCESS;
                    } else {
                        mensaje = "No se han acualizado los datos, intenta más tarde.";
                    }
                } else {
                    mensaje = "El correo que ingresaste no esta disponible, ingresa otro.";
                }
            
        } else {
            mensaje = "Los datos no se han acualizado, intenta más tarde.";
        }
        inputStream = new StringBufferInputStream(mensaje);
        return SUCCESS;
    }

    public String cambiarEstatusPromotor() {
        respuesta = DAO.cambiarEstatusPromotor(id);
        return SUCCESS;
    }

    public BeanUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(BeanUsuario usuario) {
        this.usuario = usuario;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodigoRecuperacion() {
        return codigoRecuperacion;
    }

    public void setCodigoRecuperacion(String codigoRecuperacion) {
        this.codigoRecuperacion = codigoRecuperacion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public List<BeanUsuario> getPromotores() {
        return promotores;
    }

    public void setPromotores(List<BeanUsuario> promotores) {
        this.promotores = promotores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getContrasenaAnterior() {
        return contrasenaAnterior;
    }

    public void setContrasenaAnterior(String contrasenaAnterior) {
        this.contrasenaAnterior = contrasenaAnterior;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
