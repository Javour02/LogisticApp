package com.example.logisticapp;

import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    private EditText usu, con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        usu = findViewById(R.id.usuarioLog);
        con = findViewById(R.id.conLog);
    }

    public void irRegistro (View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void loggearse(View view) {
        String usuario = usu.getText().toString();
        String contraseña = con.getText().toString();
        if(usuario.equals("")||contraseña.equals("")){
            Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }else if(!validarEmail(usuario)){
            Toast.makeText(this, "Correo no valido", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        actualizarId(usuario, view);
                    }else{
                        Toast.makeText(view.getContext(), "No se pudo encontrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void actualizarId(String email, View view){
        db.collection("Usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        if(document.get("email").toString().equals(email)){
                            db.collection("Usuarios").document(document.getId()).update("id",mAuth.getCurrentUser().toString());
                            Intent intent = new Intent(view.getContext(), Menu.class);
                            startActivity(intent);
                        }
                    }
                    actualizarIdLogistico(email, view);
                }else{
                    Toast.makeText(view.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void actualizarIdLogistico(String email, View view){
        db.collection("ULogistico").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        if(document.get("email").toString().equals(email)){
                            db.collection("ULogistico").document(document.getId()).update("id",mAuth.getCurrentUser().toString());
                            Intent intent = new Intent(view.getContext(), LogisticMenu.class);
                            startActivity(intent);
                        }
                    }
                }else{
                    Toast.makeText(view.getContext(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
