package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tab_Laptop_Fragment extends Fragment {

    List<HashMap<String, String>> list = new ArrayList<>();
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_laptop, container, false);
        gridView = view.findViewById(R.id.gridView_laptop);
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
        return view;
    }
}