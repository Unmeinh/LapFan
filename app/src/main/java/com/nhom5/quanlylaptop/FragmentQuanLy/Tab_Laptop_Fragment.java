package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom5.quanlylaptop.Activity.Laptop_Manager_Activity;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tab_Laptop_Fragment extends Fragment {

    List<HashMap<String, String>> list = new ArrayList<>();
    GridView gridView;
    FloatingActionButton themLaptop;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_laptop, container, false);
        gridView = view.findViewById(R.id.gridView_laptop);
        themLaptop = view.findViewById(R.id.button_Add_Laptop);
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.cardview_nva_laptop, new String[]{}, new int[]{});
        gridView.setAdapter(simpleAdapter);

        themLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Laptop_Manager_Activity.class));
            }
        });
        return view;
    }
}