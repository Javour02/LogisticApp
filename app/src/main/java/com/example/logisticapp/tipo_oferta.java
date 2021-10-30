package com.example.logisticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class tipo_oferta extends AppCompatActivity {

    public static final String TIP = "tipo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_oferta);
    }

    public void ofertaTransporte(View v){
        Intent i = new Intent(this, BuscarActivos.class);
        i.putExtra(TIP, "Transporte");
        startActivity(i);
    }

    public void ofertaAlmacenamiento(View v){
        Intent i = new Intent(this, BuscarActivos.class);
        i.putExtra(TIP, "Almacenamiento");
        startActivity(i);
    }

    public void ofertaOtro(View v){
        Intent i = new Intent(this, BuscarActivos.class);
        i.putExtra(TIP, "Otro");
        startActivity(i);
    }
}