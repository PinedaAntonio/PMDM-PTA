package com.example.propuesta_8_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.barra_nuevo) {
            mensaje(R.string.cadena_barra_nuevo);
            return true;
        } else if (id == R.id.barra_editar) {
            mensaje(R.string.cadena_barra_editar);
            return true;
        } else if (id == R.id.barra_configurar) {
            mensaje(R.string.cadena_barra_configurar);
            return true;
        } else if (id == R.id.barra_ayuda) {
            mensaje(R.string.cadena_barra_ayuda);
            return true;
        } else if (id == R.id.barra_acerca) {
            mensaje(R.string.cadena_barra_acerca);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void mensaje(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }
}
