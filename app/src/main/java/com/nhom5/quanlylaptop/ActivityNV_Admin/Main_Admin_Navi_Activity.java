package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.nhom5.quanlylaptop.Activity.PickRole_Activity;
import com.nhom5.quanlylaptop.PagerAdapter.Admin_PagerAdapter_Bottom;
import com.nhom5.quanlylaptop.PagerAdapter.Admin_PagerAdapter_Drawer;
import com.nhom5.quanlylaptop.R;

public class Main_Admin_Navi_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    NavigationView naviView;
    String TAG = "Main_Admin_Navi_Activity_____";
    DrawerLayout drawerLayout;
    int itemNaviDr, count;
    Context context = this;

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
        useToolbar("", 0);
    }

    private void useToolbar(String title, int type) {
        setSupportActionBar(findViewById(R.id.toolbar_Account));
        if (type == 0) {
            layoutAccount(title);
        } else {
            layoutSearch(title);
        }
        ImageButton open = findViewById(R.id.imageButton_Open_Drawer);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void layoutAccount(String title) {
        LinearLayout layoutAcc = findViewById(R.id.layout_Account);
        LinearLayout layoutSearch = findViewById(R.id.layout_Search);
        TextView titleView = findViewById(R.id.textView_Title_Toolbar_Acc);
        ImageView imageView = findViewById(R.id.imageView_Avatar);

        layoutAcc.setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);
        titleView.setText(title);
        imageView.setImageResource(R.drawable.admin_avatar);
    }

    private void layoutSearch(String title) {
        LinearLayout layoutAcc = findViewById(R.id.layout_Account);
        LinearLayout layoutSearch = findViewById(R.id.layout_Search);
        TextView titleView = findViewById(R.id.textView_Title_Toolbar_Search);
        EditText search = findViewById(R.id.editText_Search);
        ImageButton open = findViewById(R.id.imageButton_Search_Toolbar);
        count = 0;

        layoutAcc.setVisibility(View.GONE);
        layoutSearch.setVisibility(View.VISIBLE);
        titleView.setText(title);
        if (count % 2 == 0) {
            titleView.setVisibility(View.VISIBLE);
            search.setText("");
            search.setVisibility(View.GONE);
            count++;
        }
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: count: " + count);
                if (count % 2 == 0) {
                    titleView.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                    count++;
                } else {
                    titleView.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    count++;
                }
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
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(0);
                            itemNaviDr = 0;
                            useToolbar("", 0);
                            bottomNavigationView.setVisibility(View.VISIBLE);
                            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_home).setCheckable(true);
                            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_home).setChecked(true);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_noti) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 1 - fptshop");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(1);
                            itemNaviDr = 1;
                            useToolbar("FPT Shop", 0);
                            bottomNavigationView.setVisibility(View.VISIBLE);
                            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_noti).setCheckable(true);
                            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_noti).setChecked(true);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_Laptop) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 2 - laptop");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(2);
                            itemNaviDr = 3;
                            useToolbar("QLý Laptop", 1);
                            bottomNavigationView.setVisibility(View.GONE);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_DonHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 3 - đơn hàng");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(3);
                            itemNaviDr = 4;
                            useToolbar("QLý Đơn Hàng", 1);
                            bottomNavigationView.setVisibility(View.GONE);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_KhachHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 4 - khách hàng");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(4);
                            itemNaviDr = 5;
                            useToolbar("QLý Khách Hàng", 1);
                            bottomNavigationView.setVisibility(View.GONE);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_Voucher) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 5 - voucher");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(5);
                            itemNaviDr = 6;
                            useToolbar("QLý Voucher", 1);
                            bottomNavigationView.setVisibility(View.GONE);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_NhanVien) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 6 - nhân viên");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(6);
                            itemNaviDr = 7;
                            useToolbar("QLý Nhân Viên", 1);
                            bottomNavigationView.setVisibility(View.GONE);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_ThongKe) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 7 - thống kê");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Drawer adapter = new Admin_PagerAdapter_Drawer(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(7);
                            itemNaviDr = 8;
                            useToolbar("Doanh Thu\nThống Kê", 0);
                            bottomNavigationView.setVisibility(View.VISIBLE);
                            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_thongKe).setCheckable(true);
                            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_admin_thongKe).setChecked(true);
                        }
                    }, 300);
                }
                if (id == R.id.item_navi_drawer_admin_Logout) {
                    itemNaviDr = 9;
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 8 - log out");
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
    }

    private void setViewNaviBottom() {
        Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.item_navi_bottom_admin_home) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(0);
                            naviView.getMenu().getItem(0).setChecked(true);
                            naviView.getMenu().getItem(0).setCheckable(true);
                            itemNaviDr = 0;
                            useToolbar("", 0);
                        }
                    }, 300);
                }
                if (i == R.id.item_navi_bottom_admin_noti) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(1);
                            naviView.getMenu().getItem(1).setChecked(true);
                            naviView.getMenu().getItem(1).setCheckable(true);
                            itemNaviDr = 1;
                            useToolbar("FPT Shop", 0);
                        }
                    }, 300);
                }
                if (i == R.id.item_navi_bottom_admin_thongKe) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(2);
                            naviView.getMenu().getItem(8).setChecked(true);
                            naviView.getMenu().getItem(8).setCheckable(true);
                            itemNaviDr = 8;
                            useToolbar("Doanh Thu\nThống Kê", 0);
                        }
                    }, 300);
                }
                if (i == R.id.item_navi_bottom_add_staff) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Admin_PagerAdapter_Bottom adapter = new Admin_PagerAdapter_Bottom(getSupportFragmentManager());
                            viewPager.setAdapter(adapter);
                            viewPager.setCurrentItem(3);
                            Log.d(TAG, "onNavigationItemSelected: itemNavi: " + itemNaviDr);
                            naviView.getMenu().getItem(itemNaviDr).setChecked(false);
                            naviView.getMenu().getItem(itemNaviDr).setCheckable(false);
                            useToolbar("Thêm Nhân Viên", 0);
                        }
                    }, 300);
                }
                return true;
            }
        });
    }
}