package com.example.elyzzbarrueta.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elyzzbarrueta.administrador.peticiones.entity.pedido;
import com.example.elyzzbarrueta.administrador.peticiones.httpAsync;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;


public class Main2Activity extends AppCompatActivity {

    Button  acesar;
    EditText user;
    EditText pass;

    final ArrayList<pedido> pedidosAll=new ArrayList<pedido>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        acesar=(Button) findViewById(R.id.buttonAcceder);
        user=(EditText) findViewById(R.id.editName);
        pass=(EditText) findViewById(R.id.editPass);


        acesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = user.getText().toString().trim();
                String pwd = pass.getText().toString().trim();
                if (us.equals("Administrador") && pwd.equals("BeerAdm")) {
                    Intent menu = new  Intent(Main2Activity.this, MainActivity.class);
                    startActivity(menu);
                } else {
                    verToa("Usuario y/o Password incorrecto");
                    pass.setText("");
                }
            }
        });
    }


    public void prueba() throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("notes", "Test api support");
        StringEntity entity = new StringEntity(jsonParams.toString());
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        final RequestHandle get = client.post(this, "http://192.168.0.104/android/insert.php",  entity, "application/json", new TextHttpResponseHandler() {

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        System.out.println("--------------------"+responseString);
                        System.out.println("*************Failed");


                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        String he=headers.toString();
                        System.out.println("********** he"+he);
                        if (statusCode == 200) {
                            String rest = new String(responseString);
                            System.out.println("***********Success " + rest);
                            Toast.makeText(Main2Activity.this, "todo ok" + rest, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }

    public void verToa(String  text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Intent acerca= new Intent(Main2Activity.this, acercade.class);
                startActivity(acerca);
                break;
            case R.id.action_salir:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void getPedidos(String metodo){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        client.get("http://simbeer.byethost22.com/hola.php", params, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                System.out.println("**************** Failed :'( ");
                System.out.println("******** Code "+statusCode);
                pedidosAll.clear();
            }
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray timeline) {
                System.out.println("**************** Success :) ");
                System.out.println(timeline);
                JSONArray jsonArray = timeline;
                try {
                    for (int i = 0; i < timeline.length(); i++) {
                        pedido pedido = new pedido();
                        JSONObject obj = jsonArray.getJSONObject(1);
                        pedido.setId(obj.getInt("idPedido"));
                        pedido.setNomCliente(obj.getString("nomCliente"));
                        pedido.setEstatus(obj.getString("estatus"));
                        pedidosAll.add(pedido);
                        System.out.println(pedidosAll.size());
                        Bundle  b= new Bundle();
                        //b.putArrayList("pedidosAll",pedidosAll);
                        //enviarlo al asig activity por el bundle  y ejecutar la activity
                    }
                } catch (JSONException e) {
                    verToa("Error  al convertir los datos ****************");
                }
            }
        });

    }
}
