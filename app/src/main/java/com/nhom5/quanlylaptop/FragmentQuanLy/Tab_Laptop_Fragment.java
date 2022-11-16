package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom5.quanlylaptop.Activity.Laptop_Manager_Activity;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.UrlToBitmap;

public class Tab_Laptop_Fragment extends Fragment {

    FloatingActionButton themLaptop;
    String TAG = "Tab_Laptop_Fragment_____";
    UrlToBitmap urlToBitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_laptop, container, false);
        themLaptop = view.findViewById(R.id.button_Add_Laptop);

        String[] url = {"https://cdn.tgdd.vn/Products/Images/44/279259/asus-tuf-gaming-fx506lhb-i5-hn188w-600x600.jpeg",
                "https://cdn.tgdd.vn/Products/Images/44/270031/asus-rog-strix-gaming-g513ih-r7-4800h-8gb-512gb-4gb-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/253582/apple-macbook-pro-16-m1-max-2021-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/282828/apple-macbook-pro-13-inch-m2-2022-xam-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/291152/hp-240-g8-i3-617k6pa-thumb-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/296789/hp-envy-x360-13-bf0090tu-i7-76b13pa-101122-093057-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/285965/hp-pavilion-x360-14-ek0055tu-i7-6l293pa-270822-110932-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/265015/acer-travelmate-b3-tmb311-31-c2hb-n4020-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/283458/acer-nitro-5-tiger-an515-58-773y-i7-nhqfksv001-thumb-600x600.jpg",
                "https://cdn.tgdd.vn/Products/Images/44/260171/dell-gaming-g15-5515-r5-p105f004dgr-291121-114930-600x600.jpg"};
        urlToBitmap = new UrlToBitmap(Tab_Laptop_Fragment.this, getContext());
        urlToBitmap.execute(url);

        themLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Laptop_Manager_Activity.class));
            }
        });
        return view;
    }

}