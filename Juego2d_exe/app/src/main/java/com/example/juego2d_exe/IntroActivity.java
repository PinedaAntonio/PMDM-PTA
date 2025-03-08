package com.example.juego2d_exe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(v -> {
            // Mostrar mensaje "Iniciando..."
            Toast.makeText(this, "Iniciando...", Toast.LENGTH_SHORT).show();

            // Reproducir sonido de inicio
            SoundEffects.playIntroSound(this);

            // Retrasar el inicio del juego 2 segundos (2000ms)
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(IntroActivity.this, GameActivity.class);
                startActivity(intent);
                finish(); // Cierra la pantalla de introducción
            }, 2000); // 2000 milisegundos = 2 segundos
        });
    }
}
