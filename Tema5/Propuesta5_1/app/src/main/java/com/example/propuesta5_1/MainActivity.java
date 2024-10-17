package com.example.propuesta5_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void texto1(View view){
        Intent intent = new Intent(this, texto1.class);
        startActivity(intent);

    }

    public void texto2(View view){
        Intent intent = new Intent(this, texto2.class);
        startActivity(intent);

    }

    public void texto3(View view){
        Intent intent = new Intent(this, texto3.class);
        startActivity(intent);

    }

    public void texto4(View view){
        Intent intent = new Intent(this, texto4.class);
        startActivity(intent);

    }
}