package com.example.diagonalescanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DiagonalesCanvasView extends View {
    private Paint linePaint, textPaint;
    private float scaledDensity;

    public DiagonalesCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        scaledDensity = getResources().getDisplayMetrics().scaledDensity;

        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(2 * scaledDensity);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(16 * scaledDensity);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Establecer el fondo amarillo
        canvas.drawColor(Color.YELLOW);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int right = getRight();
        int bottom = getBottom();

        // Dibujar diagonales
        canvas.drawLine(0, 0, width, height, linePaint); // Diagonal de esquina superior izquierda a inferior derecha
        canvas.drawLine(0, height, width, 0, linePaint); // Diagonal de esquina inferior izquierda a superior derecha

        // Dibujar texto en el centro arriba
        canvas.drawText("Width: " + width + " Height: " + height + " Right: " + right + " Bottom: " + bottom, width / 2, 30 * scaledDensity, textPaint);
    }
}
