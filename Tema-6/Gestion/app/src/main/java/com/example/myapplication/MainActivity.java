package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideojuegoAdapter adapter;
    private List<Videojuego> listaVideojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar RecyclerView y lista de videojuegos
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear lista de videojuegos con imágenes
        listaVideojuegos = new ArrayList<>();
        listaVideojuegos.add(new Videojuego("The Witcher 3", "RPG épico de mundo abierto", R.drawable.witcher, true, 4.5f, "https://thewitcher.com", "123456789"));
        listaVideojuegos.add(new Videojuego("Cyberpunk 2077", "Juego de rol de acción y aventura", R.drawable.cyberpunk, false, 3.5f, "https://cyberpunk.net", "987654321"));
        listaVideojuegos.add(new Videojuego("Smash Ultimate", "Juego de peleas en plataforma", R.drawable.smash, true, 4.5f, "https://cyberpunk.net", "627162399"));

        adapter = new VideojuegoAdapter(listaVideojuegos);
        recyclerView.setAdapter(adapter);
    }
}
