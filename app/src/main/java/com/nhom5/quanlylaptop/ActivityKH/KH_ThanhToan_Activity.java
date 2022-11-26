package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.DiaChiDAO;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DiaChi;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.IdData;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_ThanhToan_Adapter;
import com.nhom5.quanlylaptop.KH_Loader.KH_ThanhToan_Loader;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

import java.util.ArrayList;

public class KH_ThanhToan_Activity extends AppCompatActivity {

    TextView changeAddress;
    Laptop laptop;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    String TAG = "KH_ThanhToan_Activity_____", input = "";
    Context context = this;
    String maDC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_thanh_toan);
        changeAddress = findViewById(R.id.textView_Change_Address);
        recyclerView = findViewById(R.id.recyclerView_DonHang);
        relativeLayout = findViewById(R.id.layoutView);
        linearLayout = findViewById(R.id.loadingView);

        getInput();
        doiDiaChi();
        useToolbar();
        getSetDiaChi();

        KH_ThanhToan_Loader kh_thanhToan_loader = new KH_ThanhToan_Loader(context, laptop, recyclerView, linearLayout, relativeLayout, KH_ThanhToan_Activity.this);
        kh_thanhToan_loader.execute("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSetDiaChi();
        KH_ThanhToan_Loader kh_thanhToan_loader = new KH_ThanhToan_Loader(context, laptop, recyclerView, linearLayout, relativeLayout, KH_ThanhToan_Activity.this);
        kh_thanhToan_loader.execute("");
    }

    private void doiDiaChi() {
        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KH_DiaChi_Activity.class);
                IdData.getInstance().setIdDC(maDC);
                startActivity(intent);
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
                if (input.equals("MuaNgay")) {
                    laptop = (Laptop) intent.getExtras().getBinder("laptop");
                } else {
                    laptop = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getSetDiaChi() {
        maDC = IdData.getInstance().getIdDC();
        Log.d(TAG, "getSetDiaChi: maDC: " + maDC);

        if (maDC != null) {
            DiaChiDAO diaChiDAO = new DiaChiDAO(context);
            ArrayList<DiaChi> list = diaChiDAO.selectDiaChi(null, "maDC=?", new String[]{maDC}, null);
            if (list.size() > 0) {
                DiaChi diaChi = list.get(0);
                TextView textView = findViewById(R.id.textView_DiaChi);
                textView.setText(diaChi.getTenNguoiNhan() + " - " + diaChi.getSDT() + "\n " + diaChi.getThanhPho()
                        + " - " + diaChi.getQuanHuyen() + " - " + diaChi.getXaPhuong());
            }
        }

    }

}