package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhom5.quanlylaptop.NVA_Loader.QL_HangLaptop_Loader;
import com.nhom5.quanlylaptop.R;

public class Tab_HangLaptop_Fragment extends Fragment {

    String TAG = "Tab_HangLaptop_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_hang_laptop, container, false);

        QL_HangLaptop_Loader QL_hangLaptop_loader = new QL_HangLaptop_Loader(Tab_HangLaptop_Fragment.this, getContext());
        QL_hangLaptop_loader.execute("");

        return view;
    }

}