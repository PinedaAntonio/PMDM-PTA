package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
    private RadioButton jugado;
    private int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_videojuego);

        // Inicialización de vistas
        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
        editUrl = findViewById(R.id.editUrl);
        editTelefono = findViewById(R.id.editTelefono);
        ratingBar = findViewById(R.id.ratingBar);
        btnCrear = findViewById(R.id.btnCrear);
        jugado = findViewById(R.id.jugado); // RadioButton para "Jugado"
        imgPortada = findViewById(R.id.imgPortada);
        editFechaLanzamiento = findViewById(R.id.editFechaLanzamiento);

        // Obtener datos si es edición
        Intent intent = getIntent();
        Videojuego videojuegoAEditar = (Videojuego) intent.getSerializableExtra("videojuego");

        if (videojuegoAEditar != null) {
            // Rellenar los campos con los datos del videojuego existente
            editNombre.setText(videojuegoAEditar.getNombre());
            editDescripcion.setText(videojuegoAEditar.getDescripcion());
            editUrl.setText(videojuegoAEditar.getWeb());
            editTelefono.setText(videojuegoAEditar.getTelefono());
            ratingBar.setRating(videojuegoAEditar.getValoracion());

            // Establecer la fecha de lanzamiento
            String fechaLanzamiento = videojuegoAEditar.getFechaLanzamiento();
            String[] fechaPartes = fechaLanzamiento.split("/");
            int day = Integer.parseInt(fechaPartes[0]);
            int month = Integer.parseInt(fechaPartes[1]) - 1;
            int year = Integer.parseInt(fechaPartes[2]);
            editFechaLanzamiento.updateDate(year, month, day);

            // Establecer imagen y estado de jugado
            imgPortada.setImageResource(videojuegoAEditar.getPortadaResId());
            jugado.setChecked(videojuegoAEditar.getJugado());

            // Configurar posición para edición
            positionToEdit = intent.getIntExtra("position", -1);
            btnCrear.setText(R.string.edit_video_game);
        } else {
            btnCrear.setText(R.string.add_videojuego);
        }

        // Configuración del botón "Crear/Editar"
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener datos ingresados por el usuario
                String nombre = editNombre.getText().toString();
                String descripcion = editDescripcion.getText().toString();
                String url = editUrl.getText().toString();
                String telefono = editTelefono.getText().toString();
                float valoracion = ratingBar.getRating();

                // Obtener la fecha seleccionada
                int day = editFechaLanzamiento.getDayOfMonth();
                int month = editFechaLanzamiento.getMonth();
                int year = editFechaLanzamiento.getYear();
                Calendar fechaSeleccionada = Calendar.getInstance();
                fechaSeleccionada.set(year, month, day);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String fechaLanzamiento = dateFormat.format(fechaSeleccionada.getTime());

                // Obtener estado del RadioButton
                boolean estadoJugado = jugado.isChecked();

                // Crear o editar el videojuego
                Videojuego videojuego;
                if (positionToEdit == -1) {
                    videojuego = new Videojuego(nombre, descripcion, R.drawable.default_image, estadoJugado, valoracion, url, telefono, fechaLanzamiento);
                } else {
                    int portadaResId = intent.getIntExtra("portadaResId", R.drawable.default_image);
                    videojuego = new Videojuego(nombre, descripcion, portadaResId, estadoJugado, valoracion, url, telefono, fechaLanzamiento);
                }

                // Devolver datos a la actividad principal
                Intent resultIntent = new Intent();
                resultIntent.putExtra("videojuego", videojuego);
                resultIntent.putExtra("position", positionToEdit);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
