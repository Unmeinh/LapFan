package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.GiaoDichDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
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
    LinearLayout linearLayout;
    ViTien viTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_vi_tien);
        linearLayout = findViewById(R.id.linearGiaoDichEmpty);
        viTienDAO = new ViTienDAO(context);
        giaoDichDAO = new GiaoDichDAO(context);
        listGD = giaoDichDAO.selectGiaoDich(null, null, null, null);

        getUser();
        if (viTien != null) {
            KH_GiaoDich_Loader kh_giaoDich_loader = new KH_GiaoDich_Loader(context, findViewById(R.id.recyclerView_GiaoDich), linearLayout);
            kh_giaoDich_loader.execute(viTien.getMaVi());
        }

        useToolbar();
        clickNapTien();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viTien != null) {
            KH_GiaoDich_Loader kh_giaoDich_loader = new KH_GiaoDich_Loader(context, findViewById(R.id.recyclerView_GiaoDich), linearLayout);
            kh_giaoDich_loader.execute(viTien.getMaVi());
        }
    }

    private void getUser() {
        SharedPreferences pref = getSharedPreferences("Who_Login", MODE_PRIVATE);
        if (pref == null) {
            viTien = null;
        } else {
            String user = pref.getString("who", "");
            listVi = viTienDAO.selectViTien(null, "maKH=?", new String[]{user}, null);
            if (listVi.size() > 0) {
                viTien = listVi.get(0);
            }
        }
    }

    private void clickNapTien() {
        LinearLayout napTien = findViewById(R.id.onclick_NapTien);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inft = ((Activity) context).getLayoutInflater();
        View view = inft.inflate(R.layout.dialog_naptien, null);
        AppCompatButton button = view.findViewById(R.id.button_Dialog);

        builder.setView(view);
        Dialog dialog = builder.create();
        napTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_ViTien));
        TextView soTien = findViewById(R.id.textView_SoTien);
        if (viTien != null) {
            soTien.setText("Số dư: " + viTien.getSoTien());
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