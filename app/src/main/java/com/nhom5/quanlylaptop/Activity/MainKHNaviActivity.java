package com.nhom5.quanlylaptop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nhom5.quanlylaptop.PagerAdapter.PagerAdapter;
import com.nhom5.quanlylaptop.R;

public class MainKHNaviActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khnavi);
        bottomNavigationView = findViewById(R.id.bottomNavi);
        viewPager = findViewById(R.id.viewP);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.item_navi_bottom_home){
                    Toast.makeText(MainKHNaviActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                }
                if (i == R.id.item_navi_bottom_noti){
                    Toast.makeText(MainKHNaviActivity.this, "Thông Báo", Toast.LENGTH_SHORT).show();
                    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                }
                if (i == R.id.item_navi_bottom_gioHang){
                    Toast.makeText(MainKHNaviActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                }
                if (i == R.id.item_navi_bottom_acc){
                    Toast.makeText(MainKHNaviActivity.this, "Account", Toast.LENGTH_SHORT).show();
                    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}