package com.example.elyzzbarrueta.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

public class stock extends AppCompatActivity {
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
    }

    public void crearDatos() {
        GrupoDeItems grupo0 = new GrupoDeItems("Stout Chocolate");
        grupo0.children.add("Agregar");
        grupo0.children.add("Disminuir");
        grupo0.children.add("Eliminar");//lleva a 0 la cantidad de stock
        grupos.append(0, grupo0);
        GrupoDeItems grupo1 = new GrupoDeItems("Porter Maple");
        grupo1.children.add("Agregar");
        grupo1.children.add("Disminuir");
        grupo1.children.add("Eliminar");//lleva a 0 la cantidad de stock
        grupos.append(1, grupo1);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stock, menu);
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
                Intent acerca= new Intent(stock.this, acercade.class);
                startActivity(acerca);
                break;
            case R.id.action_salir:
                finish();
                break;
            case R.id.action_listapedidosEn://entregados
                Intent entregados= new Intent(stock.this, listPedidos.class);
                startActivity(entregados);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
            case R.id.action_listapedidosPr://proceso
                Intent proceso= new Intent(stock.this, listPedidos.class);
                startActivity(proceso);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
            case R.id.action_stock:
                Intent stock= new Intent(stock.this, stock.class);
                startActivity(stock);
                break;
            case R.id.action_fermentacion:
                Intent fermentacion= new Intent(stock.this, actualizacion.class);
                startActivity(fermentacion);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
