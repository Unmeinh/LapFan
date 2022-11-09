package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_DonHang_Activity extends AppCompatActivity {

    List<HashMap<String, String>> list = new ArrayList<>();
    ListView listView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_don_hang);
        context = this;
        listView = findViewById(R.id.listView_Laptop_KH_DanhGia);
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, list, R.layout.cardview_laptop_rating, new String[]{}, new int[]{});
        listView.setAdapter(simpleAdapter);

        View view = getLayoutInflater().inflate(R.layout.cardview_laptop_rating, null);
        Button button = view.findViewById(R.id.button_Open_DanhGia);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, KH_Rating_Activity.class));
            }
        });
        useToolbar();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.title_Toolbar);
        titleToolbar.setText("Quản lý Đơn hàng");
        ImageButton back = findViewById(R.id.button_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}