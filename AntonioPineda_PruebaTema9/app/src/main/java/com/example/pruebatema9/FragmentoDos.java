package com.example.pruebatema9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentoDos extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_dos, container, false);
        textView = view.findViewById(R.id.textoRecibido);
        return view;
    }

    public void actualizarTexto(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }
}