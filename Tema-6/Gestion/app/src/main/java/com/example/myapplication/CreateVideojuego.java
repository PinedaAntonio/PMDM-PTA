package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.appcompat.app.AppCompatActivity;

public class CreateVideojuego extends AppCompatActivity {

    private EditText editNombre, editDescripcion, editUrl, editTelefono;
    private RatingBar ratingBar;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_videojuego);

        // Referenciar vistas
        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
        editUrl = findViewById(R.id.editUrl);
        editTelefono = findViewById(R.id.editTelefono);
        ratingBar = findViewById(R.id.ratingBar);
        btnCrear = findViewById(R.id.btnCrear);

        // Configurar botón de crear
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recoger datos del formulario
                String nombre = editNombre.getText().toString();
                String descripcion = editDescripcion.getText().toString();
                String url = editUrl.getText().toString();
                String telefono = editTelefono.getText().toString();
                float valoracion = ratingBar.getRating();

                // Crear un objeto Videojuego
                Videojuego nuevoVideojuego = new Videojuego(
                        nombre,
                        descripcion,
                        R.drawable.default_image, // Imagen predeterminada
                        false,                   // No jugado por defecto
                        valoracion,
                        url,
                        telefono
                );

                // Guardar el objeto en el intent de resultado
                setResult(Activity.RESULT_OK, getIntent().putExtra("videojuego", nuevoVideojuego));

                // Finalizar actividad
                finish();
            }
        });
    }
}
