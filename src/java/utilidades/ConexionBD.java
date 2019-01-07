package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    private static Connection contacto = null;

    public static Connection getConexion() {
        
  //      String url = "jdbc:mysql://node16706-env-2670408.jelastic.saveincloud.net:3306/ObservacionCIDHAL";
        String url = "jdbc:mysql://localhost:3306/ObservacionCIDHAL";
        try {
            Class.forName("com.mysql.jdbc.Driver");
 //           contacto = DriverManager.getConnection(url, "root", "ugiYbawQXm");
            contacto = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            System.out.println("Error de conexion con la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("utilidades.ConexionBD.getConexion() Error de classForName: "+ex.getMessage());
        }
        return contacto;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = getConexion();
        if (con!= null) {
            con.close();
            System.out.println("Conexion exitosa");
        }
    }
}
