package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nhom5.quanlylaptop.KH_Loader.KH_DiaChi_Loader;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

public class KH_DiaChi_Activity extends AppCompatActivity {

    String TAG = "KH_DiaChi_Activity_____";
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_dia_chi);

        KH_DiaChi_Loader kh_diaChi_loader = new KH_DiaChi_Loader(context, findViewById(R.id.recyclerView_DiaChi));
        kh_diaChi_loader.execute("");

        useToolbar();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Danh sách Voucher");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData addData = new AddData(context);
                addData.setNullDataDC();
                finish();
            }
        });
    }
}