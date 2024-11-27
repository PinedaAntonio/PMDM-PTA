package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

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



        // Crear lista de videojuegos
        listaVideojuegos = new ArrayList<>();
        listaVideojuegos.add(new Videojuego("The Witcher 3", "RPG épico de mundo abierto", R.drawable.witcher, true, 4.5f, "https://thewitcher.com", "123456789"));
        listaVideojuegos.add(new Videojuego("Cyberpunk 2077", "Juego de rol de acción y aventura", R.drawable.cyberpunk, false, 3.5f, "https://cyberpunk.net", "987654321"));
        listaVideojuegos.add(new Videojuego("Smash Ultimate", "Juego de peleas en plataforma", R.drawable.smash, true, 4.5f, "https://smashultimate", "627162399"));
        listaVideojuegos.add(new Videojuego("Sparking Zero", "Juego de peleas de Dragon Ball", R.drawable.sparking, true, 4f, "https://cyberpunk.net", "637253126"));

        adapter = new VideojuegoAdapter(listaVideojuegos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.sort_by_name) {
            listaVideojuegos.sort((v1, v2) -> v1.getNombre().compareToIgnoreCase(v2.getNombre()));
        } else if (itemId == R.id.sort_by_rating) {
            listaVideojuegos.sort((v1, v2) -> Float.compare(v2.getValoracion(), v1.getValoracion()));
        } else if (itemId == R.id.create_videojuego) {
            Intent intent = new Intent(this, CreateVideojuego.class);
            startActivityForResult(intent, 1);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Obtener el objeto Videojuego del Intent
            Videojuego nuevoVideojuego = (Videojuego) data.getSerializableExtra("videojuego");

            // Agregar el nuevo videojuego a la lista
            if (nuevoVideojuego != null) {
                listaVideojuegos.add(nuevoVideojuego);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
