package com.gigawattstechnology.customer_side;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class unavailablestocktab extends Fragment {
TextView unavail;
DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_unavailablestocktab, container, false);
        unavail=view.findViewById(R.id.unavail);
        return view;
    }
}