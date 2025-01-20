package com.example.pruebatema9;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements InputFragment.OnTextSendListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void textoEnviado(String text) {
        //Encuentra el fragmento verde (DisplayFragment)
        DisplayFragment displayFragment = (DisplayFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_display);

        //Actualiza el texto del DisplayFragment
        if (displayFragment != null) {
            displayFragment.updateText(text);
        }
    }
}
