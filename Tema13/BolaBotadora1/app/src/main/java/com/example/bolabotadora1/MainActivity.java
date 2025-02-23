package com.example.bolabotadora1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean continuar=true;
    float velocidad=0.5f;
    int dt=10;
    int tiempo=0;
    Thread hilo=null;
    GraficoView dinamica;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica=new GraficoView(this);
        setContentView(dinamica);
        hilo = new Thread(dinamica);
        hilo.start();
    }

    class GraficoView extends View implements Runnable{

        int x,y,ymax;
        Paint paintFondo,paintParticula,paint;

        public GraficoView(Context context) {
            super(context);
            paintFondo=new Paint();
            paintParticula=new Paint();
            paint=new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paint.setColor(Color.BLACK);
            paint.setTextSize(30);
        }

        @Override
        public void run() {

            while(continuar){
                tiempo=tiempo+dt;
                y=y+(int)(velocidad*dt);
                if(y>ymax) velocidad=-velocidad;
                if(y<0) velocidad=-velocidad;
                postInvalidate();
                try { Thread.sleep( dt ); }
                catch ( InterruptedException e ){ ; }
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw,int oldh){
            x=w/2;
            y=0;
            ymax=h;
        }


        @Override
        public void onDraw(Canvas canvas ){
            canvas.drawPaint(paintFondo);
            canvas.drawCircle(x+150, y, 30, paintParticula);
            canvas.drawText("ALTURA="+y, 50, 50, paint);
            canvas.drawText("TIEMPO="+tiempo, 50, 90, paint);
        }

    }

}
