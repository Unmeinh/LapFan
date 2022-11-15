package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom5.quanlylaptop.Activity.Laptop_Manager_Activity;
import com.nhom5.quanlylaptop.BaseAdapter.QL_Laptop_Adapter;
import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;
import com.nhom5.quanlylaptop.Support.UrlToBitmap;

import java.util.ArrayList;

public class Tab_Laptop_Fragment extends Fragment {

    FloatingActionButton themLaptop;
    String TAG = "Tab_Laptop_Fragment_____";
    Bitmap[] bitmap;
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

//    public void setUpGridView() {
//        listHang = hangLaptopDAO.selectHangLaptop(null, null, null, null);
//        listLap = laptopDAO.selectLaptop(null, null, null, null);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        if (listLap == null) {
//            Log.d(TAG, "onCreateView: list null");
//            addDemoLaptop();
//            ql_laptop_adapter = new QL_Laptop_Adapter(listLap,listHang, getContext());
//            recyclerView.setAdapter(ql_laptop_adapter);
//        } else {
//            if (listLap.size() == 0) {
//                Log.d(TAG, "onCreateView: list not null");
//                Log.d(TAG, "onCreateView: list size = " + listLap.size());
//                addDemoLaptop();
//                ql_laptop_adapter = new QL_Laptop_Adapter(listLap,listHang, getContext());
//                recyclerView.setAdapter(ql_laptop_adapter);
//            } else {
//                Log.d(TAG, "onCreateView: list not null");
//                Log.d(TAG, "onCreateView: list size = " + listLap.size());
//                ql_laptop_adapter = new QL_Laptop_Adapter(listLap,listHang, getContext());
//                recyclerView.setAdapter(ql_laptop_adapter);
//            }
//        }
//    }
//
//    public void addDemoLaptop() {
//
//        bitmap = getBitmap();
//
//        if (bitmap != null){
//            Bitmap bm1 = bitmap[0];
//            if (bitmap != null) {
//                Laptop lp1 = new Laptop("LP0", "LAsus", "Laptop Asus TUF Gaming FX506LHB i5 10300H"
//                        , "RAM 8GB", 19990000, changeType.checkByteInput(changeType.bitmapToByte(bm1)));
//                laptopDAO.insertLaptop(lp1);
//            }
//
//            Bitmap bm2 = bitmap[1];
//            if (bitmap != null) {
//                Laptop lp2 = new Laptop("LP1", "LAsus", "Laptop Asus ROG Strix Gaming G513IH R7 4800H"
//                        , "RAM 8GB", 21190000, changeType.checkByteInput(changeType.bitmapToByte(bm2)));
//                laptopDAO.insertLaptop(lp2);
//            }
//
//            Bitmap bm3 = bitmap[2];
//            if (bitmap != null) {
//                Laptop lp3 = new Laptop("LP2", "LMac", "Laptop Apple MacBook Pro 16 M1 Max 2021 10 core-CPU"
//                        , "RAM 16GB", 85990000, changeType.checkByteInput(changeType.bitmapToByte(bm3)));
//                laptopDAO.insertLaptop(lp3);
//            }
//
//            Bitmap bm4 = bitmap[3];
//            if (bitmap != null) {
//                Laptop lp4 = new Laptop("LP3", "LMac", "Laptop Apple MacBook Pro M2 2022 8GB"
//                        , "RAM 8GB", 35990000, changeType.checkByteInput(changeType.bitmapToByte(bm4)));
//                laptopDAO.insertLaptop(lp4);
//            }
//
//            Bitmap bm5 = bitmap[4];
//            if (bitmap != null) {
//                Laptop lp5 = new Laptop("LP4", "LHP", "Laptop HP 240 G8 i3 1005G1"
//                        , "RAM 4GB", 13790000, changeType.checkByteInput(changeType.bitmapToByte(bm5)));
//                laptopDAO.insertLaptop(lp5);
//            }
//
//            Bitmap bm6 = bitmap[5];
//            if (bitmap != null) {
//                Laptop lp6 = new Laptop("LP5", "LHP", "Laptop HP Envy X360 13 bf0090TU i7 1250U"
//                        , "RAM 16GB", 32590000, changeType.checkByteInput(changeType.bitmapToByte(bm6)));
//                laptopDAO.insertLaptop(lp6);
//            }
//
//            Bitmap bm7 = bitmap[6];
//            if (bitmap != null) {
//                Laptop lp7 = new Laptop("LP6", "LHP", "Laptop HP Pavilion X360 14 ek0055TU i7 1255U"
//                        , "RAM 16GB", 25590000, changeType.checkByteInput(changeType.bitmapToByte(bm7)));
//                laptopDAO.insertLaptop(lp7);
//            }
//
//            Bitmap bm8 = bitmap[7];
//            if (bitmap != null) {
//                Laptop lp8 = new Laptop("LP7", "LAcer", "Laptop Acer TravelMate B3 TMB311 31 C2HB N4020"
//                        , "RAM 4GB", 5990000, changeType.checkByteInput(changeType.bitmapToByte(bm8)));
//                laptopDAO.insertLaptop(lp8);
//            }
//
//            Bitmap bm9 = bitmap[8];
//            if (bitmap != null) {
//                Laptop lp9 = new Laptop("LP8", "LAcer", "Laptop Acer Nitro 5 Tiger AN515 58 773Y i7 12700H"
//                        , "RAM 8GB", 31990000, changeType.checkByteInput(changeType.bitmapToByte(bm9)));
//                laptopDAO.insertLaptop(lp9);
//            }
//
//            Bitmap bm10 = bitmap[9];
//            if (bitmap != null) {
//                Laptop lp10 = new Laptop("LP9", "LDell", "Laptop Dell Gaming G15 5515 R5 5600H"
//                        , "RAM 16GB", 23490000, changeType.checkByteInput(changeType.bitmapToByte(bm10)));
//                laptopDAO.insertLaptop(lp10);
//            }
//        }
//    }
//
//    public Bitmap[] getBitmap() {
//        return bitmap;
//    }
//
//    public void setBitmap(Bitmap[] bitmap) {
//        this.bitmap = bitmap;
//    }

}