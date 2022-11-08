package com.nhom5.quanlylaptop.FragmentKH;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nhom5.quanlylaptop.ActivityKH.InfoLaptopActivity;
import com.nhom5.quanlylaptop.R;

public class KH_Home_Fragment extends Fragment {

    ImageView laptopView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_home, container, false);
        laptopView = view.findViewById(R.id.home_kh_Laptop_View);
        laptopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InfoLaptopActivity.class));
            }
        });
        return view;
    }

}