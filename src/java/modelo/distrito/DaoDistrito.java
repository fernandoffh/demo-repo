/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.distrito;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.estado.BeanEstado;
import utilidades.ConexionBD;

/**
 *
 * @author CDS-
 */
public class DaoDistrito {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public List<BeanDistrito> consultarDistritos() {
        List<BeanDistrito> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM distrito");
            rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(new BeanDistrito(rs.getInt("id"), rs.getInt("codigo")));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.distrito.DaoDistrito.consultarDistritos()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.distrito.DaoDistrito.consultarDistritos()Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    public int totalDistritos() {
        int respuesta = 0;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT count(id) as total_estados FROM distrito;");
            rs = pstm.executeQuery();
            if (rs.next()) {
                  respuesta = rs.getInt("total_estados");
            }
        } catch (SQLException ex) {
            System.out.println("modelo.distrito.DaoDistrito.totalDistritos()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.distrito.DaoDistrito.totalDistritos()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    

    public List<BeanDistrito> consultarDistritosDeEstado(int cantidadDistritos) {
        List<BeanDistrito> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM distrito");
            rs = pstm.executeQuery();
            int i = 0;
            while (rs.next() && i<(cantidadDistritos)) {
                lista.add(new BeanDistrito(rs.getInt("id"), rs.getInt("codigo")));
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("modelo.distrito.DaoDistrito.consultarDistritosDeEstado()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.distrito.DaoDistrito.consultarDistritosDeEstado()Error: " + ex.getMessage());
            }
        }
        return lista;
    }
}
