package com.example.bangjenggotsproject_2019130014;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeAdmin extends Fragment implements View.OnClickListener {
    private ImageView dineinadmin;
    private ImageView deliveryadmin;

    public HomeAdmin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);

        dineinadmin = view.findViewById(R.id.dineinadmin);
        deliveryadmin = view.findViewById(R.id.deliveryadmin);

        dineinadmin.setOnClickListener(this);
        deliveryadmin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == dineinadmin){
            startActivity(new Intent(getActivity(), DineInAdmin.class));
        }

        if(view == deliveryadmin){
            startActivity(new Intent(getActivity(), DeliveryAdmin.class));
        }
    }
}