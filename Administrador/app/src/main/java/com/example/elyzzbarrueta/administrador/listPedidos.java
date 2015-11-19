package com.example.elyzzbarrueta.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listPedidos extends AppCompatActivity {
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pedidos);
        lista=(ListView) findViewById(R.id.listView);
        String [] nom= getResources().getStringArray(R.array.Productos);

        ArrayAdapter<String> lis=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nom);
        lista.setAdapter(lis);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent d= new Intent(listPedidos.this, detallePedidos.class);
                startActivity(d);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_pedidos, menu);
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
                Intent acerca= new Intent(listPedidos.this, acercade.class);
                startActivity(acerca);
                break;
            case R.id.action_salir:
                finish();
                break;
            case R.id.action_listapedidosEn://entregados
                Intent entregados= new Intent(listPedidos.this, listPedidos.class);
                startActivity(entregados);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
            case R.id.action_listapedidosPr://proceso
                Intent proceso= new Intent(listPedidos.this, listPedidos.class);
                startActivity(proceso);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
            case R.id.action_stock:
                Intent stock= new Intent(listPedidos.this, stock.class);
                startActivity(stock);
                break;
            case R.id.action_fermentacion:
                Intent fermentacion= new Intent(listPedidos.this, actualizacion.class);
                startActivity(fermentacion);
                //enviar parametro para saber que tipo de consulta se realizará
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
