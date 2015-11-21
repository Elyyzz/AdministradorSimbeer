/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.demo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import webService_DAO.queryAdminstrador;

/**
 *
 * @author Elyzz Barrueta
 */
@Path("/getPedidos")
public class getPedidos {

    @GET
    @Path("listaPedidos")
    @Produces({MediaType.APPLICATION_JSON})
    public String listaPedidos() {
        queryAdminstrador adm = new queryAdminstrador();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        String query = "SELECT DISTINCT ped.IDPEDIDO, cli.NOMBRECLI, ped.ESTATUS \n"
                + "FROM PEDIDOS_TAB ped, PRODUCTO_TAB prod, DETALLEPED_TAB det, CLIENTE_TAB cli\n"
                + "WHERE ped.IDPEDIDO=det.IDPEDIDO\n"
                + "AND prod.IDPRODUCTO=det.IDPRODUCTO\n"
                + "AND cli.IDCLIENTE=det.IDCLIENTE";
        jsonArrayBuilder = adm.getPedidos("simbeer", "s1mb33r", query);
        return jsonArrayBuilder.build().toString();
    }

    @GET
    @Path("pedidosEntregados")
    @Produces({MediaType.APPLICATION_JSON})
    public String pedidosEntregados() {
        queryAdminstrador adm = new queryAdminstrador();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        String query = "SELECT DISTINCT ped.IDPEDIDO, cli.NOMBRECLI, ped.ESTATUS \n"
                + "FROM PEDIDOS_TAB ped, PRODUCTO_TAB prod, DETALLEPED_TAB det, CLIENTE_TAB cli\n"
                + "WHERE ped.IDPEDIDO=det.IDPEDIDO\n"
                + "AND prod.IDPRODUCTO=det.IDPRODUCTO\n"
                + "AND cli.IDCLIENTE=det.IDCLIENTE\n"
                + "AND ped.ESTATUS='ENTREGADO'";
        jsonArrayBuilder = adm.getPedidos("simbeer", "s1mb33r", query);
        return jsonArrayBuilder.build().toString();
    }

    @GET
    @Path("pedidosPendientes")
    @Produces({MediaType.APPLICATION_JSON})
    public String pedidosPendientes() {
        queryAdminstrador adm = new queryAdminstrador();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        String query = "SELECT DISTINCT ped.IDPEDIDO, cli.NOMBRECLI, ped.ESTATUS \n"
                + "FROM PEDIDOS_TAB ped, PRODUCTO_TAB prod, DETALLEPED_TAB det, CLIENTE_TAB cli\n"
                + "WHERE ped.IDPEDIDO=det.IDPEDIDO\n"
                + "AND prod.IDPRODUCTO=det.IDPRODUCTO\n"
                + "AND cli.IDCLIENTE=det.IDCLIENTE\n"
                + "AND ped.ESTATUS='PENDIENTE'";
        jsonArrayBuilder = adm.getPedidos("simbeer", "s1mb33r", query);
        return jsonArrayBuilder.build().toString();
    }

    @GET
    @Path("pedidosCancelados")
    @Produces({MediaType.APPLICATION_JSON})
    public String pedidosCancelados() {
        queryAdminstrador adm = new queryAdminstrador();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        String query = "SELECT DISTINCT ped.IDPEDIDO, cli.NOMBRECLI, ped.ESTATUS \n"
                + "FROM PEDIDOS_TAB ped, PRODUCTO_TAB prod, DETALLEPED_TAB det, CLIENTE_TAB cli\n"
                + "WHERE ped.IDPEDIDO=det.IDPEDIDO\n"
                + "AND prod.IDPRODUCTO=det.IDPRODUCTO\n"
                + "AND cli.IDCLIENTE=det.IDCLIENTE\n"
                + "AND ped.ESTATUS='CANCELADO'";
        jsonArrayBuilder = adm.getPedidos("simbeer", "s1mb33r", query);
        return jsonArrayBuilder.build().toString();
    }

    @GET
    @Path("detallePedido")
    @Produces({MediaType.APPLICATION_JSON})
    public String detallePedido(@QueryParam("idPedido") int id) {
        queryAdminstrador adm = new queryAdminstrador();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        String query = "SELECT det.CANTIDAD, det.DESCRIPCION, prod.NOMBRE\n"
                + "FROM PEDIDOS_TAB ped, PRODUCTO_TAB prod, DETALLEPED_TAB det, CLIENTE_TAB cli\n"
                + "WHERE ped.IDPEDIDO=det.IDPEDIDO\n"
                + "AND prod.IDPRODUCTO=det.IDPRODUCTO\n"
                + "AND cli.IDCLIENTE=det.IDCLIENTE\n"
                + "AND ped.IDPEDIDO=" + id;
        jsonArrayBuilder = adm.getPedidos("simbeer", "s1mb33r", query);
        return jsonArrayBuilder.build().toString();
    }

}
