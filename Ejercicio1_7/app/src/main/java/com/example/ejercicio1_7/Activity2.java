package com.example.ejercicio1_7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void intentDeVuelta(View view){
        Intent data =new Intent();
        data.putExtra("RETORNO", "Este es el valor de retorno");
        setResult(RESULT_OK, data);
        finish();
    }
}

