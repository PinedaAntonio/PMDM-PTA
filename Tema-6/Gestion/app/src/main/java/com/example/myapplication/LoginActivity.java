package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usuario = findViewById(R.id.usuario);
        EditText contraseña = findViewById(R.id.contraseña);
        Button loginbutton = findViewById(R.id.loginbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usuario.getText().toString();
                String password = contraseña.getText().toString();

                if (username.equals("usuario") && password.equals("usuario")) {
                    Toast.makeText(LoginActivity.this, "Usuario y contraseña correctos, iniciando...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña erróneos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}