package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom5.quanlylaptop.ActivityNV_Admin.Laptop_Manager_Activity;
import com.nhom5.quanlylaptop.NVA_Loader.QL_Laptop_Loader;
import com.nhom5.quanlylaptop.R;

import me.ibrahimsn.lib.CirclesLoadingView;

public class Tab_Laptop_Fragment extends Fragment {

    FloatingActionButton themLaptop;
    String TAG = "Tab_Laptop_Fragment_____";
    QL_Laptop_Loader QL_laptop_loader;
    RecyclerView reView;
    LinearLayout loadingView, linearLayoutEmpty;
    RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_laptop, container, false);
        themLaptop = view.findViewById(R.id.button_Add_Laptop);
        loadingView = view.findViewById(R.id.loadingView);
        relativeLayout = view.findViewById(R.id.layoutView);
        reView = view.findViewById(R.id.recyclerView_Laptop);
        linearLayoutEmpty = view.findViewById(R.id.linearLaptopEmpty);

        QL_laptop_loader = new QL_Laptop_Loader(getContext(), reView, loadingView, linearLayoutEmpty, relativeLayout);
        QL_laptop_loader.execute("");

        themLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Laptop_Manager_Activity.class));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        QL_laptop_loader = new QL_Laptop_Loader(getContext(), reView, loadingView, linearLayoutEmpty, relativeLayout);
        QL_laptop_loader.execute("");
    }
}