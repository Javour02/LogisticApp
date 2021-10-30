package com.example.logisticapp;

import static com.example.logisticapp.BuscarActivos.DES;
import static com.example.logisticapp.BuscarActivos.TIPO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmarActivo extends AppCompatActivity {

    private TextView tipoView;
    public static final String EMAIL = "email";
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
    private LinearLayout contenedor;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_activo);
        i = new Intent(this, OfertarActivo.class);
        Intent in = getIntent();
        tipoView = findViewById(R.id.tipo);
        String tipo = in.getStringExtra(BuscarActivos.TIPO);
        tipoView.setText(tipo);
        i.putExtra(TIPO, tipo);
        i.putExtra(EMAIL, in.getStringExtra(BuscarActivos.EMAIL));
        i.putExtra(DES, in.getStringExtra(BuscarActivos.DES));
        TextView a = new TextView(this);
        a.setText(i.getStringExtra(BuscarActivos.DES));
        contenedor = findViewById(R.id.info);
        contenedor.addView(a);
        i.putExtra(EST, in.getStringExtra(BuscarActivos.EST));
        i.putExtra(RES, in.getStringExtra(BuscarActivos.RES));
        if(tipo.equals("Transporte")){
            i.putExtra(TIP, in.getStringExtra(BuscarActivos.TIP));
            TextView b = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.TIP));
            contenedor.addView(b);
            i.putExtra(NOM, in.getStringExtra(BuscarActivos.NOM));
            TextView c = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.NOM));
            contenedor.addView(c);
            i.putExtra(EJE, in.getStringExtra(BuscarActivos.EJE));
            TextView d = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.EJE));
            contenedor.addView(d);
            i.putExtra(ANC, in.getStringExtra(BuscarActivos.ANC));
            TextView e = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.ANC));
            contenedor.addView(e);
            i.putExtra(ALT, in.getStringExtra(BuscarActivos.ALT));
            TextView f = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.ALT));
            contenedor.addView(f);
            i.putExtra(LONG, in.getStringExtra(BuscarActivos.LONG));
            TextView g = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.LONG));
            contenedor.addView(g);
            i.putExtra(CAP, in.getStringExtra(BuscarActivos.CAP));
            TextView h = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.CAP));
            contenedor.addView(h);
        }else if(tipo.equals("Almacenamiento")){
            i.putExtra(TAM, in.getStringExtra(BuscarActivos.TAM));
            TextView j = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.TAM));
            contenedor.addView(j);
            i.putExtra(TIP, in.getStringExtra(BuscarActivos.TIP));
            TextView k = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.TIP));
            contenedor.addView(k);
            i.putExtra(PISO, in.getStringExtra(BuscarActivos.PISO));
            TextView d = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.PISO));
            contenedor.addView(d);
            i.putExtra(ALT, in.getStringExtra(BuscarActivos.ALT));
            TextView e = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.ALT));
            contenedor.addView(e);
            i.putExtra(PESO, in.getStringExtra(BuscarActivos.PESO));
            TextView f = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.PESO));
            contenedor.addView(f);
        }else{
            i.putExtra(PROD, in.getStringExtra(BuscarActivos.PROD));
            TextView m = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.PROD));
            contenedor.addView(m);
            i.putExtra(UBI, in.getStringExtra(BuscarActivos.UBI));
            TextView u = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.UBI));
            contenedor.addView(u);
            i.putExtra(CARAC, in.getStringExtra(BuscarActivos.CARAC));
            TextView b = new TextView(this);
            a.setText(in.getStringExtra(BuscarActivos.CARAC));
            contenedor.addView(b);
        }
    }

    public void continuar(View view){
        startActivity(i);
    }
}