package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by Elyzz Barrueta on 16/11/2015.
 */
public class Conexion {

    public static String ERROR = "";

    public Conexion() {
    }

    public static Connection getConnection(String usuario, String password) {
        Connection conexion = null;
        String servidor = "jdbc:oracle:thin:@192.168.40.103:1521:sia";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conexion = DriverManager.getConnection(servidor, usuario, password);
            return conexion;
        } catch (ClassNotFoundException var9) {
            ERROR = var9.getMessage();
            System.out.println("Error **************** "+ERROR);
            conexion = null;
            return conexion;
        } catch (SQLException var10) {
            //ERROR = ErrorMensaje.translateError(var10, "Conexión", "Conexión");
            System.out.println("Error **************** "+ERROR);

            conexion = null;
            return conexion;
        } finally {
            ;
        }
    }

    public static void cerrarConexion(Connection conn) {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException var2) {
            //ERROR = ErrorMensaje.translateError(var2, "Conexión", "Conexión");
            System.out.println("Error **************** "+ERROR);
        }

    }
}
