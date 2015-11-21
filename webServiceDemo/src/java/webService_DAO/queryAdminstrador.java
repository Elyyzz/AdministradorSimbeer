/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webService_DAO;

import itt.web.conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import me.demo.entitidades.Producto;

/**
 *
 * @author Elyzz Barrueta
 */
public class queryAdminstrador {

    private ResultSet result;

    public JsonArrayBuilder getProductos(String user, String pass) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();//arreglo con todos los obj
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();;//objeto
        Conexion conexion = new Conexion();
        Connection con = Conexion.getConnection(user, pass);
        String query;
        Statement sentencia;
        if (con != null) {
            try {
                query = "SELECT * FROM SIMBEER.PRODUCTO_TAB";
                sentencia = con.createStatement();
                result = sentencia.executeQuery(query);
                while (result.next()) {
                    jsonBuilder.add("idProducto", result.getInt(1));
                    jsonBuilder.add("descripcion", result.getString(2));
                    jsonBuilder.add("nombre", result.getString(3));
                    jsonBuilder.add("precio", result.getInt(4));
                    jsonArrayBuilder.add(jsonBuilder);
                }
            } catch (SQLException ex) {
                jsonArrayBuilder = null;
            }
        }
        return jsonArrayBuilder;
    }

    public JsonArrayBuilder getPedidos(String user, String pass, String query) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();//arreglo con todos los obj
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();;//objeto
        Conexion conexion = new Conexion();
        Connection con = Conexion.getConnection(user, pass);
        Statement sentencia;
        if (con != null) {
            try {
                sentencia = con.createStatement();
                result = sentencia.executeQuery(query);
                while (result.next()) {
                    jsonBuilder.add("idPedido", result.getInt(1));
                    jsonBuilder.add("nomCliente", result.getString(2));
                    jsonBuilder.add("estatus", result.getString(3));
                    jsonArrayBuilder.add(jsonBuilder);
                }
            } catch (SQLException ex) {
                jsonArrayBuilder = null;
            }
        }
        return jsonArrayBuilder;
    }

   

}
