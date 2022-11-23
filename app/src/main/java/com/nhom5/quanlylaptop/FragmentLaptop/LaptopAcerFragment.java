package com.nhom5.quanlylaptop.FragmentLaptop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhom5.quanlylaptop.Entity.Photo;
import com.nhom5.quanlylaptop.KH_Loader.KH_Laptop_Loader;
import com.nhom5.quanlylaptop.PagerAdapter.Photo_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import me.relex.circleindicator.CircleIndicator;

public class LaptopAcerFragment extends Fragment {

    List<Photo> list = new ArrayList<>();
    ViewPager viewPager;
    Photo_Adapter photoAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laptop_acer, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        CircleIndicator circleIndicator = view.findViewById(R.id.circleIndicator);

        photoAdapter = new Photo_Adapter(requireContext(), getListPhoto());
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        KH_Laptop_Loader kh_laptop_loader = new KH_Laptop_Loader(getContext(), view.findViewById(R.id.recyclerView_Laptop_Acer));
        kh_laptop_loader.execute("LAcer");

        return view;
    }

    private List<Photo> getListPhoto() {
        list.add(new Photo(R.drawable.img_laptop_acer));
        list.add(new Photo(R.drawable.ac1));
        list.add(new Photo(R.drawable.ac2));
        list.add(new Photo(R.drawable.ac3));
        list.add(new Photo(R.drawable.ac4));
        return list;
    }
}