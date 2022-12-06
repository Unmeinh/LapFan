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
import com.nhom5.quanlylaptop.Support.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MacBookFragment extends Fragment {

    List<Photo> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mac_book, container, false);
        SliderView sliderView = view.findViewById(R.id.sliderView);

        list = setListPhoto();
        SliderAdapter sliderAdapter = new SliderAdapter(list);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        KH_Laptop_Loader kh_laptop_loader = new KH_Laptop_Loader(getContext(), view.findViewById(R.id.recyclerView_Macbook));
        kh_laptop_loader.execute("LMacBook");

        return view;
    }

    private List<Photo> setListPhoto() {
        list.add(new Photo(R.drawable.img_macbook));
        list.add(new Photo(R.drawable.mb1));
        list.add(new Photo(R.drawable.mb2));
        list.add(new Photo(R.drawable.mb3));
        list.add(new Photo(R.drawable.mb4));
        return list;
    }
}