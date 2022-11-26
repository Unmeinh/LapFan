package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.KH_Loader.KH_Voucher_Loader;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_Voucher_Activity extends AppCompatActivity {

    String TAG = "KH_Voucher_Activity_____";
    Context context = this;
    String openFrom;
    RecyclerView recyclerView;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_voucher);
        recyclerView = findViewById(R.id.recyclerView_KH_Voucher);

        getData();
        KH_Voucher_Loader kh_voucher_loader = new KH_Voucher_Loader(context, recyclerView, openFrom, pos);
        kh_voucher_loader.execute("");

        useToolbar();
    }

    private void getData(){
        Intent intent = getIntent();
        try {
            openFrom = intent.getStringExtra("openFrom");
            if (openFrom != null){
                if (openFrom.equals("ThanhToan")){
                    pos = intent.getIntExtra("posLap", -1);
                } else {
                    pos = -1;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Danh sách Voucher");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}