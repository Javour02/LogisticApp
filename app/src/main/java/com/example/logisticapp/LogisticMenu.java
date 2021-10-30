package com.example.logisticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class LogisticMenu extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic_menu);
        mAuth = FirebaseAuth.getInstance();
    }

    public void ofertar(View view){
        Intent i = new Intent(this, Ofertar.class);
        startActivity(i);
    }

    public void verOfertas(View view){
        Intent i = new Intent(this, VerOfertas.class);
        startActivity(i);
    }

    public void salida(View view){
        mAuth.signOut();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }


}