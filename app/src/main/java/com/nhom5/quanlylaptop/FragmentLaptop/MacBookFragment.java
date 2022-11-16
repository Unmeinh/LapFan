package com.nhom5.quanlylaptop.FragmentLaptop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Laptop_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MacBookFragment extends Fragment {

    LaptopDAO laptopDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    KH_Laptop_Adapter kh_laptop_adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mac_book, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_Macbook);
        laptopDAO = new LaptopDAO(getContext());
        listLap = laptopDAO.selectLaptop(null, "maHangLap=?", new String[]{"LMac"}, null);
        kh_laptop_adapter = new KH_Laptop_Adapter(listLap, getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(kh_laptop_adapter);
        return view;
    }
}