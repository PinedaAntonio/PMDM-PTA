package com.example.responsivegestion;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class Fragmento1 extends ListFragment {

    private Callbacks mCallbacks = CallbacksVacios;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuramos el adaptador personalizado para la lista
        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ENT_LISTA) {
            @Override
            public void onEntrada(Object entrada, View view) {
                // Convertimos la entrada al tipo Lista_entrada
                Contenido.Lista_entrada listaEntrada = (Contenido.Lista_entrada) entrada;

                // Configuramos el título
                TextView textoTitulo = view.findViewById(R.id.textoTitulo);
                textoTitulo.setText(listaEntrada.textoEncima);

                // Configuramos la imagen
                ImageView imagenLista = view.findViewById(R.id.imagenlista);
                imagenLista.setImageResource(listaEntrada.idImagen);

                // Configuramos el texto debajo si el layout tiene ese TextView
                TextView textoDebajo = view.findViewById(R.id.textoContenido);
                if (textoDebajo != null) {
                    textoDebajo.setText(listaEntrada.textoDebajo);
                }
            }
        });
    }

    public interface Callbacks {
        void onEntradaSeleccionada(String id);
    }

    private static final Callbacks CallbacksVacios = new Callbacks() {
        @Override
        public void onEntradaSeleccionada(String id) {
            // Callback vacío, no realiza ninguna acción
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            mCallbacks = (Callbacks) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement Callbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = CallbacksVacios;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Llamamos al callback con el ID de la entrada seleccionada
        mCallbacks.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).id);
    }
}
