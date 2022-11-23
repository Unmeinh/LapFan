package com.nhom5.quanlylaptop.FragmentLaptop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.Photo;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Laptop_Adapter;
import com.nhom5.quanlylaptop.KH_Loader.KH_Laptop_Loader;
import com.nhom5.quanlylaptop.PagerAdapter.Photo_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class LaptopAsusFragment extends Fragment {

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<Photo> list = new ArrayList<>();
    Photo_Adapter photoAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laptop_asus, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.circleIndicator);

        list = getListPhoto();
        photoAdapter = new Photo_Adapter(requireContext(), list);
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        KH_Laptop_Loader kh_laptop_loader = new KH_Laptop_Loader(getContext(), view.findViewById(R.id.recyclerView_Laptop_Asus));
        kh_laptop_loader.execute("LAsus");

        return view;
    }

    private List<Photo> getListPhoto() {
        list.add(new Photo(R.drawable.img_laptop_asus));
        list.add(new Photo(R.drawable.ar1));
        list.add(new Photo(R.drawable.ar2));
        list.add(new Photo(R.drawable.ar3));
        list.add(new Photo(R.drawable.ar4));
        return list;
    }
}