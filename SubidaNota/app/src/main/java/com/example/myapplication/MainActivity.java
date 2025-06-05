package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Forzar modo claro --> Hecho para evitar problemas de compatibilidad con el estilo del gráfico
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        barChart = findViewById(R.id.barChart);

        //Datos
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 9f));  //Matemáticas
        entries.add(new BarEntry(1f, 7f));  //Historia
        entries.add(new BarEntry(2f, 8f));  //Biología
        entries.add(new BarEntry(3f, 6f));  //Física
        entries.add(new BarEntry(4f, 8.5f)); //Química

        //DataSet
        BarDataSet dataSet = new BarDataSet(entries, "Notas del Estudiante");
        dataSet.setColors(new int[]{
                Color.parseColor("#F44336"), //Matemáticas
                Color.parseColor("#2196F3"), //Historia
                Color.parseColor("#4CAF50"), //Biología
                Color.parseColor("#FF9800"), //Física
                Color.parseColor("#9C27B0")  //Química
        });

        //Datos para el gráfico
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f); //Grosor de barras
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate(); //Redibujar

        //Etiquetas del eje X
        String[] subjects = {"Matemáticas", "Historia", "Biología", "Física", "Química"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(subjects));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        //Leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true);
        legend.setForm(Legend.LegendForm.SQUARE);

        //Descripción
        float averageScore = (9f + 7f + 8f + 6f + 8.5f) / 5;
        Description description = new Description();
        description.setText("Puntuación media: " + averageScore);
        barChart.setDescription(description);

        //Animación
        barChart.animateY(1000);

        //Listener
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int index = (int) e.getX();
                float score = e.getY();
                if (index >= 0 && index < subjects.length) {
                    String subject = subjects[index];
                    Toast.makeText(MainActivity.this, subject + ": " + score, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected() { }
        });
    }
}
