package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.ActivityNV_Admin.DonHang_Manager_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_DonHang_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_DonHang_Fragment extends Fragment {

    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    KhachHangDAO khachHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<DonHang> listDon = new ArrayList<>();
    ArrayList<KhachHang> listKH = new ArrayList<>();
    QL_DonHang_Adapter ql_donHang_adapter;
    RecyclerView recyclerView;
    String TAG = "KH_DonHang_Activity_____";
    AppCompatButton addDHButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_don_hang, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_NVA_DonHang);
        addDHButton = view.findViewById(R.id.button_AddDH);

        laptopDAO = new LaptopDAO(getContext());
        donHangDAO = new DonHangDAO(getContext());
        khachHangDAO = new KhachHangDAO(getContext());
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        listDon = donHangDAO.selectDonHang(null, null, null, null);
        listKH = khachHangDAO.selectKhachHang(null, null, null, null);
        if (listDon != null){
            if (listDon.size() > 0){
                setUpReView();
            } else {
                addDemoDH();
            }
        } else {
            addDemoDH();
        }
        addDonHang();
        return view;
    }

    private void addDonHang(){
        addDHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonHang_Manager_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void addDemoDH(){
        DonHang dh1 = new DonHang("DH1", "NV1", "KH1", "LP2", "VOU1", "Rate1",
                "Nam Từ Liêm, Hà Nội", "19/11/2022", "Ví FPT Pay", "false", "63.570.000đ", 3);
        donHangDAO.insertDonHang(dh1);
        DonHang dh2 = new DonHang("DH2", "NV2", "KH0", "LP5", "VOU4", "Rate9",
                "Quận Thủ Đức, TP Hồ CHí Minh", "20/11/2022", "Thanh toán khi nhận hàng", "true", "65.180.000đ", 2);
        donHangDAO.insertDonHang(dh2);
        DonHang dh3 = new DonHang("DH3", "NV2", "KH0", "LP7", "VOU6", "Rate6",
                "Ngũ Hành Sơn, Đà Nẵng", "21/11/2022", "Ví FPT Pay", "true", "25.590.000đ", 1);
        donHangDAO.insertDonHang(dh3);
    }

    private void setUpReView() {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ql_donHang_adapter = new QL_DonHang_Adapter(listLap, listDon, listKH, getContext());
        recyclerView.setAdapter(ql_donHang_adapter);
    }
}