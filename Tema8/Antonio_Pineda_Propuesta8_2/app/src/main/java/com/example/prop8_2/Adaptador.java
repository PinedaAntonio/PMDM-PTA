package com.example.prop8_2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adaptador extends FragmentStatePagerAdapter {
    private int numTabs;

    public Adaptador(@NonNull FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Primero();
            case 1:
                return new Segundo();
            case 2:
                return new Tercero();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
