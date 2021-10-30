package com.example.logisticapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OfertarActivo extends AppCompatActivity {

    private String tipo, des, estado, res, tip, nom, eje, ancho, alto, lon, cap, tam, dispiso, disalt, peso, prod, ubi, carac, email;
    private EditText larg, alt, anch, lugarSalida, lugarLlegada, horaSalida, horaLlegada;
    private EditText piso, altura;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private LinearLayout a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertar_activo);
        Intent in = getIntent();
        a = findViewById(R.id.linearTransporte);
        b = findViewById(R.id.linearAlmacenamiento);
        c = findViewById(R.id.linearOtros);
        a.setVisibility(View.GONE);
        b.setVisibility(View.GONE);
        c.setVisibility(View.GONE);
        email = in.getStringExtra(BuscarActivos.EMAIL);
        tipo = in.getStringExtra(BuscarActivos.TIPO);
        des = in.getStringExtra(BuscarActivos.DES);
        estado = in.getStringExtra(BuscarActivos.EST);
        res = in.getStringExtra(BuscarActivos.RES);
        larg = findViewById(R.id.largoDisp);
        alt = findViewById(R.id.altoDisp);
        anch = findViewById(R.id.anchoDis);
        lugarSalida = findViewById(R.id.LugarSalida);
        lugarLlegada = findViewById(R.id.LugarLlegada);
        horaSalida = findViewById(R.id.horaSalida);
        horaLlegada = findViewById(R.id.horaLlegada);
        piso = findViewById(R.id.piso);
        altura = findViewById(R.id.altura);

        if(tipo.equals("Transporte")){
            a.setVisibility(View.VISIBLE);
            tip = in.getStringExtra(BuscarActivos.TIP);
            nom = in.getStringExtra(BuscarActivos.NOM);
            eje = in.getStringExtra(BuscarActivos.EJE);
            ancho = in.getStringExtra(BuscarActivos.ANC);
            alto = in.getStringExtra(BuscarActivos.ALT);
            lon = in.getStringExtra(BuscarActivos.LONG);
            cap = in.getStringExtra(BuscarActivos.CAP);
        }else if(tipo.equals("Almacenamiento")){
            b.setVisibility(View.VISIBLE);
            tam = in.getStringExtra(BuscarActivos.TAM);
            tip = in.getStringExtra(BuscarActivos.TIP);
            dispiso = in.getStringExtra(BuscarActivos.PISO);
            disalt = in.getStringExtra(BuscarActivos.ALT);
            peso = in.getStringExtra(BuscarActivos.PESO);
        }else{
            c.setVisibility(View.VISIBLE);
            prod = in.getStringExtra(BuscarActivos.PROD);
            ubi = in.getStringExtra(BuscarActivos.UBI);
            carac = in.getStringExtra(BuscarActivos.CARAC);
        }
    }

    public void ofertar(View view){
        String largo = larg.getText().toString();
        String altDs = alt.getText().toString();
        String ancDs = anch.getText().toString();
        String lSalida = lugarSalida.getText().toString();
        String lLlegada = lugarLlegada.getText().toString();
        String hSalida = horaSalida.getText().toString();
        String hLlegada = horaLlegada.getText().toString();
        String pis = piso.getText().toString();
        String al = altura.getText().toString();
        Map<String, Object> elemento = new HashMap<>();
        elemento.put("des", des);
        elemento.put("est", estado);
        elemento.put("res", res);
        elemento.put("email", email);
        if(tipo.equals("Transporte")){
            elemento.put("tip", tip);
            elemento.put("nom", nom);
            elemento.put("eje", eje);
            elemento.put("ancho", ancho);
            elemento.put("alto", alto);
            elemento.put("long", lon);
            elemento.put("cap", cap);
            elemento.put("largoDis", largo);
            elemento.put("altoDis", altDs);
            elemento.put("anchoDis", ancDs);
            elemento.put("lugSalida", lSalida);
            elemento.put("lugLlegada", lLlegada);
            elemento.put("hSalida", hSalida);
            elemento.put("hLlegada", hLlegada);
        }else if(tipo.equals("Almacenamiento")){
            elemento.put("tam", tam);
            elemento.put("tip", tip);
            elemento.put("pisoTotal", dispiso);
            elemento.put("altTotal", disalt);
            elemento.put("peso", peso);
            elemento.put("pisDis", pis);
            elemento.put("altDis", al);
        }else{
            elemento.put("prod", prod);
            elemento.put("ubi", ubi);
            elemento.put("carac", carac);
        }

        db.collection("Ofertas").add(elemento).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                    Intent i = new Intent(view.getContext(), LogisticMenu.class);
                    startActivity(i);
                }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(view.getContext(), "No se pudo agregar la oferta", Toast.LENGTH_SHORT).show();
            }
        });
    }


}