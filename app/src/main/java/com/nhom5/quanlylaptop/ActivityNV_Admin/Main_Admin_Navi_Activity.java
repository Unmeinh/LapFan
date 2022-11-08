package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

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
import com.nhom5.quanlylaptop.PagerAdapter.Admin_PagerAdapter_Bottom;
import com.nhom5.quanlylaptop.PagerAdapter.Admin_PagerAdapter_Drawer;
import com.nhom5.quanlylaptop.R;

public class Main_Admin_Navi_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    NavigationView naviView;
    String TAG = "Main_Admin_Navi_Activity_____";
    DrawerLayout drawerLayout;
    int itemNaviDr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_navi);
        bottomNavigationView = findViewById(R.id.bottomNavi);
        viewPager = findViewById(R.id.viewP);
        naviView = findViewById(R.id.naviView);
        drawerLayout = findViewById(R.id.drawerLayout);
        setViewNaviBottom();
        setViewNaviDrawer();
        openNaviDrawer();
    }

    public void openNaviDrawer() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ImageButton open = findViewById(R.id.open_navi_drawer);
        ImageView imageView = findViewById(R.id.home_Avatar_Account);
        imageView.setImageResource(R.drawable.admin_avatar);
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
                if (id == R.id.item_navi_drawer_admin_trangChu) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 0 - home");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    itemNaviDr = 0;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_home).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_home).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_admin_thongBao) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 1 - phiếu mượn");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    itemNaviDr = 1;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_noti).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_noti).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_admin_Laptop) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 2 - thành viên");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    itemNaviDr = 2;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_admin_HoaDon) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 3 - sách");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                    itemNaviDr = 3;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_admin_KhachHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 4 - loại sách");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(4);
                    itemNaviDr = 4;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_admin_Voucher) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 5 - doanh thu");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(5);
                    itemNaviDr = 5;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_admin_NhanVien) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 6 - top sách");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(6);
                    itemNaviDr = 6;
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_admin_ThongKe) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 7 - thêm mem");
                    Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(7);
                    itemNaviDr = 7;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_thongKe).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_thongKe).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_admin_Logout) {
                    itemNaviDr = 8;
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 8 - đổi pass");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
    }

    private void setViewNaviBottom(){
        Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.item_navi_bottom_admin_home){
                    Toast.makeText(Main_Admin_Navi_Activity.this, "Home", Toast.LENGTH_SHORT).show();
                    Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    naviView.getMenu().getItem(0).setChecked(true);
                    naviView.getMenu().getItem(0).setCheckable(true);
                    itemNaviDr = 0;
                }
                if (i == R.id.item_navi_bottom_admin_noti){
                    Toast.makeText(Main_Admin_Navi_Activity.this, "Thông Báo", Toast.LENGTH_SHORT).show();
                    Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    naviView.getMenu().getItem(1).setChecked(true);
                    naviView.getMenu().getItem(1).setCheckable(true);
                    itemNaviDr = 1;
                }
                if (i == R.id.item_navi_bottom_admin_thongKe){
                    Toast.makeText(Main_Admin_Navi_Activity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                    Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    naviView.getMenu().getItem(8).setChecked(true);
                    naviView.getMenu().getItem(8).setCheckable(true);
                    itemNaviDr = 8;
                }
                if (i == R.id.item_navi_bottom_add_staff){
                    Toast.makeText(Main_Admin_Navi_Activity.this, "Account", Toast.LENGTH_SHORT).show();
                    Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                    Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
                    naviView.getMenu().getItem(itemNaviDr).setChecked(false);
                    naviView.getMenu().getItem(itemNaviDr).setCheckable(false);
                }
                return true;
            }
        });
    }
}