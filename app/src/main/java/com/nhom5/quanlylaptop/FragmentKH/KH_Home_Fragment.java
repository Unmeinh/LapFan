package com.nhom5.quanlylaptop.FragmentKH;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.R;

public class KH_Home_Fragment extends Fragment {

    ImageView laptopView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_home, container, false);
        laptopView = view.findViewById(R.id.imageView_Laptop);
        return view;
    }

}