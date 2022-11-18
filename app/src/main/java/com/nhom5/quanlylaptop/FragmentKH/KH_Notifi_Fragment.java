package com.nhom5.quanlylaptop.FragmentKH;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.KH_Adapter.KH_GioHang_Adapter;
import com.nhom5.quanlylaptop.KH_Adapter.KH_ThongBao_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_Notifi_Fragment extends Fragment {

    ArrayList<ThongBao> listTB = new ArrayList<>();
    KH_ThongBao_Adapter kh_thongBao_adapter;
    ThongBaoDAO thongBaoDAO;
    RecyclerView recyclerView;
    String TAG = "KH_GioHang_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_notifi, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_Notifi);

        thongBaoDAO = new ThongBaoDAO(getContext());
        listTB = thongBaoDAO.selectThongBao(null, null, null, null);
        addDemoTB();
        return view;
    }

    private void addDemoTB(){
        if (listTB != null){
            if (listTB.size() == 0){
                addDemoDataTB();
                setUpReView();
            } else {
                setUpReView();
            }
        }
    }

    private void addDemoDataTB(){
        ThongBao tb1 = new ThongBao("TB1", "null", "Chào mừng khách hàng",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-11-18");
        thongBaoDAO.insertThongBao(tb1);

        ThongBao tb2 = new ThongBao("TB2", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Apple MacBook Air M1 2020 16GB với giá 33.490.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb2);

        ThongBao tb3 = new ThongBao("TB3", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Apple MacBook Pro 16 M1 Pro 2021 10 core-CPU với giá 66.990.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb3);

        ThongBao tb4 = new ThongBao("TB4", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MacBook Pro 14 M1 Max 2021 10-core CPU với giá 71.900.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb4);

        ThongBao tb5 = new ThongBao("TB5", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Apple MacBook Pro 16 M1 Max 2021 10 core-CPU với giá 85.990.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb5);

        ThongBao tb6 = new ThongBao("TB6", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Asus Gaming ROG Flow Z13 GZ301Z i7 12700H với giá 48.690.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb6);

        ThongBao tb7 = new ThongBao("TB7", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Asus TUF Gaming FX507Z i7 12700H với giá 38.690.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb7);

        ThongBao tb8 = new ThongBao("TB8", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Asus Vivobook Pro 15 OLED K6502Z i7 12650H với giá 31.690.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb8);

        ThongBao tb9 = new ThongBao("TB9", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Gaming GS66 Stealth 11UG i7 11800H với giá 64.490.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb9);

        ThongBao tb10 = new ThongBao("TB10", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Gaming GE66 Raider 11UH i7 11800H với giá 77.490.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb10);

        ThongBao tb11 = new ThongBao("TB11", "null", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Creator Z16P B12UGST i7 12700H với giá 79.490.000₫","2022-11-18");
        thongBaoDAO.insertThongBao(tb11);
    }

    private void setUpReView(){
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        kh_thongBao_adapter = new KH_ThongBao_Adapter(listTB, getContext());
        recyclerView.setAdapter(kh_thongBao_adapter);
    }


}