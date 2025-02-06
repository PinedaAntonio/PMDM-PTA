package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "LoginPrefs"; // Nombre del archivo SharedPreferences
    private static final String KEY_LAST_USERNAME = "lastUsername"; // Clave para guardar el último usuario
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mediaPlayer = MediaPlayer.create(this, R.raw.mariobros);
        mediaPlayer.start();

        EditText usuario = findViewById(R.id.usuario);
        EditText contraseña = findViewById(R.id.contraseña);
        Button loginbutton = findViewById(R.id.loginbutton);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String lastUsername = preferences.getString(KEY_LAST_USERNAME, "");
        usuario.setText(lastUsername);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usuario.getText().toString();
                String password = contraseña.getText().toString();

                if (username.equals("usuario") && password.equals("usuario")) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KEY_LAST_USERNAME, username);
                    editor.apply();

                    showCustomToast(R.string.inicio_true);
                    mediaPlayer.stop();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showCustomToast(R.string.inicio_false);
                }
            }
        });
    }

    private void showCustomToast(int stringResId) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_message);
        text.setText(getString(stringResId));

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
