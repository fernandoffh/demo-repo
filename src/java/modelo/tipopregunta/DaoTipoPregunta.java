/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.tipopregunta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.estado.BeanEstado;
import utilidades.ConexionBD;
import utilidades.Herramientas;

/**
 *
 * @author CDS-
 */
public class DaoTipoPregunta {

    private Connection con;
    private ResultSet rs;
    private ArrayList list;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public List<BeanTipoPregunta> consultarTipoPregunta() {
        List<BeanTipoPregunta> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM tipo_pregunta");
            rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(new BeanTipoPregunta(rs.getInt("id"), rs.getString("tipo")));
            }
        } catch (SQLException ex) {
                System.out.println("modelo.tipopregunta.DaoTipoPregunta.consultarTipoPregunta()Error: " + ex.getMessage());
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
                System.out.println("modelo.tipopregunta.DaoTipoPregunta.consultarTipoPregunta()Error: " + ex.getMessage());
            }
        }
        return lista;
    }

}
