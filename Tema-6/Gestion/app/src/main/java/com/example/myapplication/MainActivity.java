package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideojuegoAdapter adapter;
    private List<Videojuego> listaVideojuegos;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 100;
    private int selectedItemPosition;
    private List<Videojuego> original;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaVideojuegos = databaseHelper.getAllVideojuegos();
        adapter = new VideojuegoAdapter(listaVideojuegos, this::onItemLongClick, databaseHelper);
        recyclerView.setAdapter(adapter);

        original = new ArrayList<>(listaVideojuegos);

        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateVideojuego.class);
            startActivityForResult(intent, 1);
        });

        ImageButton btnVoice = findViewById(R.id.btn_voice);
        btnVoice.setOnClickListener(v -> startVoiceRecognition());
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
        } else if (item.getItemId() == R.id.app_info) {
            mostrarInformacionApp();
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

    private void mostrarInformacionApp() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.info_uso);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (Exception e) {
            stringBuilder.append(getString(R.string.error_loading_info));
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.app_info)
                .setMessage(stringBuilder.toString())
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private void confirmDeleteVideojuego(int position) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_video_game)
                .setMessage(R.string.confirm_delete)
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    Videojuego videojuegoToDelete = listaVideojuegos.get(position);

                    boolean isDeleted = databaseHelper.deleteVideojuego(videojuegoToDelete.getId());

                    if (isDeleted) {
                        listaVideojuegos.remove(position);
                        adapter.notifyItemRemoved(position);
                    } else {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.error)
                                .setMessage(R.string.delete_failed)
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
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



    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_prompt));
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    private void processVoiceCommand(String command) {
        if (command.toLowerCase().contains("buscar")) {
            String query = command.replace("buscar", "").trim();
            searchVideojuego(query);
        } else if (command.toLowerCase().contains("crear")) {
            Intent intent = new Intent(this, CreateVideojuego.class);
            startActivityForResult(intent, 1);
        } else if (command.toLowerCase().contains("lista completa")){
            listaVideojuegos.clear();
            listaVideojuegos.addAll(databaseHelper.getAllVideojuegos());
            adapter.updateList(listaVideojuegos);
        } else if (command.toLowerCase().contains("eliminar")){
            String query = command.replace("eliminar", "").trim();
            deleteVideojuegoByVoice(query);
        } else if (command.contains("actualizar")) {
            String query = command.replace("actualizar", "").trim();
            editVideojuegoByVoice(query);
        }
    }


    private void searchVideojuego(String query) {
        listaVideojuegos.clear();
        listaVideojuegos.addAll(original);
        List<Videojuego> filteredList = new ArrayList<>();
        for (Videojuego v : listaVideojuegos) {
            if (v.getNombre().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(v);
            }
        }
        adapter.updateList(filteredList);
    }

    private void deleteVideojuegoByVoice(String query) {
        for (int i = 0; i < listaVideojuegos.size(); i++) {
            Videojuego videojuego = listaVideojuegos.get(i);
            if (query.toLowerCase().equals(videojuego.getNombre().toLowerCase())) {
                confirmDeleteVideojuego(i);
                return;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(getString(R.string.videojuego_not_found, query))
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private void editVideojuegoByVoice(String query) {
        for (int i = 0; i < listaVideojuegos.size(); i++) {
            Videojuego videojuego = listaVideojuegos.get(i);
            if (query.toLowerCase().equals(videojuego.getNombre().toLowerCase())) {
                editVideojuego(i);
                return;
            }
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(getString(R.string.videojuego_not_found, query))
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) { // New game
                Videojuego nuevoVideojuego = (Videojuego) data.getSerializableExtra("videojuego");
                long id = databaseHelper.addVideojuego(nuevoVideojuego);
                nuevoVideojuego.setId((int) id);
                listaVideojuegos.add(nuevoVideojuego);
                original.add(nuevoVideojuego);
                adapter.notifyItemInserted(listaVideojuegos.size() - 1);
            } else if (requestCode == 2) { // Edit game
                int position = data.getIntExtra("position", -1);
                if (position >= 0) {
                    Videojuego videojuegoEditado = (Videojuego) data.getSerializableExtra("videojuego");
                    adapter.editItem(position, videojuegoEditado);
                }
            } else if (requestCode == VOICE_RECOGNITION_REQUEST_CODE) {
                ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (matches != null && !matches.isEmpty()) {
                    processVoiceCommand(matches.get(0));
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listaVideojuegos", new ArrayList<>(listaVideojuegos));
        outState.putSerializable("original", new ArrayList<>(original));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            listaVideojuegos.clear();
            listaVideojuegos.addAll((List<Videojuego>) savedInstanceState.getSerializable("listaVideojuegos"));

            original.clear();
            original.addAll((List<Videojuego>) savedInstanceState.getSerializable("original"));

            adapter.notifyDataSetChanged();
        }
    }
}
