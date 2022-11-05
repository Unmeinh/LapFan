package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom5.quanlylaptop.R;

public class PickRoleActivity extends AppCompatActivity {

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
                startActivity(new Intent(PickRoleActivity.this, SignInActivity.class));
            }
        });

        rollNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickRoleActivity.this, SignInActivity.class));
            }
        });

        rollKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickRoleActivity.this, MainKHNaviActivity.class));
            }
        });
    }
}
