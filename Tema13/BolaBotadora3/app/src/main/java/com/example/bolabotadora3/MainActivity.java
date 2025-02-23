package com.example.bolabotadora3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean continuar = true;
    int dt = 10;
    int tiempo = 0;
    Thread hilo = null;
    GraficoView dinamica;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica = new GraficoView(this);
        setContentView(dinamica);
        hilo = new Thread(dinamica);
        hilo.start();
    }

    class GraficoView extends View implements Runnable {
        float x, y;
        float xmax, ymax;
        Paint paintFondo, paintParticula, paint;
        float vx = 0f, vy = 0f;
        final float aceleracion = 0.2f;

        public GraficoView(Context context) {
            super(context);
            paintFondo = new Paint();
            paintParticula = new Paint();
            paint = new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paint.setColor(Color.BLACK);
            paint.setTextSize(30);
        }

        @Override
        public void run() {
            while (continuar) {
                tiempo += dt;
                if (vx >= 0) {
                    vx += aceleracion;
                } else {
                    vx -= aceleracion;
                }
                if (vy >= 0) {
                    vy += aceleracion;
                } else {
                    vy -= aceleracion;
                }

                x += vx;
                y += vy;

                if (x > xmax) {
                    x = xmax;
                    vx = -vx;
                }
                if (x < 0) {
                    x = 0;
                    vx = -vx;
                }
                if (y > ymax) {
                    y = ymax;
                    vy = -vy;
                }
                if (y < 0) {
                    y = 0;
                    vy = -vy;
                }

                postInvalidate();
                try {
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            xmax = w;
            ymax = h;
            x = w / 2;
            y = h / 2;
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawPaint(paintFondo);
            canvas.drawCircle(x, y, 30, paintParticula);
            canvas.drawText("ALTURA = " + (int) y, 50, 50, paint);
            canvas.drawText("TIEMPO = " + tiempo, 50, 90, paint);
        }
    }
}
