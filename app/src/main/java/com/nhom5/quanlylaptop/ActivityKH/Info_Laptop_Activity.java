package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class Info_Laptop_Activity extends AppCompatActivity {

    Context context = this;
    Laptop laptop = null;
    String TAG = "Info_Laptop_Activity_____";
    ImageView imageLaptop;
    TextView tenLaptop, giaLaptop, tsktLaptop;
    ChangeType changeType = new ChangeType();
    AppCompatButton buyNow, themVaoGio;
    GioHangDAO gioHangDAO;
    ArrayList<GioHang> listGio = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_laptop);
        imageLaptop = findViewById(R.id.imageView_Laptop);
        tenLaptop = findViewById(R.id.textView_TenLaptop);
        giaLaptop = findViewById(R.id.textView_GiaTien);
        tsktLaptop = findViewById(R.id.textView_TSKT);
        buyNow = findViewById(R.id.button_Mua);
        themVaoGio = findViewById(R.id.button_Add_To_GioHang);
        gioHangDAO = new GioHangDAO(context);

        useToolbar();
        getInfoLaptop();
        setInfoLaptop();
        addToCart();
        buyNowLaptop();
    }

    private void buyNowLaptop(){
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (laptop != null) {
                    Intent intent = new Intent(context, KH_ThanhToan_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("laptop", laptop);
                    Log.d(TAG, "onBindViewHolder: Laptop: " + laptop.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("input", "muangay");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addToCart() {
        listGio = gioHangDAO.selectGioHang(null, null, null, null);
        if (listGio != null) {
            themVaoGio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GioHang gioHang = new GioHang("GH" + listGio.size(), laptop.getMaLaptop(),
                            "No Data", "2022-11-17", 1);
                    gioHangDAO.insertGioHang(gioHang);
                }
            });
        }
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