package com.example.juego2d_exe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GameOverActivity extends AppCompatActivity {
    private int lastScore;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        SoundEffects.stopGameMusic(); // Detener música del juego

        // Obtener puntuación del Intent
        lastScore = getIntent().getIntExtra("score", 0);

        // Obtener récord almacenado en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("game_prefs", MODE_PRIVATE);
        highScore = prefs.getInt("high_score", 0);

        // Guardar el nuevo récord
        if (lastScore > highScore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("high_score", lastScore);
            editor.apply();
            highScore = lastScore;
        }

        // Mostrar la puntuación y el récord
        TextView scoreText = findViewById(R.id.text_last_score);
        TextView recordText = findViewById(R.id.text_high_score);

        scoreText.setText("Puntuación: " + lastScore);
        recordText.setText("Récord: " + highScore);


        // Implementación del qr
        ImageView qrImageView = findViewById(R.id.qrImageView);
        String qrText = "Mi récord en Cave Survivor es: " + highScore + " puntos"; // Texto que se muestra al escanear
        qrImageView.setImageBitmap(generateQRCode(qrText));
        Button btnRestart = findViewById(R.id.btn_restart);
        Button btnExit = findViewById(R.id.btn_exit);

        btnRestart.setOnClickListener(v -> {
            // Mostrar toast "Reiniciando..."
            Toast.makeText(this, "Reiniciando...", Toast.LENGTH_SHORT).show();

            // Reproducir sonido de inicio
            SoundEffects.playIntroSound(this);

            // Retrasar el reinicio del juego 2 segundos (así dejamos que se reproduzca el sonido)
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

    private Bitmap generateQRCode(String text) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 500, 500);
            return new BarcodeEncoder().createBitmap(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
