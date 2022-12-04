package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.nhom5.quanlylaptop.ActivityKH.KH_DonHang_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_ViTien_Activity;
import com.nhom5.quanlylaptop.CreateWallet.TaoVi_Activity;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.PagerAdapter.KH_PagerAdapter_Drawer;
import com.nhom5.quanlylaptop.PagerAdapter.NV_PagerAdapter_Bottom;
import com.nhom5.quanlylaptop.PagerAdapter.NV_PagerAdapter_Drawer;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class Main_NV_Navi_Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    NavigationView naviView;
    String TAG = "Main_NV_Navi_Activity_____";
    DrawerLayout drawerLayout;
    int itemNaviDr, count;
    Context context = this;
    NhanVien nhanVien;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nv_navi);
        bottomNavigationView = findViewById(R.id.bottomNavi);
        viewPager = findViewById(R.id.viewP);
        naviView = findViewById(R.id.naviView);
        drawerLayout = findViewById(R.id.drawerLayout);

        getUser();
        useToolbar("", 0);
        setViewNaviBottom();
        setViewNaviDrawer();

        SharedPreferences pref = getSharedPreferences("Info_Click", MODE_PRIVATE);
        if (pref != null) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("what", "none");
            editor.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("Info_Click", MODE_PRIVATE);
        if (pref != null) {
            String infoWhat = pref.getString("what", "none");
            if (infoWhat.equals("home")) {
                setOnResumeNavi(0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("what", "none");
                editor.commit();
            }
            if (infoWhat.equals("noti")) {
                setOnResumeNavi(1);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("what", "none");
                editor.commit();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    private void getUser() {
        SharedPreferences pref = getSharedPreferences("Who_Login", MODE_PRIVATE);
        if (pref == null) {
            nhanVien = null;
        } else {
            String user = pref.getString("who", "");
            NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
            ArrayList<NhanVien> list = nhanVienDAO.selectNhanVien(null, "maNV=?", new String[]{user}, null);
            if (list.size() > 0) {
                nhanVien = list.get(0);
            }
        }
    }

    private void layoutAccount(String title) {
        LinearLayout layoutAcc = findViewById(R.id.layout_Account);
        LinearLayout layoutSearch = findViewById(R.id.layout_Search);
        TextView titleView = findViewById(R.id.textView_Title_Toolbar_Acc);
        ImageView imageView = findViewById(R.id.imageView_Avatar);

        layoutAcc.setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);
        titleView.setText(title);
        if (nhanVien != null) {
            imageView.setImageBitmap(changeType.byteToBitmap(nhanVien.getAvatar()));
        }
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
                if (id == R.id.item_navi_drawer_nv_trangChu) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 0 - home");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    itemNaviDr = 0;
                    useToolbar("", 0);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_home).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_home).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_nv_noti) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 1 - fptshop");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    itemNaviDr = 1;
                    useToolbar("FPT Shop", 0);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_noti).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_noti).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_nv_Laptop) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 2 - laptop");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    itemNaviDr = 3;
                    useToolbar("QLý Laptop", 1);
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_DonHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 3 - đơn hàng");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(3);
                    itemNaviDr = 4;
                    useToolbar("QLý Đơn Hàng", 1);
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_KhachHang) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 4 - khách hàng");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(4);
                    itemNaviDr = 5;
                    useToolbar("QLý Khách Hàng", 1);
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_Voucher) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 5 - voucher");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(5);
                    itemNaviDr = 6;
                    useToolbar("QLý Voucher", 1);
                    bottomNavigationView.setVisibility(View.GONE);
                }
                if (id == R.id.item_navi_drawer_nv_ThongKe) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 6 - thống kê");
                    NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(6);
                    itemNaviDr = 7;
                    useToolbar("Doanh Thu\nThống Kê", 0);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_thongKe).setCheckable(true);
                    bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_thongKe).setChecked(true);
                }
                if (id == R.id.item_navi_drawer_kh_DH_Damua) {
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 7 - giỏ hàng");
                    startActivity(new Intent(context, NV_DonHang_Activity.class));
                }

                if (id == R.id.item_navi_drawer_nv_lienHe) {
                    itemNaviDr = 8;
                    item.setCheckable(true);
                    Log.d(TAG, "onNavigationItemSelected: 8 - liên hệ");
                    bottomNavigationView.setVisibility(View.VISIBLE);
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
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(0);
                    naviView.getMenu().getItem(0).setChecked(true);
                    naviView.getMenu().getItem(0).setCheckable(true);
                    itemNaviDr = 0;
                    useToolbar("", 0);
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_nv_noti) {
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    naviView.getMenu().getItem(1).setChecked(true);
                    naviView.getMenu().getItem(1).setCheckable(true);
                    itemNaviDr = 1;
                    useToolbar("FPT Shop", 0);
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_nv_thongKe) {
                    NV_PagerAdapter_Bottom adapter = new NV_PagerAdapter_Bottom(getSupportFragmentManager());
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(2);
                    naviView.getMenu().getItem(7).setChecked(true);
                    naviView.getMenu().getItem(7).setCheckable(true);
                    itemNaviDr = 7;
                    useToolbar("Doanh Thu\nThống Kê", 0);
                    getSupportActionBar().show();
                }
                if (i == R.id.item_navi_bottom_nv_acc) {
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

    private void setOnResumeNavi(int frag) {
        NV_PagerAdapter_Drawer adapter = new NV_PagerAdapter_Drawer(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(frag);
        itemNaviDr = frag;
        if (frag == 0) {
            useToolbar("", 0);
            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_home).setCheckable(true);
            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_home).setChecked(true);
            naviView.getMenu().getItem(0).setChecked(true);
            naviView.getMenu().getItem(0).setCheckable(true);
            getSupportActionBar().show();
        }
        if (frag == 1) {
            useToolbar("Thông Báo", 0);
            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_noti).setCheckable(true);
            bottomNavigationView.getMenu().findItem(R.id.item_navi_bottom_nv_noti).setChecked(true);
            naviView.getMenu().getItem(1).setChecked(true);
            naviView.getMenu().getItem(1).setCheckable(true);
            getSupportActionBar().show();
        }
    }
}