package com.example.propuesta5_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class texto3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_texto3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maintexto3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView texto3 = (TextView) findViewById(R.id.texto3);
        texto3.append("\n Texto añadido con append desde java");
        texto3.setTextColor(Color.BLUE);
    }

    public void onPause(){
        super.onPause();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}