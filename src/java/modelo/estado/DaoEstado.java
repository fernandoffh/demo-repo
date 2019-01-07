/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estado;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.entrevista.BeanEntrevista;
import modelo.municipio.BeanMunicipio;
import modelo.persona.BeanPersona;
import modelo.usuario.BeanUsuario;
import utilidades.ConexionBD;
import utilidades.Herramientas;

/**
 *
 * @author CDS-
 */
public class DaoEstado {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    private List<BeanEstado> estados;
    private List<BeanEstado> list;

    public List<BeanEstado> consultarEstadosConMunicipios() {
        estados = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_ESTADO);
            rs = cstm.executeQuery();
            while (rs.next()) {
                estados.add(new BeanEstado(rs.getInt("id"), rs.getString("nombre"), rs.getInt("codigo"), consultarMunicipiosEstado(rs.getInt("id"))));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.consultarEstadosConMunicipios()Error: " + ex.getMessage());
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
                System.out.println("modelo.estado.DaoEstado.consultarEstadosConMunicipios()Error: " + ex.getMessage());
            }
        }
        return estados;
    }

    public List<BeanMunicipio> consultarMunicipiosEstado(int id) {
        List<BeanMunicipio> municipios = new ArrayList();
        ResultSet Krs = null;
        PreparedStatement Kpstm = null;
        CallableStatement Kcstm = null;
        Connection Kcon = null;

        try {
            Kcon = ConexionBD.getConexion();
            Kcstm = Kcon.prepareCall(Herramientas.CONSULTAR_MUNICIPIOS_ESTADO);
            Kcstm.setInt(1, id);
            Krs = Kcstm.executeQuery();
            while (Krs.next()) {
                municipios.add(new BeanMunicipio(Krs.getInt("id"), Krs.getString("nombre"), Krs.getInt("codigo"), null, Krs.getBoolean("estatus")));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.consultarEstadosConMunicipios()Error: " + ex.getMessage());
        } finally {
            try {
                if (Kcon != null) {
                    Kcon.close();
                }
                if (Kcstm != null) {
                    Kcstm.close();
                }
                if (Krs != null) {
                    Krs.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.consultarEstadosConMunicipios()Error: " + ex.getMessage());
            }
        }
        return municipios;
    }

    public List<BeanEstado> consultarEstados() {
        List<BeanEstado> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM estado");
            rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(new BeanEstado(rs.getInt("id"), rs.getString("nombre"), rs.getInt("codigo"), rs.getInt("cantidad_distritos"), rs.getBoolean("estatus"), null));
            }
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.consultarEstados()Error: " + ex.getMessage());
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
                System.out.println("modelo.estado.DaoEstado.consultarEstados()Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    public boolean cambiarEstatusEstado(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("UPDATE estado SET estatus = ? WHERE id = ?;");
            pstm.setBoolean(1, !consultarEstatus(id));
            pstm.setInt(2, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.cambiarEstatusEstado()Error: " + ex.getMessage());
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
                System.out.println("modelo.estado.DaoEstado.cambiarEstatusEstado()Error: " + ex.getMessage());
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
            Fpstm = Fcon.prepareStatement("SELECT estatus FROM estado WHERE id = ?");
            Fpstm.setInt(1, id);
            Frs = Fpstm.executeQuery();
            if (Frs.next()) {
                respuesta = Frs.getBoolean("estatus");
            }
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.consultarEstatus()Error: " + ex.getMessage());
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
                System.out.println("modelo.estado.DaoEstado.consultarEstatus()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean registrarEstado(BeanEstado estado) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.REGISTRAR_ESTADO);
            cstm.setString(1, estado.getNombre());
            cstm.setInt(2, estado.getCodigo());
            cstm.setInt(3, estado.getCantidadDistritos());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.registrarEstado()Error: " + ex.getMessage());
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
                System.out.println("modelo.estado.DaoEstado.registrarEstado()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public BeanEstado consultarUnEstado(int id) {
        BeanEstado estado = null;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("SELECT * FROM estado WHERE id = ?;");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                estado = new BeanEstado(rs.getInt("id"), rs.getString("nombre"), rs.getInt("codigo"), rs.getInt("cantidad_distritos"), rs.getBoolean("estatus"), null);
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
        return estado;
    }

    public boolean actualizarEstado(BeanEstado estado) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("UPDATE estado SET nombre = ?, cantidad_distritos = ?, codigo = ? WHERE id = ?;");
            pstm.setString(1, estado.getNombre());
            pstm.setInt(2, estado.getCantidadDistritos());
            pstm.setInt(3, estado.getCodigo());
            pstm.setInt(4, estado.getId());
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.actualizarEstado()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.actualizarEstado()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public boolean eliminarEstado(int id) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement("DELETE FROM estado WHERE id = ?;");
            pstm.setInt(1, id);
            respuesta = pstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.eliminarEstado()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.eliminarEstado()Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public List<BeanMunicipio> consultarMunicipiosAsignadosAdistritos(int idDistrito, int idEstado) {
        List<BeanMunicipio> municipios = new ArrayList();
        String consulta = "select municipio.*, distrito_municipio.id as id_distrito_municipio "
                    + "from distrito_municipio right join municipio on distrito_municipio.municipio_id = municipio.id "
                    + "inner join distrito on distrito.id = distrito_municipio.distrito_id "
                    + "where distrito.id = "+idDistrito+" and municipio.estado_id = "+idEstado+";";
        try {
            System.out.println("consultat: "+consulta);
            con = ConexionBD.getConexion();
            pstm = con.prepareStatement(consulta);
//            pstm.setInt(1, idDistrito);
//            pstm.setInt(2, idEstado);
            rs = pstm.executeQuery();
            while (rs.next()) {
                BeanMunicipio municipio = new BeanMunicipio(rs.getInt("id"), rs.getString("nombre"), rs.getInt("codigo"), null, false);
                municipio.setIdDistritoMunicipio(rs.getInt("id_distrito_municipio"));
                System.out.println("modelo.estado.DaoEstado.consultarMunicipiosAsignadosAdistritos() :"+municipio.getNombre());
                System.out.println("modelo.estado.DaoEstado.consultarMunicipiosAsignadosAdistritos()  rs.getInt(\"id_distrito_municipio\"):"+rs.getInt("id_distrito_municipio"));
                municipios.add(municipio);
            }
        } catch (SQLException ex) {
            System.out.println("modelo.estado.DaoEstado.consultarMunicipiosAsignadosAdistritos()Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.estado.DaoEstado.consultarMunicipiosAsignadosAdistritos()Error: " + ex.getMessage());
            }
        }
        municipios = filtroDistritos(municipios, idEstado);
        return municipios;
    }

    private List<BeanMunicipio> filtroDistritos(List<BeanMunicipio> municipios, int idEstado) {
        List<BeanMunicipio> nuevaLista = consultarMunicipiosEstado(idEstado);
        for(BeanMunicipio municipio : nuevaLista){
            boolean encontrado = false;
            int idDistritoMunicipio = 0;
            for(BeanMunicipio bean : municipios){
                if (bean.getId() == municipio.getId()) {
                    encontrado = true;
                    idDistritoMunicipio = bean.getIdDistritoMunicipio();
                }
            }
            municipio.setEstatus(encontrado);
            municipio.setIdDistritoMunicipio(idDistritoMunicipio);
        }
        return nuevaLista;
    }

   

}
