package com.example.logisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
    }

    public void login(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public void registrarse(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}
