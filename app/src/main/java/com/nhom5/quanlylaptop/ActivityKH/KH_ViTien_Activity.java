package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.GiaoDichDAO;
import com.nhom5.quanlylaptop.DAO.ViTienDAO;
import com.nhom5.quanlylaptop.Entity.GiaoDich;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.ViTien;
import com.nhom5.quanlylaptop.KH_Adapter.KH_GiaoDich_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class KH_ViTien_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ViTien> listVi = new ArrayList<>();
    ArrayList<GiaoDich> listGD = new ArrayList<>();
    ViTienDAO viTienDAO;
    GiaoDichDAO giaoDichDAO;
    KH_GiaoDich_Adapter kh_giaoDich_adapter;
    Context context = this;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_vi_tien);
        recyclerView = findViewById(R.id.recyclerView_GiaoDich);
        viTienDAO = new ViTienDAO(context);
        giaoDichDAO = new GiaoDichDAO(context);

        listVi = viTienDAO.selectViTien(null, null, null, null);
        listGD = giaoDichDAO.selectGiaoDich(null, null, null, null);
        useToolbar();

        if (listGD != null){
            if (listGD.size() > 0){
                setUpRecyclerView(context);
            } else {
                addDemoGD();
                setUpRecyclerView(context);
            }
        }

        clickNapTien();
    }

    private void clickNapTien(){
        ImageButton napTien = findViewById(R.id.button_NapTien);
        View view = getLayoutInflater().inflate(R.layout.dialog_naptien, null);
        AppCompatButton button = view.findViewById(R.id.button_Dialog);

        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        napTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    private void addDemoGD(){
        GiaoDich gd0 = new GiaoDich("GD0", "No Data", "Nạp tiền", "Nạp tiền vào ví FPT Pay",
                changeType.intMoneyToString(100000), "2022-11-20");
        giaoDichDAO.insertGiaoDich(gd0);

        GiaoDich gd1 = new GiaoDich("GD1", "No Data", "Nạp tiền", "Nạp tiền vào ví FPT Pay",
                changeType.intMoneyToString(1000000000), "2022-11-20");
        giaoDichDAO.insertGiaoDich(gd1);

        GiaoDich gd2 = new GiaoDich("GD2", "No Data", "Thanh toán đơn hàng", "Thanh toán đơn hàng Laptop...",
                changeType.intMoneyToString(47900000), "2022-11-22");
        giaoDichDAO.insertGiaoDich(gd2);

        GiaoDich gd3 = new GiaoDich("GD3", "No Data", "Thanh toán đơn hàng", "Thanh toán đơn hàng Laptop...",
                changeType.intMoneyToString(35500000), "2022-11-25");
        giaoDichDAO.insertGiaoDich(gd3);

        GiaoDich gd4 = new GiaoDich("GD4", "No Data", "Thanh toán đơn hàng", "Thanh toán đơn hàng Laptop Macbook",
                changeType.intMoneyToString(25900000), "2022-11-27");
        giaoDichDAO.insertGiaoDich(gd4);

        GiaoDich gd5 = new GiaoDich("GD5", "No Data", "Nạp tiền", "Nạp tiền vào ví FPT Pay",
                changeType.intMoneyToString(100000000), "2022-11-29");
        giaoDichDAO.insertGiaoDich(gd5);
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_ViTien));
        TextView soTien = findViewById(R.id.textView_SoTien);
        if (listVi != null){
            if (listVi.size() > 0){
                ViTien viTien = listVi.get(0);
                soTien.setText(viTien.getSoTien());
            }
        }
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUpRecyclerView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        kh_giaoDich_adapter = new KH_GiaoDich_Adapter(listGD, context);
        recyclerView.setAdapter(kh_giaoDich_adapter);
    }
}