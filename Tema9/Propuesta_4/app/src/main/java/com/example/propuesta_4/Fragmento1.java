package com.example.propuesta_4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class Fragmento1 extends ListFragment {

    private Callbacks mCallbacks=CallbacksVacios;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ENT_LISTA) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textoTitulo);
                texto_superior_entrada.setText(((Contenido.Lista_entrada) entrada).textoEncima);
                ImageView imagen_emtrada = (ImageView) view.findViewById(R.id.imagenlista);
                imagen_emtrada.setImageResource(((Contenido.Lista_entrada) entrada).idImagen);
            }
        });
    }

    public interface Callbacks{
        public void onEntradaSeleccionada(String id);
    }

    private static Callbacks CallbacksVacios = new Callbacks() {
        @Override
        public void onEntradaSeleccionada(String id) {

        }
    };

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        mCallbacks=(Callbacks) activity;
    }

    public Fragmento1(){ }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallbacks=CallbacksVacios;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallbacks.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).id);
    }



}