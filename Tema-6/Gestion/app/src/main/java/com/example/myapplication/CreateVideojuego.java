package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class CreateVideojuego extends AppCompatActivity {

    private EditText editNombre, editDescripcion, editUrl, editTelefono;
    private RatingBar ratingBar;
    private Button btnCrear;
    private ImageView imgPortada;  // Para mostrar la portada
    private int positionToEdit = -1; // Para saber qué posición estamos editando

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
        imgPortada = findViewById(R.id.imgPortada);  // Agregar referencia a la ImageView

        // Comprobar si estamos editando o creando
        Intent intent = getIntent();
        Videojuego videojuegoAEditar = (Videojuego) intent.getSerializableExtra("videojuego");

        if (videojuegoAEditar != null) {
            // Si estamos editando, llenar los campos con los datos actuales
            editNombre.setText(videojuegoAEditar.getNombre());
            editDescripcion.setText(videojuegoAEditar.getDescripcion());
            editUrl.setText(videojuegoAEditar.getWeb());
            editTelefono.setText(videojuegoAEditar.getTelefono());
            ratingBar.setRating(videojuegoAEditar.getValoracion());

            // Mostrar la portada del videojuego actual
            imgPortada.setImageResource(videojuegoAEditar.getPortadaResId());

            // Guardar la posición para editar
            positionToEdit = intent.getIntExtra("position", -1);
            btnCrear.setText("Editar Videojuego");
        } else {
            // Si no estamos editando, dejar los campos vacíos para crear un nuevo videojuego
            btnCrear.setText("Crear Videojuego");
        }

        // Configurar botón de crear o editar
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recoger datos del formulario
                String nombre = editNombre.getText().toString();
                String descripcion = editDescripcion.getText().toString();
                String url = editUrl.getText().toString();
                String telefono = editTelefono.getText().toString();
                float valoracion = ratingBar.getRating();

                Videojuego videojuego;

                if (positionToEdit == -1) {
                    // Crear un objeto Videojuego
                    videojuego = new Videojuego(
                            nombre,
                            descripcion,
                            R.drawable.default_image, // Imagen predeterminada (solo cuando se crea uno nuevo)
                            false,                   // No jugado por defecto
                            valoracion,
                            url,
                            telefono
                    );
                } else {
                    // Editar el objeto Videojuego existente, mantener la portada original
                    int portadaResId = intent.getIntExtra("portadaResId", R.drawable.default_image);

                    videojuego = new Videojuego(
                            nombre,
                            descripcion,
                            portadaResId,  // Usar la imagen original si estamos editando
                            false,                    // Puedes cambiar esta lógica si es necesario
                            valoracion,
                            url,
                            telefono
                    );
                }

                // Guardar el objeto en el intent de resultado
                Intent resultIntent = new Intent();
                resultIntent.putExtra("videojuego", videojuego);
                resultIntent.putExtra("position", positionToEdit);

                setResult(Activity.RESULT_OK, resultIntent);

                // Finalizar actividad
                finish();
            }
        });
    }
}
