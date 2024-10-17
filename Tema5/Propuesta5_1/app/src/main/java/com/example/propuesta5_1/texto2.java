package com.example.propuesta5_1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class texto2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_texto2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maintexto2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView textView = new TextView(this);
        textView.setText("Texto construido desde Java Tamaño 20dp, italic y color blue");
        textView.setTextSize(20); // Tamaño 20dp
        textView.setTypeface(null, Typeface.ITALIC); // Estilo italic
        textView.setTextColor(Color.BLUE); // Color blue

        // Obtener el LinearLayout del layout XML
        LinearLayout mainLayout = findViewById(R.id.maintexto2);

        // Añadir el TextView al LinearLayout
        mainLayout.addView(textView);


    }

    public void onPause(){
        super.onPause();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}