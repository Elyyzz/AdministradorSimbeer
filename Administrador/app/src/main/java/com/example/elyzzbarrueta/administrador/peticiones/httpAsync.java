package com.example.elyzzbarrueta.administrador.peticiones;


import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.elyzzbarrueta.administrador.peticiones.entity.pedido;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Elyzz Barrueta on 18/11/2015.
 */
public class httpAsync {

    public ArrayList<pedido> getPedidos(String metodo){
        AsyncHttpClient client = new AsyncHttpClient();
         final ArrayList<pedido> pedidosAll=new ArrayList<pedido>();
        client.get("http://192.168.0.128/api/getPedidos/"+metodo, new JsonHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        pedidosAll.clear();
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray timeline) {
                        System.out.println("**************** Success :) ");
                        System.out.println(timeline);

                        pedido pedido;
                        try {
                            for(int i=0;  i<timeline.length(); i++){
                                pedido= new pedido();
                                JSONObject obj=timeline.getJSONObject(1);
                                pedido.setId(obj.getInt("idPedido"));
                                pedido.setNomCliente(obj.getString("nomCliente"));
                                pedido.setEstatus(obj.getString("estatus"));
                                pedidosAll.add(pedido);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                }
        );
        return pedidosAll;
    }
}
