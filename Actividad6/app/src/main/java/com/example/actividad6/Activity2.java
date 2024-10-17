package com.example.actividad6;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String nombre = getIntent().getStringExtra("nombre");
        double num = getIntent().getDoubleExtra("numero", 0.0);
        Toast toast = Toast.makeText(this, "Nombre = " + nombre + "\n Edad = " + num, Toast.LENGTH_LONG);
        toast.show();
    }


}
