package com.example.responsivegestion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.FragmentActivity;

public class Activity extends FragmentActivity implements Fragmento1.Callbacks {

    private boolean dosFragmentos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad);

        if(findViewById(R.id.frame_contenedor) != null){
            dosFragmentos=true;
        }
    }

    @Override
    public void onEntradaSeleccionada(String id) {
        if(dosFragmentos){
            Bundle arguments = new Bundle();
            arguments.putString(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
            Fragmento3 fragment = new Fragmento3();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_contenedor, fragment).commit();
        }else{
            Intent detalleIntent = new Intent(this, Fragmento2.class);
            detalleIntent.putExtra(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
            startActivity(detalleIntent);
        }
    }
}