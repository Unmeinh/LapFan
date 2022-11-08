package com.nhom5.quanlylaptop.FragmentKH;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom5.quanlylaptop.ActivityKH.InfoLaptopActivity;
import com.nhom5.quanlylaptop.ActivityKH.KH_DonHang_Activity;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_GioHang_Fragment extends Fragment {

    List<HashMap<String, String>> list = new ArrayList<>();
    ListView listView;
    LinearLayout emptyGHLayout;
    RelativeLayout viewGHLayout;
    AppCompatButton buttonPayNow;
    TextView totalMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_gio_hang, container, false);
        emptyGHLayout = view.findViewById(R.id.empty_Gio_Hang);
        viewGHLayout = view.findViewById(R.id.view_Gio_Hang);
        listView = view.findViewById(R.id.listView_Gio_Hang);
        buttonPayNow = view.findViewById(R.id.button_Pay_Now);
        totalMoney = view.findViewById(R.id.total_Money_GH);

        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.cardview_gio_hang, new String[]{}, new int[]{});
        listView.setAdapter(simpleAdapter);

        emptyGHLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyGHLayout.setVisibility(View.GONE);
                viewGHLayout.setVisibility(View.VISIBLE);
            }
        });

        buttonPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGHLayout.setVisibility(View.GONE);
                emptyGHLayout.setVisibility(View.VISIBLE);
            }
        });

        totalMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KH_DonHang_Activity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), KH_DonHang_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}