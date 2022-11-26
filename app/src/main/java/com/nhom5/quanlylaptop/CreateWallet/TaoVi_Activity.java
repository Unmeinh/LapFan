package com.nhom5.quanlylaptop.CreateWallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nhom5.quanlylaptop.ActivityKH.KH_ViTien_Activity;
import com.nhom5.quanlylaptop.PagerAdapter.TaoVi_PagerAdapter;
import com.nhom5.quanlylaptop.R;

public class TaoVi_Activity extends AppCompatActivity {

    ViewPager viewPager;
    Button nextFrag, prevFrag;
    TaoVi_PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_vi);
        viewPager = findViewById(R.id.viewPager);
        nextFrag = findViewById(R.id.button_Next);
        prevFrag = findViewById(R.id.button_Prev);
        useToolbar("Tạo ví FPT Pay");
        prevFrag.setVisibility(View.GONE);

        prevFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() > 0){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });

        nextFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < 2){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    Intent intent = new Intent(TaoVi_Activity.this, KH_ViTien_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        pagerAdapter = new TaoVi_PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    prevFrag.setVisibility(View.GONE);
                    nextFrag.setVisibility(View.VISIBLE);
                    useToolbar("Tạo ví FPT Pay");
                } else if (position == 1) {
                    prevFrag.setVisibility(View.VISIBLE);
                    nextFrag.setVisibility(View.VISIBLE);
                    useToolbar("Tài khoản liên kết");
                } else if (position == 2) {
                    prevFrag.setVisibility(View.VISIBLE);
                    nextFrag.setVisibility(View.VISIBLE);
                    useToolbar("Chọn ngân hàng liên kết");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void useToolbar(String title) {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText(title);
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
