package com.example.antonioprueba2eval2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

class CustomView extends View {
    private Paint linea;
    private Paint texto;
    private Paint textoGrande;
    private float scaledDensity;
    private int width, height;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        scaledDensity = metrics.scaledDensity;
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        linea = new Paint();
        linea.setColor(Color.GREEN);
        linea.setStrokeWidth(6);

        texto = new Paint();
        texto.setColor(Color.BLACK);
        texto.setTextSize(20 * scaledDensity);

        textoGrande = new Paint();
        textoGrande.setColor(Color.BLACK);
        textoGrande.setTextSize(30 * scaledDensity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        float paso = 30 * scaledDensity;
        float y = paso;

        while (y < height) {
            for (int x = 0; x < width; x += 20) {
                canvas.drawLine(x, y, x + 10, y, linea);
            }
            canvas.drawText(String.valueOf(y), 10, y, texto);
            y += paso;
        }

        float cuartoAltura = height / 4.0f;
        canvas.drawText("fila: " + cuartoAltura + " scale= " + scaledDensity, 160, cuartoAltura, textoGrande);
        canvas.drawText("fila: " + (2 * cuartoAltura) + " width= " + width, 160, 2 * cuartoAltura, textoGrande);
        canvas.drawText("fila: " + (3 * cuartoAltura) + " height= " + height, 160, 3 * cuartoAltura, textoGrande);

        float ratio = (float) height / width;
        canvas.drawText("ratio= " + ratio, 160, 4 * cuartoAltura, textoGrande);
    }
}
