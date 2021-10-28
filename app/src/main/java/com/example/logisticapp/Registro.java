package com.example.logisticapp;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
    }

    public void regUsuario(View view){
        Intent i = new Intent(this, RegUsuario.class);
        startActivity(i);
    }

    public void regLogistico(View view){
        Intent i = new Intent(this, RegLogistico.class);
        startActivity(i);
    }
}
