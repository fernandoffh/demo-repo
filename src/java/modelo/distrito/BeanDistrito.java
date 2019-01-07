/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.distrito;

/**
 *
 * @author CDS-
 */
public class BeanDistrito {
    private int id;
    private int codigo;

    public BeanDistrito() {
    }

    public BeanDistrito(int id, int codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
