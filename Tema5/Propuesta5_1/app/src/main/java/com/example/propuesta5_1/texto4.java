package com.example.propuesta5_1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class texto4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_texto4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maintexto4   ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView texto4 = (TextView) findViewById(R.id.texto4);
        Typeface mifuente = Typeface.createFromAsset(getAssets(),"font/umbrella.ttf");
        texto4.setTypeface(mifuente);
    }

    public void onPause(){
        super.onPause();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}