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
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_GioHang_Adapter;
import com.nhom5.quanlylaptop.KH_Adapter.KH_ThanhToan_Adapter;
import com.nhom5.quanlylaptop.KH_Loader.KH_ThanhToan_Loader;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_ThanhToan_Activity extends AppCompatActivity {

    TextView changeAddress;
    LaptopDAO laptopDAO;
    GioHangDAO gioHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<GioHang> listGio = new ArrayList<>();
    KH_ThanhToan_Adapter kh_thanhToan_adapter;
    RecyclerView recyclerView;
    String TAG = "KH_GioHang_Fragment_____", input = "";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thanh_toan);
        changeAddress = findViewById(R.id.textView_Change_Address);
        recyclerView = findViewById(R.id.recyclerView_DonHang);

        getInput();
        doiDiaChi();
        useToolbar();

        KH_ThanhToan_Loader kh_thanhToan_loader = new KH_ThanhToan_Loader(KH_ThanhToan_Activity.this, context,
                listLap, input);
        kh_thanhToan_loader.execute("");
    }

    private void doiDiaChi() {
        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, KH_DiaChi_Activity.class));
            }
        });
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_DonHang));
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getInput() {
        Intent intent = getIntent();
        try {
            input = intent.getStringExtra("input");
            if (input != null) {
                if (input.equals("muangay")){
                    ArrayList<Laptop> list = new ArrayList<>();
                    list.add((Laptop) intent.getExtras().getBinder("laptop"));
                    listLap = list;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}