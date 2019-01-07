/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.municipio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entrevista.BeanEntrevista;
import modelo.estado.BeanEstado;
import modelo.municipio.BeanMunicipio;
import modelo.persona.BeanPersona;
import modelo.usuario.BeanUsuario;
import utilidades.ConexionBD;
import utilidades.Herramientas;

/**
 *
 * @author alex
 */
public class DaoMunicipio {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public List<BeanMunicipio> consultarMunicipios() {
        List<BeanMunicipio> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM municipio;");
            rs = pstm.executeQuery();
            while (rs.next()) {
                BeanEstado estado = new BeanEstado();
                estado.setId(rs.getInt("estado_id"));
                lista.add(new BeanMunicipio(rs.getInt("id"), rs.getString("nombre"), rs.getInt("codigo"), estado, rs.getBoolean("estatus")));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.municipio.DaoMunicipio.consultarMunicipios() Error: " + ex.getMessage());
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
                System.out.println("modelo.municipio.DaoMunicipio.consultarMunicipios() Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    public boolean cambiarEstatusMunicipio(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("UPDATE municipio SET estatus = ? WHERE id = ?;");
            pstm.setBoolean(1, !consultarEstatus(id));
            pstm.setInt(2, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.municipio.DaoMunicipio.cambiarEstatusMunicipio()Error: " + ex.getMessage());
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
                System.out.println("modelo.municipio.DaoMunicipio.cambiarEstatusMunicipio()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    private boolean consultarEstatus(int id) {
        Connection Fcon = null;
        ResultSet Frs = null;
        PreparedStatement Fpstm = null;
        boolean respuesta = false;
        try {
            Fcon = ConexionBD.getConexion();
            Fpstm = Fcon.prepareStatement("SELECT estatus FROM municipio WHERE id = ?");
            Fpstm.setInt(1, id);
            Frs = Fpstm.executeQuery();
            if (Frs.next()) {
                respuesta = Frs.getBoolean("estatus");
            }
        } catch (SQLException ex) {
            System.out.println("modelo.municipio.DaoMunicipio.consultarEstatus())Error: " + ex.getMessage());
        } finally {
            try {
                if (Fcon != null) {
                    Fcon.close();
                }
                if (Fpstm != null) {
                    Fpstm.close();
                }
                if (Frs != null) {
                    Frs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.municipio.DaoMunicipio.consultarEstatus())Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public BeanMunicipio consultarMunicipio(int id) {
        BeanMunicipio municipio = null;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM municipio WHERE id = ?;");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                municipio =   new BeanMunicipio(rs.getInt("id"), rs.getString("nombre"), rs.getInt("codigo"), null, rs.getBoolean("estatus"));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.consultarUnEstado()Error: " + ex.getMessage());
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
                System.out.println("modelo.estado.DaoEstado.consultarUnEstado()Error: " + ex.getMessage());
            }
        }
        return municipio;
    }

    public boolean acualizarMunicipio(BeanMunicipio municipio) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("UPDATE municipio SET nombre = ?,  codigo = ? WHERE id = ?;");
            pstm.setString(1, municipio.getNombre());
            pstm.setInt(2, municipio.getCodigo());
            pstm.setInt(3, municipio.getId());
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.acualizarMunicipio()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.acualizarMunicipio()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean eliminarMunicipio(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("DELETE FROM municipio WHERE id = ?;");
            pstm.setInt(1, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.eliminarMunicipio()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.eliminarMunicipio()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean registrarMunicipio(BeanMunicipio municipio) {
boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("INSERT INTO municipio (nombre, codigo, estado_id) VALUES (?, ?, ?);");
            pstm.setString(1, municipio.getNombre());
            pstm.setInt(2, municipio.getCodigo());
            pstm.setInt(3, municipio.getEstado().getId());
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
                System.out.println("modelo.municipio.DaoMunicipio.registrarMunicipio()" + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.municipio.DaoMunicipio.registrarMunicipio()" + ex.getMessage());
            }
        }
        return respuesta;
    }

}
