package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_DonHang_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.NV_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class NV_DonHang_Activity extends AppCompatActivity {

    Context context;
    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<DonHang> listDon = new ArrayList<>();
    NV_DonHang_Adapter nv_donHang_adapter;
    RecyclerView recyclerView;
    String TAG = "NV_DonHang_Activity_____";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nv_don_hang);
        context = this;
        recyclerView = findViewById(R.id.recyclerView_NV_DonHang);

        laptopDAO = new LaptopDAO(context);
        donHangDAO = new DonHangDAO(context);
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        listDon = donHangDAO.selectDonHang(null, null, null, null);
        if (listDon != null){
            if (listDon.size() > 0){
                setUpReView();
            }
        }
        useToolbar();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Đơn Hàng đã bán");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUpReView() {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        nv_donHang_adapter = new NV_DonHang_Adapter(listLap, listDon, context);
        recyclerView.setAdapter(nv_donHang_adapter);
    }
}