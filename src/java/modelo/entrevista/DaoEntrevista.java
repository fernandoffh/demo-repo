/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entrevista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.distrito.BeanDistrito;
import modelo.municipio.BeanMunicipio;
import modelo.persona.BeanPersona;
import modelo.rol.BeanRol;
import modelo.usuario.BeanUsuario;
import utilidades.ConexionBD;
import utilidades.Herramientas;

/**
 *
 * @author alex
 */
public class DaoEntrevista {

    private Connection con;
    private ResultSet rs;
    private ArrayList list;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    public boolean registrarEntrevista(BeanEntrevista entrevista) {
        boolean respuesta = false;
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.REGISTRAR_ENTREVISTA);
            cstm.setString(1, entrevista.getFechaRegistro());
            cstm.setInt(2, entrevista.getSeccion());
            cstm.setString(3, entrevista.getTipoCasilla()); 
            cstm.setString(4, entrevista.getSexo());
            cstm.setString(5, entrevista.getEdad());
            cstm.setInt(6, entrevista.getVotoMujer());
            cstm.setInt(7, entrevista.getDistritoMunicipio().getId());
            cstm.setInt(8, entrevista.getUsuario().getId());
            cstm.setInt(9, entrevista.getEncuesta().getId());
            respuesta = cstm.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.out.println("modelo.entrevista.registrarEntrevista() Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (cstm != null) {
                    cstm.close();
                }
            } catch (SQLException ex) {
                System.out.println("modelo.entrevista.registrarEntrevista() Error: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    public List<BeanEntrevista> consultarEntrevistasDeEncuesta(int id) {
          List<BeanEntrevista> lista = new ArrayList();
        try {
            con = ConexionBD.getConexion();
            cstm = con.prepareCall(Herramientas.CONSULTAR_ENTREVISTAS_ENCUESTA);
            cstm.setInt(1, id);
            rs = cstm.executeQuery();
            while (rs.next()) {
                 BeanEntrevista entrevista = new BeanEntrevista();
                 entrevista.setId(rs.getInt("id"));
                 entrevista.setFechaRegistro(rs.getString("fecha_registro"));
                 entrevista.setSeccion(rs.getInt("seccion"));
                 entrevista.setTipoCasilla(rs.getString("tipo_casilla"));
                 entrevista.setSexo(rs.getString("sexo"));
                 entrevista.setEdad(rs.getString("edad"));
                 entrevista.setVotoMujer(rs.getInt("votoMujer"));
                 
                 BeanMunicipio municipio = new BeanMunicipio();
                 municipio.setNombre(rs.getString("nombre_municipio"));
                 municipio.setCodigo(rs.getInt("codigo_municipio"));
                 
                 entrevista.setMunicipio(municipio);
                 
                 entrevista.setDistrito(new BeanDistrito(0, rs.getInt("codigo_distrito")));
                 
                 String nombreCompleto = rs.getString("nombre_promotor")+" "+rs.getString("apellido_paterno_promotor")+" "+rs.getString("apellido_materno_promotor");
                 BeanPersona persona = new BeanPersona();
                 persona.setNombre(nombreCompleto);
                 BeanUsuario usuario = new BeanUsuario();
                 usuario.setPersona(persona);
                 
                 entrevista.setUsuario(usuario);
                 
                 lista.add(entrevista);
                 
            }
        } catch (SQLException ex) {
            System.out.println("\"modelo.usuario.DaoUsuario.consultarEntrevistasDeEncuesta()Error: " + ex.getMessage());
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
                System.out.println("\"modelo.usuario.DaoUsuario.consultarEntrevistasDeEncuesta()Error: " + ex.getMessage());
            }
        }
        return lista;
    }

  

  
}
