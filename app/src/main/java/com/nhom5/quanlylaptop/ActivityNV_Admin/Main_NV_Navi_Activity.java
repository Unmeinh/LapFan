package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nhom5.quanlylaptop.PagerAdapter.NV_PagerAdapter_Bottom;
import com.nhom5.quanlylaptop.PagerAdapter.NV_PagerAdapter_Drawer;
import com.nhom5.quanlylaptop.R;

public class Main_NV_Navi_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    NavigationView naviView;
    String TAG = "Main_NV_Navi_Activity_____";
    DrawerLayout drawerLayout;
    int itemNaviDr;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nv_navi);
        bottomNavigationView = findViewById(R.id.bottomNavi);
        viewPager = findViewById(R.id.viewP);
        naviView = findViewById(R.id.naviView);
        drawerLayout = findViewById(R.id.drawerLayout);
        useToolbar();
        setViewNaviBottom();
        setViewNaviDrawer();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Account));
        ImageButton open = findViewById(R.id.imageButton_Open_Drawer);
        ImageView imageView = findViewById(R.id.imageView_Avatar);
        imageView.setImageResource(R.drawable.ryan_reynolds);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void setViewNaviDrawer() {
        naviView.getMenu().getItem(0).setChecked(true);
        naviView.getMenu().getItem(0).setCheckable(true);
        naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_navi_drawer_nv_trangChu) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 0 - home");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    itemNaviDr = 0;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_home).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_home).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_nv_fptShop) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 1 - phiếu mượn");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    itemNaviDr = 1;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_fpt).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_fpt).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_nv_Laptop) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 2 - thành viên");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    itemNaviDr = 2;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_DonHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 3 - sách");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                    itemNaviDr = 3;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_KhachHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 4 - loại sách");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(4);
                    itemNaviDr = 4;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_Voucher) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 5 - doanh thu");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(5);
                    itemNaviDr = 5;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_ThongKe) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 7 - thêm mem");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(6);
                    itemNaviDr = 6;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_thongKe).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_thongKe).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_nv_lienHe) {
                    itemNaviDr = 7;
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 8 - đổi pass");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
        getSupportActionBar().show();
    }

    private void setViewNaviBottom() {
        NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.item_navi_bottom_nv_home) {
                    Toast.makeText(Main_NV_Navi_Activity.this, "Home", Toast.LENGTH_SHORT).show();
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    naviView.getMenu().getItem(0).setChecked(true);
                    naviView.getMenu().getItem(0).setCheckable(true);
                    itemNaviDr = 0;
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_nv_fpt) {
                    Toast.makeText(Main_NV_Navi_Activity.this, "Thông Báo", Toast.LENGTH_SHORT).show();
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    naviView.getMenu().getItem(1).setChecked(true);
                    naviView.getMenu().getItem(1).setCheckable(true);
                    itemNaviDr = 1;
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_nv_thongKe) {
                    Toast.makeText(Main_NV_Navi_Activity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    naviView.getMenu().getItem(6).setChecked(true);
                    naviView.getMenu().getItem(6).setCheckable(true);
                    itemNaviDr = 6;
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_nv_acc) {
                    Toast.makeText(Main_NV_Navi_Activity.this, "Account", Toast.LENGTH_SHORT).show();
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                    Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
                    naviView.getMenu().getItem(itemNaviDr).setChecked(false);
                    naviView.getMenu().getItem(itemNaviDr).setCheckable(false);
                    getSupportActionBar().hide();
                }
                return true;
            }
        });
    }


}