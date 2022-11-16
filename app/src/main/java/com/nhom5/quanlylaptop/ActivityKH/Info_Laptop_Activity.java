package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

public class Info_Laptop_Activity extends AppCompatActivity {

    Context context = this;
    Laptop laptop = null;
    String TAG = "Info_Laptop_Activity_____";
    ImageView imageLaptop;
    TextView tenLaptop, giaLaptop, tsktLaptop;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_laptop);
        imageLaptop = findViewById(R.id.imageView_Laptop);
        tenLaptop = findViewById(R.id.textView_TenLaptop);
        giaLaptop = findViewById(R.id.textView_GiaTien);
        tsktLaptop = findViewById(R.id.textView_TSKT);
        useToolbar();
        getInfoLaptop();
        setInfoLaptop();


    }

    private void setInfoLaptop() {
        if (laptop != null) {
            imageLaptop.setImageBitmap(changeType.byteToBitmap(laptop.getAnhLaptop()));
            tenLaptop.setText(laptop.getTenLaptop());
            giaLaptop.setText("Giá tiền: " + laptop.getGiaTien());
            if (laptop.getThongSoKT().equals("RAM 4GB")) {
                tsktLaptop.setText(R.string.tskt_ram_4g);
            }
            if (laptop.getThongSoKT().equals("RAM 8GB")) {
                tsktLaptop.setText(R.string.tskt_ram_8g);
            }
            if (laptop.getThongSoKT().equals("RAM 16GB")) {
                tsktLaptop.setText(R.string.tskt_ram_16g);
            }
        }
    }

    private void getInfoLaptop() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                laptop = (Laptop) intent.getExtras().getBinder("laptop");
                Log.d(TAG, "getInfoLaptop: laptop: " + laptop.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Navi));
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}