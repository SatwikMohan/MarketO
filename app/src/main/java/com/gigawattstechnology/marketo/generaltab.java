package com.gigawattstechnology.marketo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class generaltab extends Fragment {
ImageView status,addstock,removestock ;
Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_generaltab, container, false);
        context=getContext();
        status=view.findViewById(R.id.statusmarket);
        addstock=view.findViewById(R.id.addstock);
        removestock=view.findViewById(R.id.removestock);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), Status.class);
                getActivity().startActivity(i);

            }
        });
        addstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),addstock.class);
                getActivity().startActivity(i);

            }
        });
        removestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),removestock.class);
                getActivity().startActivity(i);

            }
        });
        return view;
    }
}