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
import com.loopj.android.http.JsonHttpResponseHandler;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {

    Button  acesar;
    EditText user;
    EditText pass;

    final ArrayList<pedido> pedidosAll=new ArrayList<pedido>();
    final RequestParams params = new RequestParams();

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
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get("http://192.168.0.128:8080/webServiceDemo/api/getPedidos/listaPedidos", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                            System.out.println("**************** Failed :'( ");
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
                } else {
                    verToa("Usuario y/o Password incorrecto");
                    pass.setText("");
                }
            }
        });
    }


    public void prueba(){
        pedido p= new pedido();
        final AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams params = new RequestParams();
        params.put("key", "value");
        params.put("more", "data");
        client.get("http://192.168.0.128:8080/webServiceDemo/api/demo/listaproductos", params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        System.out.println("******************** Fallo :( ");
                    }
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                        System.out.println("******************** Success :) ");
                        System.out.println("********************RESULTADO: | " + responseString);
                    }
                }
        );
    }
    private  String  Envia(String  datos) throws IOException {
        String line="ELy";
        String targetURL = "http://192.168.10.215/android/hola.php";
        String urlParameters = "a="+datos;
        URL url = null;
        url = new URL(targetURL);
        HttpURLConnection connection = null;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        connection.setRequestProperty("Content-Length", ""
                + Integer.toString(urlParameters.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");

        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        DataOutputStream wr;
        wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        InputStream is;
        is = connection.getInputStream();
        BufferedReader rd;
        rd = new BufferedReader(new InputStreamReader(is));
        System.out.println("Res"+rd);

        while (rd != null) {
            line=rd.readLine();
            return line;
            //Toast.makeText(this, line, Toast.LENGTH_LONG).show();
        }
        return line;

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



    }
}
