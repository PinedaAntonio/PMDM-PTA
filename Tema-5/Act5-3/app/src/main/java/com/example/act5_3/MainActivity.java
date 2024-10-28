package com.example.act5_3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_NUM1 = "num1";
    private static final String KEY_NUM2 = "num2";
    private static final String KEY_CHECK_SUM = "checkSum";
    private static final String KEY_CHECK_REST = "checkRest";
    private static final String KEY_RESULTADO = "resultado";

    private EditText num1, num2;
    private CheckBox checkBoxSuma, checkBoxResta;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        checkBoxSuma = findViewById(R.id.checkBoxSuma);
        checkBoxResta = findViewById(R.id.checkBoxResta);
        Button buttonCalcular = findViewById(R.id.buttonCalcular);
        textViewResultado = findViewById(R.id.textViewResultado);

        if (savedInstanceState != null) {
            num1.setText(savedInstanceState.getString(KEY_NUM1, ""));
            num2.setText(savedInstanceState.getString(KEY_NUM2, ""));
            checkBoxSuma.setChecked(savedInstanceState.getBoolean(KEY_CHECK_SUM, false));
            checkBoxResta.setChecked(savedInstanceState.getBoolean(KEY_CHECK_REST, false));
            textViewResultado.setText(savedInstanceState.getString(KEY_RESULTADO, "Resultado: "));
        }

        buttonCalcular.setOnClickListener(v -> {
            if (num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Ingrese ambos números", Toast.LENGTH_SHORT).show();
                return;
            }

            int numero1 = Integer.parseInt(num1.getText().toString());
            int numero2 = Integer.parseInt(num2.getText().toString());

            StringBuilder resultado = new StringBuilder("Resultado: ");

            if (checkBoxSuma.isChecked()) {
                int suma = numero1 + numero2;
                resultado.append("Suma = ").append(suma).append(" ");
            }
            if (checkBoxResta.isChecked()) {
                int resta = numero1 - numero2;
                resultado.append("Resta = ").append(resta);
            }

            if (!checkBoxSuma.isChecked() && !checkBoxResta.isChecked()) {
                Toast.makeText(MainActivity.this, "Seleccione una operación", Toast.LENGTH_SHORT).show();
                return;
            }

            textViewResultado.setText(resultado.toString());
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NUM1, num1.getText().toString());
        outState.putString(KEY_NUM2, num2.getText().toString());
        outState.putBoolean(KEY_CHECK_SUM, checkBoxSuma.isChecked());
        outState.putBoolean(KEY_CHECK_REST, checkBoxResta.isChecked());
        outState.putString(KEY_RESULTADO, textViewResultado.getText().toString());
    }
}