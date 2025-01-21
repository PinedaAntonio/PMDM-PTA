package com.example.pruebatema9;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentoUno extends Fragment {

    private OnTextSendListener listener;

    public interface OnTextSendListener {
        void textoEnviado(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTextSendListener) {
            listener = (OnTextSendListener) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_uno, container, false);

        EditText editText = view.findViewById(R.id.textoEnviado);
        Button button = view.findViewById(R.id.buttonSend);

        button.setOnClickListener(v -> {
            String inputText = editText.getText().toString();
            if (listener != null) {
                listener.textoEnviado(inputText);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}