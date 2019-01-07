/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.usuario.DaoUsuario;

/**
 *
 * @author alex
 */
public class MailManager extends ActionSupport {

    static Properties properties = new Properties();
    private final DaoUsuario DAO = new DaoUsuario();
    private String mensaje;
    private String from;
    private String password;
    private String to;
    private String subject;
    private String body;
    private String correo;
    private boolean respuesta;

    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    public boolean enviarCodigoRecuperacionCorreo(String correo, String codigo) {
        setFrom(Herramientas.CORREO);
        setPassword(Herramientas.CONTRASENA);
        setTo(correo);
        setSubject("Código de recuperación");
        setBody("Por medio del siguiente codigo podrá hacer uso para recuperar su contraseña. \n código: " + codigo);
        try {
            Session session = Session.getDefaultInstance(properties,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication
                        getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("utilidades.MailManager.enviarCodigoRecuperacion()");
            System.out.println("Error" + e.getMessage());
            return false;
        }
        return true;
    }

    public String enviarCodigoRecuperacion() {
        String codigo = Herramientas.cadenaRandom();
        respuesta = false;
        if (DAO.correoDisponible(correo)) {
            if (enviarCodigoRecuperacionCorreo(correo, codigo)) {
                if (DAO.asignarCodigoRecuperacion(codigo, correo)) {
                    mensaje = "El codigo de recuperación ha sido enviado correctamente.";
                    respuesta = true;
                }
            } else {
                mensaje = "Lo sentimos ocurrio un error al enviar el correo, por favor intentalo más tarde.";
            }

        } else {
            mensaje = "El correo que ingresado no esta registrado en el sistema. ";
        }

        return SUCCESS;
    }
    

    public String existeCorreoApp() {
        respuesta = correo != null;
        if (!respuesta) {
            correo = "no";
            return SUCCESS;
        }
        respuesta = DAO.correoDisponible(correo);
        return SUCCESS;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

}
