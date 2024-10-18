package com.example.ej5_2;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.imagen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void alfa1(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.alfa1);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void alfa2(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.alfa2);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void escala1(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.escala1);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void escala2(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.escala2);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void mueve1(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.mueve1);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void mueve2(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.mueve2);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void rotar1(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.rotar1);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void rotar2(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.rotar2);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void rotar3(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.rotar3);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void varios1(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.varios1);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }
    public void varios2(View view){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.varios2);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(5);
        imagen.startAnimation(animacion);
    }


}