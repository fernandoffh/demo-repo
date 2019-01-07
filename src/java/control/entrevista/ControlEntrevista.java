/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.entrevista;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import modelo.encuesta.DaoEncuesta;
import modelo.entrevista.DaoEntrevista;
import modelo.entrevista.BeanEntrevista;

/**
 *
 * @author alex
 */
public class ControlEntrevista extends ActionSupport {

    private final DaoEntrevista DAO = new DaoEntrevista();
    private final DaoEncuesta DAO_ENCUESTA = new DaoEncuesta();
    private List<BeanEntrevista> entrevistas;
    private BeanEntrevista entrevista;
    private String mensaje;
    private String informe;
    private boolean respuesta;
    private int id;
    private InputStream inputStream;

    public String registrarEntrevista() {
        mensaje = SUCCESS;
        if (entrevista == null) {
            mensaje = "Los datos de la encuesta no estan llegando de manera correcta.";
        } else {
            if (DAO_ENCUESTA.consultarEstatusEncuesta(entrevista.getEncuesta().getId())) {
                boolean execute = DAO.registrarEntrevista(entrevista);
                if (execute) {
                    System.out.println("entrevista registrada" + entrevista.getFechaRegistro());
                }
            }
        }
        inputStream = new StringBufferInputStream(mensaje);
        return SUCCESS;
    }

    public String registrarEntrevistasConRespuestas() {
        respuesta = false;
        mensaje = "Datos enviados correctamente";
        informe = "Informe \n";
        int entrevistasRegistradas = 0;
        for (BeanEntrevista beanEntrevista : entrevistas) {
            if (DAO_ENCUESTA.consultarEstatusEncuesta(beanEntrevista.getEncuesta().getId())) {
            }
        }
        informe += entrevistas.size() + "/" + entrevistasRegistradas + "\n";
        informe += "entrevistas registradas.";
        return SUCCESS;
    }
    public String consultarEntrevistasDeEncuesta(){
        if (id != 0) {
            entrevistas = DAO.consultarEntrevistasDeEncuesta(id);
            return SUCCESS;
        }
        return ERROR;
    }

    public BeanEntrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(BeanEntrevista entrevista) {
        this.entrevista = entrevista;
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

    public List<BeanEntrevista> getEntrevistas() {
        return entrevistas;
    }

    public void setEntrevistas(List<BeanEntrevista> entrevistas) {
        this.entrevistas = entrevistas;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
