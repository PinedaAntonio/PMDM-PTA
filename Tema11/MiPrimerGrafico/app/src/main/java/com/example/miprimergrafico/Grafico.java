package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class Grafico extends View {
    private Paint textPaint, linePaintRed, linePaintGreen;
    private float scaledDensity;
    private int width, height;

    public Grafico(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        scaledDensity = displayMetrics.scaledDensity;

        textPaint = new Paint();
        textPaint.setColor(0xFF8B0000); // Color rojo oscuro
        textPaint.setTextSize(16 * scaledDensity);

        linePaintRed = new Paint();
        linePaintRed.setColor(0xFFFF0000); // Rojo
        linePaintRed.setStrokeWidth(4);

        linePaintGreen = new Paint();
        linePaintGreen.setColor(0xFF00FF00); // Verde
        linePaintGreen.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("width: " + width + " height: " + height, 10 * scaledDensity, 30 * scaledDensity, textPaint);
        canvas.drawText("escala: " + scaledDensity, 10 * scaledDensity, 60 * scaledDensity, textPaint);

        canvas.drawLine(0, 40 * scaledDensity, width, 40 * scaledDensity, linePaintGreen); // Línea verde
        canvas.drawLine(20 * scaledDensity, 0, 20 * scaledDensity, height, linePaintRed); // Línea roja
    }
}
