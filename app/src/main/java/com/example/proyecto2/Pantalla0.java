package com.example.proyecto2;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Pantalla0 extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla0);
    }
    public void ImplicitIntentURL(View v)
    {
        String webpage= "http://www.guadalupe.gob.mx/" ;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpage));
        startActivity(intent);
    }
    public void Reportes(View v)
    {
        startActivity(new Intent(Pantalla0.this, Proyecto2Activity.class));
    }
}