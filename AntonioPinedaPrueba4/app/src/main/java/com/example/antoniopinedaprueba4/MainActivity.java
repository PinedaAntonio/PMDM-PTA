package com.example.antoniopinedaprueba4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PintorAdapter adapter;
    private List<Pintor> listaPintores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaPintores = new ArrayList<>();
        listaPintores.add(new Pintor("CARAVAGGIO", "Pintor italiano entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco.", R.drawable.caravaggio));
        listaPintores.add(new Pintor("RAFAEL SANZIO", "Pintor y arquitecto italiano del Renacimiento, realizó importantes aportes en la arquitectura y, como inspector de antigüedades.", R.drawable.rafael));
        listaPintores.add(new Pintor("VELAZQUEZ", "Pintor Barroco nacido en Sevilla en 1599, es considerado uno de los máximos exponentes de la pintura española y maestro de la pintura universal.", R.drawable.velazquez));
        listaPintores.add(new Pintor("MIGUEL ANGEL", "Arquitecto, escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia.", R.drawable.miguelangel));
        listaPintores.add(new Pintor("REMBRANDT", "Pintor y grabador holandés. La historia del arte le considera uno de los mayores maestros barrocos de la pintura y el grabado.", R.drawable.rembrant));
        listaPintores.add(new Pintor("BOTICELLI", "Apodado Sandro Botticelli, fue un pintor cuatrocentista italiano. Su obra se ha considerado representativa de la pintura del primer Renacimiento.", R.drawable.boticelli));
        listaPintores.add(new Pintor("LEONARDO DA VINCI", "Notable polímata del Renacimiento italiano (a la vez anatomista, arquitecto, artista, botánico, científico, escritor, escultor, filósofo, ingeniero...)", R.drawable.leonardo));
        listaPintores.add(new Pintor("RENOIR", "Pintor francés impresionista, interesado por la pintura de cuerpos femeninos en paisajes, inspirados a menudo en pinturas clásicas renacentistas y barrocas.", R.drawable.renoir));

        adapter = new PintorAdapter(listaPintores);
        recyclerView.setAdapter(adapter);
    }
}
