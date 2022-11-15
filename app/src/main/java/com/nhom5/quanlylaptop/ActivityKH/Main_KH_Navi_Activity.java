package com.nhom5.quanlylaptop.ActivityKH;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nhom5.quanlylaptop.PagerAdapter.KH_PagerAdapter_Bottom;
import com.nhom5.quanlylaptop.PagerAdapter.KH_PagerAdapter_Drawer;
import com.nhom5.quanlylaptop.R;

public class Main_KH_Navi_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    NavigationView naviView;
    String TAG = "Main_KH_Navi_Activity_____";
    DrawerLayout drawerLayout;
    int itemNaviDr;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kh_navi);
        bottomNavigationView = findViewById(R.id.bottomNavi);
        viewPager = findViewById(R.id.viewP);
        naviView = findViewById(R.id.naviView);
        drawerLayout = findViewById(R.id.drawerLayout);
        useToolbar("");
        setViewNaviBottom();
        setViewNaviDrawer();
    }

    private void useToolbar(String title) {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Account));
        ImageButton open = findViewById(R.id.imageButton_Open_Drawer);
        ImageView imageView = findViewById(R.id.imageView_Avatar);
        TextView titleView = findViewById(R.id.textView_Title_Toolbar_Acc);
        titleView.setText(title);
        imageView.setImageResource(R.drawable.hugh_jackman);
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
                if (id == R.id.item_navi_drawer_kh_trangChu) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 0 - home");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    itemNaviDr = 0;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_kh_home).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_kh_home).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_kh_thongBao) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 1 - thông báo");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    itemNaviDr = 1;
                    useToolbar("Thông Báo");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_kh_noti).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_kh_noti).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_kh_lTDell) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 2 - laptop dell");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    itemNaviDr = 2;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_kh_lTHP) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 3 - laptop hp");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                    itemNaviDr = 4;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_kh_lTAsus) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 4 - laptop asus");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(4);
                    itemNaviDr = 5;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_kh_lTAcer) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 5 - laptop acer");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(5);
                    itemNaviDr = 6;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_kh_lTMsi) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 6 - laptop msi");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(6);
                    itemNaviDr = 7;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_kh_mac) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 6 - laptop samsung");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(7);
                    itemNaviDr = 8;
                    useToolbar("");
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_kh_gioHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 7 - giỏ hàng");
                    KH_PagerAdapter_Drawer adapter = new KH_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(8);
                    itemNaviDr = 9;
                    useToolbar("Giỏ Hàng");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_kh_gioHang).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_kh_gioHang).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_kh_lienHe) {
                    itemNaviDr = 10;
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 8 - liên hệ");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
        getSupportActionBar().show();
    }

    private void setViewNaviBottom(){
        KH_PagerAdapter_Bottom adapter = new KH_PagerAdapter_Bottom(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.item_navi_bottom_kh_home){
                    KH_PagerAdapter_Bottom adapter = new KH_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    naviView.getMenu().getItem(0).setChecked(true);
                    naviView.getMenu().getItem(0).setCheckable(true);
                    itemNaviDr = 0;
                    useToolbar("");
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_kh_noti){
                    KH_PagerAdapter_Bottom adapter = new KH_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    naviView.getMenu().getItem(1).setChecked(true);
                    naviView.getMenu().getItem(1).setCheckable(true);
                    itemNaviDr = 1;
                    useToolbar("Thông Báo");
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_kh_gioHang){
                    KH_PagerAdapter_Bottom adapter = new KH_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    naviView.getMenu().getItem(9).setChecked(true);
                    naviView.getMenu().getItem(9).setCheckable(true);
                    itemNaviDr = 9;
                    useToolbar("Giỏ Hàng");
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_kh_acc){
                    KH_PagerAdapter_Bottom adapter = new KH_PagerAdapter_Bottom(getSupportFragmentManager());
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