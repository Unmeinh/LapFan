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

import com.nhom5.quanlylaptop.NAV_Adapter.QL_HangLaptop_Adapter;
import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class Tab_HangLaptop_Fragment extends Fragment {

    ArrayList<HangLaptop> listHang = new ArrayList<>();
    GridView gridView;
    ChangeType changeType;
    QL_HangLaptop_Adapter ql_hangLaptop_adapter;
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
            ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
            gridView.setAdapter(ql_hangLaptop_adapter);
        } else {
            if (listHang.size() == 0){
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                addHangLaptop();
                ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(ql_hangLaptop_adapter);
            } else {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(ql_hangLaptop_adapter);
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
            ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
            gridView.setAdapter(ql_hangLaptop_adapter);
        } else {
            if (listHang.size() < 6){
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                addHangLaptop();
                ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(ql_hangLaptop_adapter);
            } else {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
                gridView.setAdapter(ql_hangLaptop_adapter);
            }
        }
    }

    private void addHangLaptop(){
        Bitmap bmDell = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_dell);
        HangLaptop dell = new HangLaptop("LDell", "Laptop Dell",
                changeType.checkByteInput(changeType.bitmapToByte(bmDell)));
        hangLaptopDAO.insertHangLaptop(dell);

        Bitmap bmHp = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_hp);
        HangLaptop hp = new HangLaptop("LHP", "Laptop HP",
                changeType.checkByteInput(changeType.bitmapToByte(bmHp)));
        hangLaptopDAO.insertHangLaptop(hp);

        Bitmap bmAsus = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_asus);
        HangLaptop asus = new HangLaptop("LAsus", "Laptop Asus",
                changeType.checkByteInput(changeType.bitmapToByte(bmAsus)));
        hangLaptopDAO.insertHangLaptop(asus);

        Bitmap bmRazer = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_acer);
        HangLaptop razer = new HangLaptop("LAcer", "Laptop Acer",
                changeType.checkByteInput(changeType.bitmapToByte(bmRazer)));
        hangLaptopDAO.insertHangLaptop(razer);

        Bitmap bmSS = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_laptop_msi);
        HangLaptop ss = new HangLaptop("LMSi", "Laptop MSi",
                changeType.checkByteInput(changeType.bitmapToByte(bmSS)));
        hangLaptopDAO.insertHangLaptop(ss);

        Bitmap bmMac = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.img_macbook);
        HangLaptop mac = new HangLaptop("LMac", "MacBook",
                changeType.checkByteInput(changeType.bitmapToByte(bmMac)));
        hangLaptopDAO.insertHangLaptop(mac);
    }
}