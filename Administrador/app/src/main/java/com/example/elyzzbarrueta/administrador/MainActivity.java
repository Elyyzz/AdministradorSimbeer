package com.example.elyzzbarrueta.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class MainActivity extends AppCompatActivity {
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salir= (Button)findViewById(R.id.Salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  pid=android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                Intent salida=new Intent(Intent.ACTION_MAIN);
                salida.addCategory(Intent.CATEGORY_HOME);
                startActivity(salida);
            }
        });
        crearDatos();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
        adaptador adaptr = new adaptador(this, grupos);
        listView.setAdapter(adaptr);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0 && childPosition == 0) {//pedidos pendientes
                    try {
                        getPedidos("Select * from producto_tab");
                        //Intent pend=new Intent(MainActivity.this, listPedidos.class);
                        //startActivity(pend);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
                if (groupPosition == 0 && childPosition == 1) {//pedidos entregados
                    //7Intent entr=new Intent(MainActivity.this, a.class);
                    //startActivity(entr);
                }
                if (groupPosition == 1 && childPosition == 0) {
                    //Intent pend1=new Intent(MainActivity.this, actualizacion.class);
                    //startActivity(pend1);
                }
                if (groupPosition == 1 && childPosition == 1) {
                    //Intent pend2=new Intent(MainActivity.this, stock.class);
                 //   startActivity(pend2);
                }

                if (groupPosition == 2 && childPosition == 0) {
                  //  Intent salida=new Intent(Intent.ACTION_MAIN);
                 // finishAffinity();

                }
                return false;
            }
        });
    }
    public void crearDatos() {
        GrupoDeItems grupo0 = new GrupoDeItems("Pedidos");
        grupo0.children.add("Entregados");
        grupo0.children.add("Pendientes");
        grupos.append(0, grupo0);
        GrupoDeItems grupo1 = new GrupoDeItems("Actualizaciones");
        grupo1.children.add("Fermentación");
        grupo1.children.add("Stock");
        grupos.append(1, grupo1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
              Intent acerca= new Intent(MainActivity.this, acercade.class);
              startActivity(acerca);
              break;
          case R.id.action_salir:
              finish();
              break;

      }

        return super.onOptionsItemSelected(item);
    }
    //Conexion  a  la BD para traer pedidos  pendientes

    public void getPedidos(String query) throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("query", query);
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
                            Toast.makeText(MainActivity.this, "todo ok" + rest, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }


}
