package com.gigawattstechnology.marketo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class logouttab extends Fragment {
    TextView supermarket,manager,address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_logouttab, container, false);
        supermarket=view.findViewById(R.id.supermarketprofile);
        manager=view.findViewById(R.id.managerprofile);
        address=view.findViewById(R.id.addressprofile);
        supermarket.setText(authtransfer.givesupermarket());
        manager.setText(authtransfer.givemanager());
        address.setText(authtransfer.giveaddress());
        return view;
    }
}