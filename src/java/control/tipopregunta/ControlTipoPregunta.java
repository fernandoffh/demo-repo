/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.tipopregunta;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.tipopregunta.BeanTipoPregunta;
import modelo.tipopregunta.DaoTipoPregunta;

/**
 *
 * @author CDS-
 */
public class ControlTipoPregunta extends ActionSupport {

    private List<BeanTipoPregunta> lista;
    private DaoTipoPregunta dao = new DaoTipoPregunta();

    public String consultarTipoPregunta() {
        lista = dao.consultarTipoPregunta();
        return SUCCESS;
    }

    public List<BeanTipoPregunta> getLista() {
        return lista;
    }

    public void setLista(List<BeanTipoPregunta> lista) {
        this.lista = lista;
    }
}
