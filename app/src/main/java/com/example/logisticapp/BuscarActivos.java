package com.example.logisticapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class BuscarActivos extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView parrafo;
    private String tipo;
    private FirebaseAuth mAuth;
    private LinearLayout cont;
    public static final String TIPO = "tipo";
    public static final String DES = "des";
    public static final String TIP = "tip";
    public static final String NOM = "nom";
    public static final String EJE = "eje";
    public static final String ANC = "anc";
    public static final String ALT = "alt";
    public static final String LONG = "long";
    public static final String CAP = "cap";
    public static final String RES = "res";
    public static final String TAM = "tam";
    public static final String EST = "est";
    public static final String PISO = "piso";
    public static final String PESO = "peso";
    public static final String PROD = "prod";
    public static final String UBI = "ubi";
    public static final String CARAC = "carac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_activos);
        Intent i = getIntent();
        mAuth = FirebaseAuth.getInstance();
        parrafo = findViewById(R.id.Parrafo);
        tipo = i.getStringExtra(tipo_oferta.TIP);
        cont = findViewById(R.id.cont);
        parrafo.setText("En los activos de este usuario se encontraron los siguientes activos de tipo "+tipo+":");
        leerActivos();
    }

    private void leerActivos(){
        db.collection("ULogistico").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.get("id").toString().equals(mAuth.getCurrentUser().toString())){
                            seguirLeyendo(document);
                        }
                    }
                } else {

                }
            }
        });
    }

    private void seguirLeyendo(QueryDocumentSnapshot doc){
        db.collection("ULogistico").document(doc.getId()).collection("Activos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.get("Tipo").toString().equals(tipo)){
                            agregarActivo(document.get("activo").toString(), document);
                        }
                    }
                } else {

                }
            }
        });
    }

    private void agregarActivo(String numActivo, QueryDocumentSnapshot document){
        LinearLayout elemento = new LinearLayout(this);
        elemento.setOrientation(LinearLayout.HORIZONTAL);
        Button boton = new Button(BuscarActivos.this);
        boton.setText("Ofertar");
        TextView titulo = new TextView(this);
        titulo.setText("Activo #"+numActivo);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuscarActivos.this, OfertarActivo.class);
                intent.putExtra(TIPO, tipo);
                pasarInfo(intent, document);
                startActivity(intent);
            }
        });
        elemento.addView(boton);
        elemento.addView(titulo);
        cont.addView(elemento);
    }

    private void pasarInfo(Intent i,QueryDocumentSnapshot doc){
        i.putExtra(DES, doc.get("des").toString());
        i.putExtra(EST, doc.get("estado").toString());
        i.putExtra(RES, doc.get("res").toString());
        if(tipo.equals("Transporte")){
            i.putExtra(TIP, doc.get("tip").toString());
            i.putExtra(NOM, doc.get("nom").toString());
            i.putExtra(EJE, doc.get("eje").toString());
            i.putExtra(ANC, doc.get("ancho").toString());
            i.putExtra(ALT, doc.get("alto").toString());
            i.putExtra(LONG, doc.get("long").toString());
            i.putExtra(CAP, doc.get("cap").toString());
        }else if(tipo.equals("Almacenamiento")){
            i.putExtra(TAM, doc.get("tam").toString());
            i.putExtra(TIP, doc.get("tip").toString());
            i.putExtra(PISO, doc.get("dispiso").toString());
            i.putExtra(ALT, doc.get("disalt").toString());
            i.putExtra(PESO, doc.get("peso").toString());
        }else{
            i.putExtra(PROD, doc.get("prod").toString());
            i.putExtra(UBI, doc.get("ubi").toString());
            i.putExtra(CARAC, doc.get("res").toString());
        }
    }
}