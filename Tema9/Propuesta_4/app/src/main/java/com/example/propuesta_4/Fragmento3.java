package com.example.propuesta_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragmento3 extends Fragment {

    private Contenido.Lista_entrada miItem;

    public static final String ARG_ID_ENTRADA_SELECCIONADA = "item_id";

    public Fragmento3(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_ID_ENTRADA_SELECCIONADA)){
            miItem = Contenido.ENT_LISTA_HASHMAP.get(getArguments().getString(ARG_ID_ENTRADA_SELECCIONADA));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);
        if(miItem != null){
            ((TextView) rootView.findViewById(R.id.textoTitulo)).setText(miItem.textoEncima);
            ((TextView) rootView.findViewById(R.id.textocontenido)).setText(miItem.textoDebajo);
            ((ImageView) rootView.findViewById(R.id.imagen)).setImageResource(miItem.idImagen);
        }
        return rootView;
    }
}