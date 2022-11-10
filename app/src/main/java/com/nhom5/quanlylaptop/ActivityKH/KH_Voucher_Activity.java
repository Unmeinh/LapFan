package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_Voucher_Activity extends AppCompatActivity {

    List<HashMap<String, String>> list = new ArrayList<>();
    ListView listView;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_voucher);
        listView = findViewById(R.id.listView_Voucher_KH);

        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, list, R.layout.cardview_kh_voucher, new String[]{}, new int[]{});
        listView.setAdapter(simpleAdapter);
    }
}