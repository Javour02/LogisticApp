package com.example.logisticapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegUsuario extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText nombre, contraseña, contraseña2, telefono, nEncargado, desc, correo;
    private Switch terminos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_usuario);
        nombre = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        contraseña2 = findViewById(R.id.contraseña2);
        telefono = findViewById(R.id.telefono);
        nEncargado = findViewById(R.id.nEncargado);
        desc = findViewById(R.id.desc);
        correo = findViewById(R.id.correo);
        terminos = findViewById(R.id.switch1);

    }

    public void registrarse(View view){
        Map<String, Object> user = new HashMap<>();
        String nom = nombre.getText().toString();
        String con = contraseña.getText().toString();
        String con2 = contraseña2.getText().toString();
        String ema = correo.getText().toString();
        String tel = telefono.getText().toString();
        String enc = nEncargado.getText().toString();
        String des = desc.getText().toString();

        if(nom.equals("")||con.equals("")||ema.equals("")||tel.equals("")||enc.equals("")||des.equals("")){
            Toast.makeText(this, "Llene todos los campos para registrarse", Toast.LENGTH_SHORT).show();
        }else if(!con.equals(con2)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        }else if(!validarEmail(ema)){
            Toast.makeText(this, "El email ingresado no es valido", Toast.LENGTH_SHORT).show();
        }else if(!terminos.isChecked()){
            Toast.makeText(this, "Debe aceptar los terminos y condiciones para continuar", Toast.LENGTH_SHORT).show();
        }else{
            user.put("name", nom);
            user.put("password", con);
            user.put("email", ema);
            user.put("cName", enc);
            user.put("phone", tel);
            user.put("desc", des);

            // Add a new document with a generated ID
            db.collection("Usuarios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent i = new Intent(view.getContext(), Login.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(), "No se pudo agregar el usuario", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
