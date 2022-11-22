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
import com.nhom5.quanlylaptop.KH_Loader.KH_GiaoDich_Loader;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class KH_ViTien_Activity extends AppCompatActivity {

    ArrayList<ViTien> listVi = new ArrayList<>();
    ArrayList<ViTien> listGD = new ArrayList<>();
    ViTienDAO viTienDAO;
    GiaoDichDAO giaoDichDAO;
    Context context = this;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_vi_tien);
        viTienDAO = new ViTienDAO(context);
        giaoDichDAO = new GiaoDichDAO(context);
        listVi = viTienDAO.selectViTien(null, null, null, null);
        listGD = giaoDichDAO.selectGiaoDich(null, null, null, null);

        KH_GiaoDich_Loader kh_giaoDich_loader = new KH_GiaoDich_Loader(KH_ViTien_Activity.this, context);
        kh_giaoDich_loader.execute("");
        useToolbar();
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

}