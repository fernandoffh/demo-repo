/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.distrito;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import modelo.distrito.BeanDistrito;
import modelo.distrito.DaoDistrito;

/**
 *
 * @author CDS-
 */
public class ControlDistrito extends ActionSupport {

    private DaoDistrito daoDistrito = new DaoDistrito();
    private List<BeanDistrito> distritos;

    public String consultarDistritos() {
        distritos = daoDistrito.consultarDistritos();
        return SUCCESS;
    }

    public List<BeanDistrito> getDistritos() {
        return distritos;
    }

    public void setDistritos(List<BeanDistrito> distritos) {
        this.distritos = distritos;
    }

}
