package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateVideojuego extends AppCompatActivity {

    private EditText editNombre, editDescripcion, editUrl, editTelefono;
    private RatingBar ratingBar;
    private Button btnCrear;
    private ImageView imgPortada;
    private DatePicker editFechaLanzamiento;
    private int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_videojuego);

        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
        editUrl = findViewById(R.id.editUrl);
        editTelefono = findViewById(R.id.editTelefono);
        ratingBar = findViewById(R.id.ratingBar);
        btnCrear = findViewById(R.id.btnCrear);
        imgPortada = findViewById(R.id.imgPortada);
        editFechaLanzamiento = findViewById(R.id.editFechaLanzamiento);

        Intent intent = getIntent();
        Videojuego videojuegoAEditar = (Videojuego) intent.getSerializableExtra("videojuego");

        if (videojuegoAEditar != null) {
            editNombre.setText(videojuegoAEditar.getNombre());
            editDescripcion.setText(videojuegoAEditar.getDescripcion());
            editUrl.setText(videojuegoAEditar.getWeb());
            editTelefono.setText(videojuegoAEditar.getTelefono());
            ratingBar.setRating(videojuegoAEditar.getValoracion());

            String fechaLanzamiento = videojuegoAEditar.getFechaLanzamiento();
            String[] fechaPartes = fechaLanzamiento.split("/");
            int day = Integer.parseInt(fechaPartes[0]);
            int month = Integer.parseInt(fechaPartes[1]) - 1;
            int year = Integer.parseInt(fechaPartes[2]);

            editFechaLanzamiento.updateDate(year, month, day);

            imgPortada.setImageResource(videojuegoAEditar.getPortadaResId());

            positionToEdit = intent.getIntExtra("position", -1);
            btnCrear.setText(R.string.edit_video_game);
        } else {
            btnCrear.setText(R.string.add_videojuego);
        }

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String descripcion = editDescripcion.getText().toString();
                String url = editUrl.getText().toString();
                String telefono = editTelefono.getText().toString();
                float valoracion = ratingBar.getRating();

                int day = editFechaLanzamiento.getDayOfMonth();
                int month = editFechaLanzamiento.getMonth();
                int year = editFechaLanzamiento.getYear();

                Calendar fechaSeleccionada = Calendar.getInstance();
                fechaSeleccionada.set(year, month, day);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String fechaLanzamiento = dateFormat.format(fechaSeleccionada.getTime());

                Videojuego videojuego;

                if (positionToEdit == -1) {
                    videojuego = new Videojuego(nombre, descripcion, R.drawable.default_image, false, valoracion, url, telefono, fechaLanzamiento);
                } else {
                    int portadaResId = intent.getIntExtra("portadaResId", R.drawable.default_image);
                    videojuego = new Videojuego(nombre, descripcion, portadaResId, false, valoracion, url, telefono, fechaLanzamiento
                    );
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("videojuego", videojuego);
                resultIntent.putExtra("position", positionToEdit);

                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });
    }
}