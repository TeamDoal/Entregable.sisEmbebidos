package com.example.proyecto2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Proyecto2Activity extends Activity {

    EditText Quien, Ubicacion; //Variables para las cajas de texto.
    String[] info; // Para almacenar el nombre y la edad.

    final static String ACT_INFO = "com.example.proyecto2;"; // Ruta y Nombre de la activad a la cual voy a enviar la info.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto2);

        Quien = (EditText) findViewById(R.id.editTextQuien);
        Ubicacion = (EditText) findViewById(R.id.editTextUbicacion);

        info = new String[2];
    }

    public void pasarActividadInfo(View v){

        //Obtengo lo que hay en las cajas de texto.

        info[0] = Quien.getText().toString();
        info[1] = Ubicacion.getText().toString();

        //Creo y asigno la información a enviar.
        Intent act = new Intent(this, pantalla2.class);
        act.putExtra(ACT_INFO, info);
        startActivity(act);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proyecto2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
