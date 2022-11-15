package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nhom5.quanlylaptop.BaseAdapter.QL_HangLaptop_Adapter;
import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class Tab_HangLaptop_Fragment extends Fragment {

    ArrayList<HangLaptop> listHang = new ArrayList<>();
    GridView gridView;
    ChangeType changeType;
    QL_HangLaptop_Adapter QLHangLaptopAdapter;
    HangLaptopDAO hangLaptopDAO;
    String TAG = "Tab_HangLaptop_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_hang_laptop, container, false);
        gridView = view.findViewById(R.id.gridView_hangLaptop);
        hangLaptopDAO = new HangLaptopDAO(getContext());
        changeType = new ChangeType();

        listHang = hangLaptopDAO.selectHangLaptop(null, null, null, null);
        if (listHang == null){
            Log.d(TAG, "onCreateView: list null");
            addHangLaptop();
            QLHangLaptopAdapter = new QL_HangLaptop_Adapter(listHang);
            gridView.setAdapter(QLHangLaptopAdapter);
        } else {
            if (listHang.size() == 0){
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                addHangLaptop();
                QLHangLaptopAdapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(QLHangLaptopAdapter);
            } else {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                QLHangLaptopAdapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(QLHangLaptopAdapter);
            }
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listHang = hangLaptopDAO.selectHangLaptop(null, null, null, null);
        if (listHang == null){
            Log.d(TAG, "onCreateView: list null");
            addHangLaptop();
            QLHangLaptopAdapter = new QL_HangLaptop_Adapter(listHang);
            gridView.setAdapter(QLHangLaptopAdapter);
        } else {
            if (listHang.size() == 0){
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                addHangLaptop();
                QLHangLaptopAdapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(QLHangLaptopAdapter);
            } else {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                QLHangLaptopAdapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(QLHangLaptopAdapter);
            }
        }
    }

    private void addHangLaptop(){
        Bitmap bmDell = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_dell);
        HangLaptop dell = new HangLaptop("HLP01", "Laptop Dell",
                changeType.checkByteInput(changeType.bitmapToByte(bmDell)));
        hangLaptopDAO.insertHangLaptop(dell);

        Bitmap bmHp = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_hp);
        HangLaptop hp = new HangLaptop("HLP02", "Laptop HP",
                changeType.checkByteInput(changeType.bitmapToByte(bmHp)));
        hangLaptopDAO.insertHangLaptop(hp);

        Bitmap bmAsus = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_asus);
        HangLaptop asus = new HangLaptop("HLP03", "Laptop Asus",
                changeType.checkByteInput(changeType.bitmapToByte(bmAsus)));
        hangLaptopDAO.insertHangLaptop(asus);

        Bitmap bmRazer = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_razer);
        HangLaptop razer = new HangLaptop("HLP04", "Laptop Razer",
                changeType.checkByteInput(changeType.bitmapToByte(bmRazer)));
        hangLaptopDAO.insertHangLaptop(razer);

        Bitmap bmSS = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_samsung);
        HangLaptop ss = new HangLaptop("HLP05", "Laptop Samsung",
                changeType.checkByteInput(changeType.bitmapToByte(bmSS)));
        hangLaptopDAO.insertHangLaptop(ss);

        Bitmap bmMac = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_macbook);
        HangLaptop mac = new HangLaptop("HLP06", "MacBook",
                changeType.checkByteInput(changeType.bitmapToByte(bmMac)));
        hangLaptopDAO.insertHangLaptop(mac);
    }
}