package com.example.ud5_ej8;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar p;
    Button b;
    TextView t;
    int i =0;
    Handler h=new Handler();
    boolean estaActivado=false;
    Intent x;

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
        p= (ProgressBar) findViewById(R.id.progressBar);
        b=(Button) findViewById(R.id.boton);
        t=(TextView) findViewById(R.id.texto);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.boton){
            if (!estaActivado){
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (i<=100){
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    t.setText(i+" %");
                                    p.setProgress(i);
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if (i==100){
                                x=new Intent(MainActivity.this, imagen.class);
                                startActivity(x);
                            }
                            i++;
                            estaActivado=true;
                        }
                    }
                });
                hilo.start();
            }
        }
    }
}