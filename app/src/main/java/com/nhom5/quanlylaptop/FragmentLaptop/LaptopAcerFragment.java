package com.nhom5.quanlylaptop.FragmentLaptop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Laptop_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
public class LaptopAcerFragment extends Fragment {

    LaptopDAO laptopDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    KH_Laptop_Adapter kh_laptop_adapter;
    RecyclerView recyclerView;
    String TAG = "LaptopAcerFragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_laptop_acer, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_Laptop_Acer);
        laptopDAO = new LaptopDAO(getContext());
        listLap = laptopDAO.selectLaptop(null, "maHangLap=?", new String[]{"LAcer"}, null);
        kh_laptop_adapter = new KH_Laptop_Adapter(listLap, getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(kh_laptop_adapter);
        return view;
    }
}