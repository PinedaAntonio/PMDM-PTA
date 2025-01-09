package com.example.propuesta_2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout fragmentOneContainer = new LinearLayout(this);
        fragmentOneContainer.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 2
        ));

        LinearLayout fragmentTwoContainer = new LinearLayout(this);
        fragmentTwoContainer.setLayoutParams(new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT, 1
        ));

        fragmentOneContainer.setId(View.generateViewId());
        fragmentTwoContainer.setId(View.generateViewId());

        mainLayout.addView(fragmentOneContainer);
        mainLayout.addView(fragmentTwoContainer);

        setContentView(mainLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(fragmentOneContainer.getId(), new FragmentOne());
        transaction.add(fragmentTwoContainer.getId(), new FragmentTwo());

        transaction.commit();
    }
}