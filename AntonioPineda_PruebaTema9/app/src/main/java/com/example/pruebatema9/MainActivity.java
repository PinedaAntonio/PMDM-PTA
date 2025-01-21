package com.example.pruebatema9;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentoUno.OnTextSendListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void textoEnviado(String text) {
        FragmentoDos fragmentoDos = (FragmentoDos) getSupportFragmentManager()
                .findFragmentById(R.id.fragmento_dos);

        if (fragmentoDos != null) {
            fragmentoDos.actualizarTexto(text);
        }
    }
}