package com.nhom5.quanlylaptop.FragmentKH;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nhom5.quanlylaptop.ActivityKH.KH_ThanhToan_Activity;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_GioHang_Adapter;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Laptop_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_GioHang_Fragment extends Fragment {

    LinearLayout emptyGHLayout;
    RelativeLayout viewGHLayout;
    AppCompatButton buttonPayNow;
    TextView totalMoney;
    LaptopDAO laptopDAO;
    GioHangDAO gioHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<GioHang> listGio = new ArrayList<>();
    KH_GioHang_Adapter kh_gioHang_adapter;
    RecyclerView recyclerView;
    String TAG = "KH_GioHang_Fragment_____";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_gio_hang, container, false);
        emptyGHLayout = view.findViewById(R.id.empty_GioHang);
        viewGHLayout = view.findViewById(R.id.view_Gio_Hang);
        buttonPayNow = view.findViewById(R.id.button_PayNow);
        totalMoney = view.findViewById(R.id.textView_Total);
        recyclerView = view.findViewById(R.id.recyclerView_GioHang);

        laptopDAO = new LaptopDAO(getContext());
        gioHangDAO = new GioHangDAO(getContext());
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        listGio = gioHangDAO.selectGioHang(null, null, null, null);
//        listGio = gioHangDAO.selectGioHang(null, "maKH=?", new String[]{"LDell"}, null);

        if (listGio != null){
            if (listGio.size() == 0){
                Log.d(TAG, "onCreateView: Giỏ Hàng null");
                viewGHLayout.setVisibility(View.GONE);
                emptyGHLayout.setVisibility(View.VISIBLE);
            } else {
                Log.d(TAG, "onCreateView: Giỏ Hàng not null: " + listGio.size());
                emptyGHLayout.setVisibility(View.GONE);
                viewGHLayout.setVisibility(View.VISIBLE);
                setUpReView();
            }
        }

        buttonPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KH_ThanhToan_Activity.class);
                startActivity(intent);
            }
        });

        totalMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void setUpReView(){
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        kh_gioHang_adapter = new KH_GioHang_Adapter(listLap, listGio, getContext());
        recyclerView.setAdapter(kh_gioHang_adapter);
    }
}