package com.nhom5.quanlylaptop.HelloWorld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhom5.quanlylaptop.PagerAdapter.Hello_PagerAdapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import me.relex.circleindicator.CircleIndicator;

public class HelloActivity extends AppCompatActivity {

    private TextView skip;
    private ViewPager viewPager;
    private LinearLayout layoutbottom;
    private CircleIndicator circleIndicator;
    String TAG = "HelloActivity_____";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_activity);
        initUI();
        Hello_PagerAdapter helloPagerAdapter = new Hello_PagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(helloPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    skip.setVisibility(View.GONE);
                    layoutbottom.setVisibility(View.GONE);
                }
                else{
                    skip.setVisibility(View.VISIBLE);
                    layoutbottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void initUI() {
        skip = findViewById(R.id.textView_Skip);
        viewPager = findViewById(R.id.viewPager);
        layoutbottom = findViewById(R.id.layout_button);
        circleIndicator = findViewById(R.id.circleIndicator);
        Button button_Next = findViewById(R.id.button_Next);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });
        button_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < 3) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

    }
}