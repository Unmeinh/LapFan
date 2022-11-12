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

public class Tab_HangLaptop_Fragment extends Fragment {

    List<HashMap<String, Object>> list = new ArrayList<>();
    HashMap<String, Object> hashMap;
    String[] from = {"img", "name"};
    int[] to = {R.id.imageView_HangLaptop, R.id.textView_TenHang_Laptop};
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_hang_laptop, container, false);
        gridView = view.findViewById(R.id.gridView_hangLaptop);
        setUpGridView();
        return view;
    }

    private void setUpGridView(){
        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.img_laptop_dell);
        hashMap.put("name", "Laptop Dell");
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.img_laptop_hp);
        hashMap.put("name", "Laptop HP");
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.img_laptop_asus);
        hashMap.put("name", "Laptop Asus");
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.img_laptop_razer);
        hashMap.put("name", "Laptop Razer");
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("img", R.drawable.img_laptop_samsung);
        hashMap.put("name", "Laptop Samsung");
        list.add(hashMap);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.cardview_nva_hanglaptop, from, to);
        gridView.setAdapter(simpleAdapter);
    }
}