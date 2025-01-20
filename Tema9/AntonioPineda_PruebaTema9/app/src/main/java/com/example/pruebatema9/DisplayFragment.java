package com.example.pruebatema9;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayFragment extends Fragment {

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        textView = view.findViewById(R.id.textViewDisplay);
        return view;
    }

    public void updateText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }
}