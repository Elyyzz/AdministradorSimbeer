package com.example.elyzzbarrueta.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class detallePedidos extends AppCompatActivity {
TextView txt;
    Button Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedidos);
        txt=(TextView)findViewById(R.id.DetallePedido);
        txt.setText(R.string.detallePedido);
        Cancelar=(Button) findViewById(R.id.buttonCancelarAct);
        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ma= new Intent(detallePedidos.this, MainActivity.class);
                startActivity(ma);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_pedidos, menu);
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
                Intent acerca= new Intent(detallePedidos.this, acercade.class);
                startActivity(acerca);
                break;
            case R.id.action_salir:
                finish();
                break;
            case R.id.action_listapedidosEn://entregados
                Intent entregados= new Intent(detallePedidos.this, listPedidos.class);
                startActivity(entregados);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
            case R.id.action_listapedidosPr://proceso
                Intent proceso= new Intent(detallePedidos.this, listPedidos.class);
                startActivity(proceso);
                //enviar parametro para saber que tipo de consulta se realizará
                break;
            case R.id.action_stock:
                Intent stock= new Intent(detallePedidos.this, stock.class);
                startActivity(stock);
                break;
            case R.id.action_fermentacion:
                Intent fermentacion= new Intent(detallePedidos.this, actualizacion.class);
                startActivity(fermentacion);
                //enviar parametro para saber que tipo de consulta se realizará
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
