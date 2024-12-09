package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
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
        listaVideojuegos.add(new Videojuego("The Witcher 3", "RPG épico de mundo abierto", R.drawable.witcher, true, 4.5f, "https://thewitcher.com", "123456789", "22/10/2012"));
        listaVideojuegos.add(new Videojuego("Cyberpunk 2077", "Juego de rol de acción y aventura", R.drawable.cyberpunk, false, 3.5f, "https://cyberpunk.net", "987654321", "12/07/2022"));
        listaVideojuegos.add(new Videojuego("Smash Ultimate", "Juego de peleas en plataforma", R.drawable.smash, true, 4.5f, "https://smashultimate", "627162399", "14/03/2018"));
        listaVideojuegos.add(new Videojuego("Sparking Zero", "Juego de peleas de Dragon Ball", R.drawable.sparking, true, 4f, "https://cyberpunk.net", "637253126", "02/08/2024"));

        adapter = new VideojuegoAdapter(listaVideojuegos, this::onItemLongClick);
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

    private void onItemLongClick(int position) {
        selectedItemPosition = position;
        registerForContextMenu(recyclerView);
        recyclerView.showContextMenu();
    }

    private int selectedItemPosition;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_videojuego) {
            editVideojuego(selectedItemPosition);
            return true;
        } else if (item.getItemId() == R.id.delete_videojuego) {
            confirmDeleteVideojuego(selectedItemPosition);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void confirmDeleteVideojuego(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Videojuego")
                .setMessage("¿Estás seguro de que deseas eliminar este videojuego?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    listaVideojuegos.remove(position);
                    adapter.notifyItemRemoved(position);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void editVideojuego(int position) {
        Videojuego videojuego = listaVideojuegos.get(position);

        Intent intent = new Intent(this, CreateVideojuego.class);

        intent.putExtra("videojuego", videojuego);
        intent.putExtra("position", position);

        intent.putExtra("portadaResId", videojuego.getPortadaResId());

        startActivityForResult(intent, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Videojuego nuevoVideojuego = (Videojuego) data.getSerializableExtra("videojuego");
                listaVideojuegos.add(nuevoVideojuego);
                adapter.notifyItemInserted(listaVideojuegos.size() - 1);
            } else if (requestCode == 2) {
                int position = data.getIntExtra("position", -1);
                if (position >= 0) {
                    Videojuego videojuegoEditado = (Videojuego) data.getSerializableExtra("videojuego");
                    listaVideojuegos.set(position, videojuegoEditado);
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }


}

