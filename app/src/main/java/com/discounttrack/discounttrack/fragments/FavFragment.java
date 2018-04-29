package com.discounttrack.discounttrack.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.discounttrack.discounttrack.R;


public class FavFragment extends Fragment {

    View masterView;

    public FavFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        masterView = inflater.inflate( R.layout.fav_fragment, container, false );
        return masterView;
    }
}
