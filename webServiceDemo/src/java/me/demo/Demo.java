/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.demo;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import me.demo.entitidades.Pedido;
import me.demo.entitidades.Producto;
import webService_DAO.queryAdminstrador;

/**
 *
 * @author giovanni
 */
@Path("/demo")
public class Demo {

    private List<Pedido> pedidos = new ArrayList<>();

    @GET
    @Path("listaproductos")
    @Produces({MediaType.APPLICATION_JSON})
    public String listaProductos() {
        queryAdminstrador adm = new queryAdminstrador();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        jsonArrayBuilder = adm.getProductos("simbeer", "s1mb33r");
        return jsonArrayBuilder.build().toString();
    }

    @GET
    @Path("nuevopedido")
    @Produces({MediaType.APPLICATION_JSON})
    public String nuevoPedido(@QueryParam("id") int id, @QueryParam("descripcion") String descripcion) {

        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setDescripcion(descripcion);

        System.out.println("Agregando pedido: " + pedidos.size());
        pedidos.add(pedido);
        System.out.println("Pedidos: " + pedidos.size());
        return pedido.toJson().toString();
    }

}
