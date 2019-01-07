/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.distritomunicipio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.distrito.BeanDistrito;
import modelo.estado.BeanEstado;
import modelo.municipio.BeanMunicipio;
import utilidades.ConexionBD;

/**
 *
 * @author CDS-
 */
public class DaoDistritoMunicipio {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public List<BeanDistritoMunicipio> consultarDistritoMunicipiosId() {
        List<BeanDistritoMunicipio> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM distrito_municipio");
            rs = pstm.executeQuery();
            while (rs.next()) {

                BeanMunicipio municipio = new BeanMunicipio();
                municipio.setId(rs.getInt("municipio_id"));

                BeanDistrito distrito = new BeanDistrito();
                distrito.setId(rs.getInt("distrito_id"));

                lista.add(new BeanDistritoMunicipio(rs.getInt("id"), rs.getBoolean("estatus"), distrito, municipio));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.consultarDistritoMunicipiosId()Error: " + ex.getMessage());
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
                System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.consultarDistritoMunicipiosId()Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    public boolean eliminarDistritoMunicipio(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("DELETE FROM distrito_municipio WHERE id = ?");
            pstm.setInt(1, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.eliminarDistritoMunicipio()Error: " + ex.getMessage());
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
                System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.eliminarDistritoMunicipio()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean registrarDistritoMunicipio(int idDistrito, int idMunicipio) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("INSERT INTO distrito_municipio (distrito_id, municipio_id) VALUES (?, ?);");
            pstm.setInt(1, idDistrito);
            pstm.setInt(2, idMunicipio);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.registrarDistritoMunicipio()Error: " + ex.getMessage());
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
                System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.registrarDistritoMunicipio()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean existeMunicipioDistrito(int idMunicipio, int idDistrito) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT id FROM distrito_municipio WHERE distrito_id = ? AND municipio_id = ?;");
            pstm.setInt(1, idDistrito);
            pstm.setInt(2, idMunicipio);
            rs = pstm.executeQuery();
            if (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.existeMunicipioDistrito()Error: " + ex.getMessage());
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
                System.out.println("modelo.distritomunicipio.DaoDistritoMunicipio.existeMunicipioDistrito()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

}
