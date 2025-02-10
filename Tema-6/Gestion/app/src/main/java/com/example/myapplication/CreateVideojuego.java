package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateVideojuego extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private EditText editNombre, editDescripcion, editUrl, editTelefono;
    private RatingBar ratingBar;
    private Button btnCrear, btnTomarFoto;
    private ImageView imgPortada;
    private DatePicker editFechaLanzamiento;
    private RadioButton jugado;
    private String currentPhotoPath;
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
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        jugado = findViewById(R.id.jugado);
        imgPortada = findViewById(R.id.imgPortada);
        editFechaLanzamiento = findViewById(R.id.editFechaLanzamiento);

        // Verificar si ya tenemos el permiso
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, solicitarlo
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }

        btnTomarFoto.setOnClickListener(v -> dispatchTakePictureIntent());

        btnCrear.setOnClickListener(v -> {
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

            boolean estadoJugado = jugado.isChecked();
            String portada = (currentPhotoPath != null) ? currentPhotoPath : null;

            Videojuego videojuego = new Videojuego(nombre, descripcion, R.drawable.default_image, portada, estadoJugado, valoracion, url, telefono, fechaLanzamiento);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("videojuego", videojuego);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            // Si el permiso ha sido concedido
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Aquí puedes abrir la cámara
                dispatchTakePictureIntent();
            } else {
                // Si el permiso no ha sido concedido, informa al usuario
                Toast.makeText(this, "Permiso de cámara no concedido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.myapplication.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile("JPEG_" + timeStamp + "_", ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


}
