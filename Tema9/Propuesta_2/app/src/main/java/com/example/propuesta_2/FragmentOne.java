package com.example.propuesta_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        layout.setGravity(android.view.Gravity.CENTER);

        TextView textView = new TextView(getContext());
        textView.setText("Fragmento Uno");
        textView.setTextSize(24);
        textView.setTextColor(getResources().getColor(android.R.color.white));

        layout.addView(textView);
        return layout;
    }
}
