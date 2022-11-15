package com.nhom5.quanlylaptop.Support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.BaseAdapter.QL_Laptop_Adapter;
import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.FragmentQuanLy.Tab_Laptop_Fragment;
import com.nhom5.quanlylaptop.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UrlToBitmap extends AsyncTask<String, Void, Bitmap[]> {
    Bitmap[] listBitmap = {null, null, null, null, null, null, null, null, null, null};
    Bitmap bitmap;
    Tab_Laptop_Fragment tab_laptop_fragment;
    ArrayList<Laptop> listLap;
    ArrayList<HangLaptop> listHang;
    ChangeType changeType = new ChangeType();
    QL_Laptop_Adapter ql_laptop_adapter;
    LaptopDAO laptopDAO;
    HangLaptopDAO hangLaptopDAO;
    Context context;
    RecyclerView recyclerView;
    String TAG = "";

    public UrlToBitmap(Tab_Laptop_Fragment tab_laptop_fragment, Context context) {
        this.tab_laptop_fragment = tab_laptop_fragment;
        this.context = context;
        laptopDAO = new LaptopDAO(context);
        hangLaptopDAO = new HangLaptopDAO(context);
    }

    @Override
    protected Bitmap[] doInBackground(String... url) {
        for (int i = 0; i < url.length; i++){
            String stringUrl = url[i];
            bitmap = null;
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                if (bitmap != null){
                    listBitmap[i] = bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addDemoLaptop(listBitmap);
        return listBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap[] listBitmap) {
        super.onPostExecute(listBitmap);
        recyclerView = tab_laptop_fragment.getView().findViewById(R.id.recyclerView_Laptop);
//        tab_laptop_fragment.setBitmap(listBitmap);
        listHang = hangLaptopDAO.selectHangLaptop(null, null, null, null);
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        setUpGridView(listBitmap);
    }


    public void setUpGridView(Bitmap[] bitmaps) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        if (listLap == null) {
            Log.d(TAG, "onCreateView: list null");
            addDemoLaptop(bitmaps);
            ql_laptop_adapter = new QL_Laptop_Adapter(listLap,listHang, context);
            recyclerView.setAdapter(ql_laptop_adapter);
        } else {
            if (listLap.size() == 0) {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listLap.size());
                addDemoLaptop(bitmaps);
                ql_laptop_adapter = new QL_Laptop_Adapter(listLap,listHang, context);
                recyclerView.setAdapter(ql_laptop_adapter);
            } else {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listLap.size());
                ql_laptop_adapter = new QL_Laptop_Adapter(listLap,listHang, context);
                recyclerView.setAdapter(ql_laptop_adapter);
            }
        }
    }

    public void addDemoLaptop(Bitmap[] bitmaps) {

        if (bitmaps != null){
            Bitmap bm1 = bitmaps[0];
            if (bm1 != null) {
                Laptop lp1 = new Laptop("LP0", "LAsus", "Laptop Asus TUF Gaming FX506LHB i5 10300H"
                        , "RAM 8GB", 19990000, changeType.checkByteInput(changeType.bitmapToByte(bm1)));
                laptopDAO.insertLaptop(lp1);
            }

            Bitmap bm2 = bitmaps[1];
            if (bm2 != null) {
                Laptop lp2 = new Laptop("LP1", "LAsus", "Laptop Asus ROG Strix Gaming G513IH R7 4800H"
                        , "RAM 8GB", 21190000, changeType.checkByteInput(changeType.bitmapToByte(bm2)));
                laptopDAO.insertLaptop(lp2);
            }

            Bitmap bm3 = bitmaps[2];
            if (bm3 != null) {
                Laptop lp3 = new Laptop("LP2", "LMac", "Laptop Apple MacBook Pro 16 M1 Max 2021 10 core-CPU"
                        , "RAM 16GB", 85990000, changeType.checkByteInput(changeType.bitmapToByte(bm3)));
                laptopDAO.insertLaptop(lp3);
            }

            Bitmap bm4 = bitmaps[3];
            if (bm4 != null) {
                Laptop lp4 = new Laptop("LP3", "LMac", "Laptop Apple MacBook Pro M2 2022 8GB"
                        , "RAM 8GB", 35990000, changeType.checkByteInput(changeType.bitmapToByte(bm4)));
                laptopDAO.insertLaptop(lp4);
            }

            Bitmap bm5 = bitmaps[4];
            if (bm5 != null) {
                Laptop lp5 = new Laptop("LP4", "LHP", "Laptop HP 240 G8 i3 1005G1"
                        , "RAM 4GB", 13790000, changeType.checkByteInput(changeType.bitmapToByte(bm5)));
                laptopDAO.insertLaptop(lp5);
            }

            Bitmap bm6 = bitmaps[5];
            if (bm6 != null) {
                Laptop lp6 = new Laptop("LP5", "LHP", "Laptop HP Envy X360 13 bf0090TU i7 1250U"
                        , "RAM 16GB", 32590000, changeType.checkByteInput(changeType.bitmapToByte(bm6)));
                laptopDAO.insertLaptop(lp6);
            }

            Bitmap bm7 = bitmaps[6];
            if (bm7 != null) {
                Laptop lp7 = new Laptop("LP6", "LHP", "Laptop HP Pavilion X360 14 ek0055TU i7 1255U"
                        , "RAM 16GB", 25590000, changeType.checkByteInput(changeType.bitmapToByte(bm7)));
                laptopDAO.insertLaptop(lp7);
            }

            Bitmap bm8 = bitmaps[7];
            if (bm8 != null) {
                Laptop lp8 = new Laptop("LP7", "LAcer", "Laptop Acer TravelMate B3 TMB311 31 C2HB N4020"
                        , "RAM 4GB", 5990000, changeType.checkByteInput(changeType.bitmapToByte(bm8)));
                laptopDAO.insertLaptop(lp8);
            }

            Bitmap bm9 = bitmaps[8];
            if (bm9 != null) {
                Laptop lp9 = new Laptop("LP8", "LAcer", "Laptop Acer Nitro 5 Tiger AN515 58 773Y i7 12700H"
                        , "RAM 8GB", 31990000, changeType.checkByteInput(changeType.bitmapToByte(bm9)));
                laptopDAO.insertLaptop(lp9);
            }

            Bitmap bm10 = bitmaps[9];
            if (bm10 != null) {
                Laptop lp10 = new Laptop("LP9", "LDell", "Laptop Dell Gaming G15 5515 R5 5600H"
                        , "RAM 16GB", 23490000, changeType.checkByteInput(changeType.bitmapToByte(bm10)));
                laptopDAO.insertLaptop(lp10);
            }
        }
    }
}
