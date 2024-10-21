package com.example.propuesta5_4;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Obtener referencia al EditText
        EditText editText = findViewById(R.id.editText);

        // Cambiar el tipo de teclado para texto normal
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        // Ejemplo de otros tipos de teclado:

        // Teclado para números
        // editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Teclado para números decimales
        // editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        // Teclado para números de teléfono
        // editText.setInputType(InputType.TYPE_CLASS_PHONE);

        // Teclado para correo electrónico
        // editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        // Configurar insets para el ajuste del layout con los bordes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
