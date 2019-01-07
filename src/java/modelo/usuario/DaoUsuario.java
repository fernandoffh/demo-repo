/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.persona.BeanPersona;
import modelo.rol.BeanRol;
import utilidades.ConexionBD;
import utilidades.Herramientas;

/**
 *
 * @author alex
 */
public class DaoUsuario {

    private Connection con;
    private ResultSet rs;
    private ArrayList list;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public boolean registrarUsuarioAdministrador(BeanUsuario user) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.REGISTRAR_USUARIO_ADMINISTRADOR);
            cstm.setString(1, user.getPersona().getNombre());
            cstm.setString(2, user.getPersona().getApellidoPaterno());
            cstm.setString(3, user.getPersona().getApellidoMaterno());
            cstm.setString(4, user.getCorreo());
            cstm.setString(5, user.getContrasena());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.registrarUsuario() Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.usuario.DaoUsuario.registrarUsuario() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean registrarPromotor(BeanUsuario user) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.REGISTRAR_PROMOTOR);
            cstm.setString(1, user.getPersona().getNombre());
            cstm.setString(2, user.getPersona().getApellidoPaterno());
            cstm.setString(3, user.getPersona().getApellidoMaterno());
            cstm.setString(4, user.getCorreo());
            cstm.setString(5, user.getContrasena());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.registrarPromotor() Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.usuario.DaoUsuario.registrarPromotor() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean correoDisponible(String correo) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CORREO_DISPONIBLE);
            cstm.setString(1, correo);
            rs = cstm.executeQuery();
            if (rs.next()) {
                System.out.println("correo: " + rs.getString("correo"));
                respuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.correoDisponible() Error: " + ex.getMessage());
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
                System.out.println("modelo.usuario.DaoUsuario.correoDisponible() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean asignarCodigoRecuperacion(String codigo, String correo) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.ASIGNAR_CODIGO_RECUPERACION);
            cstm.setString(1, codigo);
            cstm.setString(2, correo);
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.asignarCodigoRecuperacion() Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.usuario.DaoUsuario.asignarCodigoRecuperacion() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean existeCodigo(String codigoRecuperacion) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.EXISTE_CODIGO_RECUPERACION);
            cstm.setString(1, codigoRecuperacion);
            rs = cstm.executeQuery();
            if (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.existeCodigo() Error: " + ex.getMessage());
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
                System.out.println("modelo.usuario.DaoUsuario.existeCodigo() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean cambiarContrasena(String contrasena, String codigo) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CAMBIAR_CONTRASENA);
            cstm.setString(1, contrasena);
            cstm.setString(2, codigo);
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.cambiarContrasena() Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.usuario.DaoUsuario.cambiarContrasena() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public BeanUsuario iniciarSesion(String correo, String contrasena) {
        BeanUsuario usuario = null;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.INICIAR_SESION);
            cstm.setString(1, correo);
            cstm.setString(2, contrasena);
            rs = cstm.executeQuery();
            if (rs.next()) {
                BeanRol rol = new BeanRol(rs.getInt("idRol"), rs.getString("rol"), rs.getString("descripcion"));
                BeanPersona persona = new BeanPersona(rs.getInt("idpersona"), rs.getString("nombre"), rs.getString("apellido_paterno"), rs.getString("apellido_materno"));
                usuario = new BeanUsuario(rs.getInt("idUsuario"), rs.getString("correo"), rs.getString("contrasena"), "", rs.getBoolean("estatus"), rol, persona);
            }
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.iniciarSesion() Error: " + ex.getMessage());
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
                System.out.println("modelo.usuario.DaoUsuario.iniciarSesion() Error: " + ex.getMessage());
            }
        }
        return usuario;
    }

    public boolean actualizarDatosUsuario(BeanUsuario user) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.ACTUALIZAR_DATOS_USUARIO);
            cstm.setString(1, user.getPersona().getNombre());
            cstm.setString(2, user.getPersona().getApellidoPaterno());
            cstm.setString(3, user.getPersona().getApellidoMaterno());
            cstm.setString(4, user.getCorreo());
            cstm.setInt(5, user.getPersona().getId());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.actualizarDatosUsuario() Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.usuario.DaoUsuario.actualizarDatosUsuario() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public List<BeanUsuario> consultarPromotores() {
        List<BeanUsuario> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_PROMOTORES);
            rs = cstm.executeQuery();
            while (rs.next()) {
                BeanPersona persona = new BeanPersona(rs.getInt("id_persona"), rs.getString("nombre"), rs.getString("apellido_paterno"), rs.getString("apellido_materno"));
                lista.add(new BeanUsuario(rs.getInt("id_usuario"), rs.getString("correo"), rs.getString("contrasena"), "0", rs.getBoolean("estatus"), null, persona));
            }
        } catch (SQLException ex) {
            System.out.println("\"modelo.usuario.DaoUsuario.consultarPromotores()Error: " + ex.getMessage());
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
                System.out.println("\"modelo.usuario.DaoUsuario.consultarPromotores()Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    public boolean cambiarEstatusPromotor(int id) {
        boolean respuesta = false;
        boolean estatus = consultarEstatusPromotor(id);
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CAMBIAR_ESTATUS_PROMOTOR);
            cstm.setBoolean(1, !estatus);
            cstm.setInt(2, id);
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("\"modelo.usuario.DaoUsuario.cambiarEstatusPromotor()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("\"modelo.usuario.DaoUsuario.cambiarEstatusPromotor()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean consultarEstatusPromotor(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_ESTATUS_PROMOTOR);
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

    public boolean cambiarMiContrasena(String contrasena, int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CAMBIAR_MI_CONTRASENA);
            cstm.setString(1, contrasena);
            cstm.setInt(2, id);
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.encuesta.DaoEncuesta.cambiarMiContrasena()Error: " + ex.getMessage());
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
                System.out.println("modelo.encuesta.DaoEncuesta.cambiarMiContrasena()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean eliminarUsuario(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement(Herramientas.ELIMINAR_USUARIO);
            pstm.setInt(1, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.usuario.DaoUsuario.eliminarUsuario()Error: " + ex.getMessage());
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
                System.out.println("modelo.usuario.DaoUsuario.eliminarUsuario()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

}
