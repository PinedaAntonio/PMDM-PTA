package com.example.juego2d_exe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        SoundEffects.stopGameMusic(); // Detener música del juego

        Button btnRestart = findViewById(R.id.btn_restart);
        Button btnExit = findViewById(R.id.btn_exit);

        btnRestart.setOnClickListener(v -> {
            // Mostrar mensaje "Reiniciando..."
            Toast.makeText(this, "Reiniciando...", Toast.LENGTH_SHORT).show();

            // Reproducir sonido de inicio
            SoundEffects.playIntroSound(this);

            // Retrasar el reinicio del juego 2 segundos
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(GameOverActivity.this, GameActivity.class);
                startActivity(intent);
                finish(); // Cierra la pantalla de Game Over
            }, 2000);
        });

        btnExit.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });
    }
}
