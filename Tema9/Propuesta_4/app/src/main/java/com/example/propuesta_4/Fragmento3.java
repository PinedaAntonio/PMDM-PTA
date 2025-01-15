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

    public static final String ARG_ID_ENTRADA_SELECCIONADA = "item_id";

    private Contenido.Lista_entrada mItem;

    public Fragmento3() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_ID_ENTRADA_SELECCIONADA)) {
            mItem = Contenido.ENT_LISTA_HASMAP.get(getArguments().getString(ARG_ID_ENTRADA_SELECCIONADA));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textotitulo)).setText(mItem.textoEncima);
            ((TextView) rootView.findViewById(R.id.textocontenido)).setText(mItem.textoDebajo);
            ((ImageView) rootView.findViewById(R.id.imagen)).setImageResource(mItem.idImagen);
        }

        return rootView;
    }
}
