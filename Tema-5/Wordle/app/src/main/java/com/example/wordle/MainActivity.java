package com.example.wordle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private StringBuilder currentWord; // Para almacenar la palabra en construcción
    private String palabraElegida; // La palabra correcta a adivinar
    private final int WORD_LENGTH = 5; // Longitud de la palabra en Wordle
    private int attempt = 0; // Contador de intentos
    private int currentAttemptRow = 0; // Fila actual de intento
    private TextView[][] soluciones = new TextView[6][5]; // Array de TextViews para los intentos

    // Lista de palabras posibles
    private static final String[] palabras = {"juego", "lazos", "coche", "perro", "silla", "mujer", "banco"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos el array de TextViews
        initializeTextViews();

        currentWord = new StringBuilder();
        palabraElegida = getRandomWord(); // Elegir una palabra aleatoria
        setKeyboardListeners();
    }

    private void initializeTextViews() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                String textViewID = "textView_" + i + "_" + j;
                int resID = getResources().getIdentifier(textViewID, "id", getPackageName());
                soluciones[i][j] = findViewById(resID);
            }
        }
    }

    private String getRandomWord() {
        Random random = new Random();
        return palabras[random.nextInt(palabras.length)]; // Selecciona una palabra aleatoria
    }

    private void setKeyboardListeners() {
        // Configuramos los listeners para cada botón
        for (char letter = 'a'; letter <= 'z'; letter++) {
            final String letterStr = String.valueOf(letter).toUpperCase();
            int resID = getResources().getIdentifier("bt_" + letterStr.toLowerCase(), "id", getPackageName());
            findViewById(resID).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onKeyboardButtonClick(letterStr);
                }
            });
        }

        // Configurar el botón de enviar
        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWord(currentWord.toString());
            }
        });

        // Configurar el botón de limpiar
        findViewById(R.id.bt_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCurrentWord();
            }
        });
    }

    private void onKeyboardButtonClick(String letter) {
        if (currentWord.length() < WORD_LENGTH) {
            currentWord.append(letter); // Añadir letra a la palabra en construcción

            // Escribir la letra en el TextView correspondiente
            int columnIndex = currentWord.length() - 1; // Columna de la letra
            soluciones[currentAttemptRow][columnIndex].setText(letter); // Mostrar letra en el TextView

        }
    }

    private void checkWord(String word) {
        // Comprobar si la palabra ingresada es correcta
        if (word.length() == WORD_LENGTH) {
            if (word.equalsIgnoreCase(palabraElegida)) {
                // Aquí puedes reiniciar el juego o realizar otras acciones
                resetGame();
            } else {
                attempt++;
                // Aquí puedes dar pistas o manejar intentos fallidos

                if (attempt >= 6) {
                    resetGame();
                }
            }
        }
    }

    private void clearCurrentWord() {
        currentWord.setLength(0); // Limpiar la palabra en construcción
        // Limpiar los TextViews de la fila actual
        for (int i = 0; i < WORD_LENGTH; i++) {
            soluciones[currentAttemptRow][i].setText(""); // Limpiar TextViews
        }
    }

    private void resetGame() {
        currentWord.setLength(0);
        attempt = 0;
        currentAttemptRow = 0; // Reiniciar la fila de intentos
        palabraElegida = getRandomWord(); // Selecciona una nueva palabra
        clearCurrentWord(); // Limpiar los TextViews
    }
}
