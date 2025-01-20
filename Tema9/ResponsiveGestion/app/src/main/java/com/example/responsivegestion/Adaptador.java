package com.example.responsivegestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private ArrayList<Contenido.Lista_entrada> entradas;
    private int R_layout_IdView;
    private Context contexto;

    public Adaptador(Context contexto, int R_layout_IdView, ArrayList<Contenido.Lista_entrada> entradas) {
        this.contexto = contexto;
        this.R_layout_IdView = R_layout_IdView;
        this.entradas = entradas;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R_layout_IdView, null);
        }

        Contenido.Lista_entrada entrada = entradas.get(posicion);

        TextView titulo = convertView.findViewById(R.id.textoTitulo);
        TextView descripcion = convertView.findViewById(R.id.textoDescripcion); // Solo para layout_listado
        ImageView imagen = convertView.findViewById(R.id.imagenlista);

        titulo.setText(entrada.textoEncima);
        if (descripcion != null) {
            descripcion.setText(entrada.textoDebajo);
        }
        if (imagen != null) {
            imagen.setImageResource(entrada.idImagen);
        }

        return convertView;
    }
}
