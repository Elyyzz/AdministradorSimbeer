package com.example.elyzzbarrueta.administrador.peticiones.entity;

/**
 * Created by Elyzz Barrueta on 20/11/2015.
 */
public class pedido {
    private int id;
    private String nomCliente;
    private String estatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
