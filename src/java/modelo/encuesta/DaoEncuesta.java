/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.encuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import modelo.entrevista.BeanEntrevista;
import modelo.persona.BeanPersona;
import modelo.rol.BeanRol;
import modelo.tipopregunta.BeanTipoPregunta;
import modelo.usuario.BeanUsuario;
import utilidades.ConexionBD;
import utilidades.Herramientas;

/**
 *
 * @author alex
 */
public class DaoEncuesta {

    private Connection con;
    private ResultSet rs;
    private List<BeanEncuesta> list;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public List<BeanEncuesta> consultarEncuestas() {
        list = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement(Herramientas.CONSULTAR_ENCUESTAS);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new BeanEncuesta(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("fecha_creacion"), rs.getBoolean("estatus")));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.consultarEncuestas()Error: " + ex.getMessage());
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
                System.out.println("modelo.encuesta.DaoEncuesta.consultarEncuestas()Error: " + ex.getMessage());
            }
        }
        return list;
    }

    public boolean cambiarEstatusEncuesta(int id) {
        boolean respuesta = false;
        boolean estatus = consultarEstatusEncuesta(id);
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CAMBIAR_ESTATUS_ENCUESTA);
            cstm.setInt(1, id);
            cstm.setBoolean(2, !estatus);
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.cambiarEstatusEncuesta()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.encuesta.DaoEncuesta.cambiarEstatusEncuesta()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public BeanEncuesta consultarUnaEncuesta(int id) {
        BeanEncuesta encuesta = null;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_UNA_ENCUESTA);
            cstm.setInt(1, id);
            rs = cstm.executeQuery();
            if (rs.next()) {
                encuesta = new BeanEncuesta(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("fecha_creacion"), rs.getBoolean("estatus"));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.consultarUnaEncuesta()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.encuesta.DaoEncuesta.consultarUnaEncuesta()Error: " + ex.getMessage());
            }
        }
        return encuesta;
    }

    public boolean acualizarEncuesta(BeanEncuesta encuesta) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.ACTUALIZAR_ENCUESTA);
            cstm.setString(1, encuesta.getNombre());
            cstm.setString(2, encuesta.getDescripcion());
            cstm.setInt(3, encuesta.getId());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.acualizarEncuesta()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.encuesta.DaoEncuesta.acualizarEncuesta()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean registarEncuesta(BeanEncuesta encuesta) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.REGISTRAR_ENCUESTA);
            cstm.setString(1, encuesta.getNombre());
            cstm.setString(2, encuesta.getDescripcion());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.registarEncuesta()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.encuesta.DaoEncuesta.registarEncuesta()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean consultarEstatusEncuesta(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_ESTATUS_ENCUESTA);
            cstm.setInt(1, id);
            rs = cstm.executeQuery();
            if (rs.next()) {
                respuesta = rs.getBoolean("estatus");
            }
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.consultarEstatusEncuesta()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.encuesta.DaoEncuesta.consultarEstatusEncuesta()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public List<BeanEncuesta> consultarEncuestasActivas() {
        list = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement(Herramientas.CONSULTAR_ENCUESTAS_ACTIVAS);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new BeanEncuesta(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("fecha_creacion"), rs.getBoolean("estatus")));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.consultarEncuestasActivas()Error: " + ex.getMessage());
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
                System.out.println("modelo.encuesta.DaoEncuesta.consultarEncuestasActivas()Error: " + ex.getMessage());
            }
        }
        return list;
    }

    public BeanEncuesta consultaGeneralUnaEncuesta(int id) {
        BeanEncuesta encuesta = consultarUnaEncuesta(id);
        List<BeanEntrevista> entrevistas = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_GENERAL_ENCUESTA);
            cstm.setInt(1, id);
            rs = cstm.executeQuery();
            while (rs.next()) {
                BeanPersona persona = new BeanPersona(0, rs.getString("nombre_persona"), rs.getString("apellido_parterno_persona"), rs.getString("apellido_materno_persona"));
                BeanUsuario user = new BeanUsuario();
                user.setPersona(persona);
                BeanEntrevista entrevista = new BeanEntrevista(rs.getInt("id_entrevista"), rs.getString("fecha_registro_entrevista"), user);
                entrevistas.add(entrevista);
            }
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.consultarEncuestas()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.encuesta.DaoEncuesta.consultarEncuestas()Error: " + ex.getMessage());
            }
        }
        return encuesta;
    }

    public boolean eliminarEncuesta(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement(Herramientas.ELIMINAR_ENCUESTA);
            pstm.setInt(1, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.eliminarEncuesta()Error: " + ex.getMessage());
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
                System.out.println("modelo.encuesta.DaoEncuesta.eliminarEncuesta()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean existeSentencia(String consulta) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement(consulta);
            rs = pstm.executeQuery();
            respuesta = rs.next();
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.existeSentencia() error: "+ex.getMessage());
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
            System.out.println("modelo.encuesta.DaoEncuesta.existeSentencia() error: "+ex.getMessage());
            }
        }
        return respuesta;
    }

}
