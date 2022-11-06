package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom5.quanlylaptop.R;

public class PickRole_Activity extends AppCompatActivity {

    AppCompatButton rollAdmin, rollNV, rollKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_role);
        rollAdmin = findViewById(R.id.role_Admin);
        rollNV = findViewById(R.id.role_NhanVien);
        rollKH = findViewById(R.id.role_KhachHang);

        rollAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickRole_Activity.this, SignIn_Activity.class));
            }
        });

        rollNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickRole_Activity.this, SignIn_Activity.class));
            }
        });

        rollKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickRole_Activity.this, Main_KH_Navi_Activity.class));
            }
        });
    }
}
