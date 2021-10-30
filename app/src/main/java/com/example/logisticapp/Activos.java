package com.example.logisticapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Activos extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String nom, con, tel, ubi, des, cor, enc;
    private EditText tdes, ttipo, tnombre, tejes, tancho, talto, tlon, tcap, tres;
    private EditText odes, oprod, oubi, ocarac, ores;
    private EditText ades, atam, atipo, adispiso, adisalt, apesmax, ares;
    private Spinner tipo;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activos);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        nom = intent.getStringExtra(RegLogistico.NOM);
        con = intent.getStringExtra(RegLogistico.CON);
        tel = intent.getStringExtra(RegLogistico.TEL);
        ubi = intent.getStringExtra(RegLogistico.UBI);
        des = intent.getStringExtra(RegLogistico.DES);
        cor = intent.getStringExtra(RegLogistico.COR);
        enc = intent.getStringExtra(RegLogistico.ENC);
        tdes = findViewById(R.id.des);
        ttipo = findViewById(R.id.car);
        tnombre = findViewById(R.id.nom);
        tejes = findViewById(R.id.eje);
        tancho = findViewById(R.id.anc);
        talto = findViewById(R.id.alt);
        tlon = findViewById(R.id.lon);
        tcap = findViewById(R.id.cap);
        tres = findViewById(R.id.res);
        odes = findViewById(R.id.desOtro);
        oprod = findViewById(R.id.prod);
        oubi = findViewById(R.id.ubicacion);
        ocarac = findViewById(R.id.caract);
        ores = findViewById(R.id.orestricciones);
        ades = findViewById(R.id.descrip);
        atam = findViewById(R.id.tam);
        atipo = findViewById(R.id.tProducto);
        adispiso = findViewById(R.id.dPiso);
        adisalt = findViewById(R.id.dAlt);
        apesmax = findViewById(R.id.pes);
        ares = findViewById(R.id.restricciones);

        tipo = findViewById(R.id.spinner);
        String opciones [] = {"Transporte", "Almacenamiento", "Contenedores", "Maquinaria", "Instaladores", "Otro"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        tipo.setAdapter(adapter);
    }

    public void habilitarVista(View view){
        ScrollView vista1 = findViewById(R.id.scrollView2);
        ScrollView vista2 = findViewById(R.id.scrollView3);
        ScrollView vista3 = findViewById(R.id.scrollView4);
        String seleccion = tipo.getSelectedItem().toString();
        if(seleccion.equals("Transporte")){
            vista2.setVisibility(View.GONE);
            vista3.setVisibility(View.GONE);
            vista1.setVisibility(View.VISIBLE);
        }else if(seleccion.equals("Almacenamiento")){
            vista1.setVisibility(View.GONE);
            vista3.setVisibility(View.GONE);
            vista2.setVisibility(View.VISIBLE);
        }else{
            vista1.setVisibility(View.GONE);
            vista2.setVisibility(View.GONE);
            vista3.setVisibility(View.VISIBLE);

        }
    }

    public void agregarAColeccion(View view){
        String tip = tipo.getSelectedItem().toString();
        Map<String, Object> elemento = new HashMap<>();
        elemento.put("Tipo", tip);
        llenarInforme(elemento, tip);
        db.collection("ULogistico").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.get("id").toString().equals(mAuth.getCurrentUser().toString())){
                            db.collection("ULogistico").document(document.getId()).update("numActivos",Integer.parseInt(document.get("numActivos").toString())+1);
                            elemento.put("activo", document.get("numActivos").toString());
                            db.collection("ULogistico").document(document.getId()).collection("Activos").add(elemento).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent i = new Intent(view.getContext(), Activos.class);
                                    startActivity(i);
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    public void agregarAColeccionFinalizar(View view){
        String tip = tipo.getSelectedItem().toString();
        Map<String, Object> elemento = new HashMap<>();
        llenarInforme(elemento, tip);
        elemento.put("Tipo", tip);
        db.collection("ULogistico").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.get("id").toString().equals(mAuth.getCurrentUser().toString())){
                            db.collection("ULogistico").document(document.getId()).collection("Activos").add(elemento).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent i = new Intent(view.getContext(), Login.class);
                                    startActivity(i);
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    private void llenarInforme(Map<String, Object> elemento, String tip){
        if(tip.equals("Transporte")){
            elemento.put("des", tdes.getText().toString());
            elemento.put("tip", ttipo.getText().toString());
            elemento.put("nom", tnombre.getText().toString());
            elemento.put("eje", tejes.getText().toString());
            elemento.put("ancho", tancho.getText().toString());
            elemento.put("alto", talto.getText().toString());
            elemento.put("long", tlon.getText().toString());
            elemento.put("cap", tcap.getText().toString());
            elemento.put("res", tres.getText().toString());
            elemento.put("estado", true);
        }else if(tip.equals("Almacenamiento")){
            elemento.put("des", ades.getText().toString());
            elemento.put("tam", atam.getText().toString());
            elemento.put("tip", atipo.getText().toString());
            elemento.put("dispiso", adispiso.getText().toString());
            elemento.put("disalt", adisalt.getText().toString());
            elemento.put("peso", apesmax.getText().toString());
            elemento.put("res", ares.getText().toString());
            elemento.put("estado", true);
        }else{
            elemento.put("des", odes.getText().toString());
            elemento.put("prod", oprod.getText().toString());
            elemento.put("ubi", oubi.getText().toString());
            elemento.put("carac", ocarac.getText().toString());
            elemento.put("res", ores.getText().toString());
            elemento.put("estado", true);
        }
    }
}