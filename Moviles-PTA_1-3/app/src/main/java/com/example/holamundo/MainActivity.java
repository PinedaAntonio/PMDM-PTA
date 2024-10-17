package com.example.holamundo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String TAG = "EJEMPLO";

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
        Log.d(TAG,"onCreate() llamado");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() llamado");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() llamado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Intent para cambiar de activity
        //Intent intent = new Intent(MainActivity.this, Actividad_2.class);

        //Intent para poder abrir google
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        // Iniciar la nueva actividad
        startActivity(intent);
        Log.d(TAG, "onPause() llamado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() llamado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() llamado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() llamado");
    }


}