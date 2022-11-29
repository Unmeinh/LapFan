package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.IdData;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Info_Laptop_Activity extends AppCompatActivity {

    Context context = this;
    Laptop laptop = null;
    String TAG = "Info_Laptop_Activity_____";
    ImageView imageLaptop;
    TextView tenLaptop, giaLaptop, tsktLaptop, soLuong;
    ChangeType changeType = new ChangeType();
    AppCompatButton buyNow, themVaoGio;
    GioHangDAO gioHangDAO;
    ArrayList<GioHang> listGio = new ArrayList<>();
    String openFrom;
    LinearLayout layout;
    KhachHang khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_laptop);
        imageLaptop = findViewById(R.id.imageView_Laptop);
        tenLaptop = findViewById(R.id.textView_TenLaptop);
        giaLaptop = findViewById(R.id.textView_GiaTien);
        tsktLaptop = findViewById(R.id.textView_TSKT);
        soLuong = findViewById(R.id.textView_Soluong);
        buyNow = findViewById(R.id.button_Mua);
        layout = findViewById(R.id.layoutViewer);
        themVaoGio = findViewById(R.id.button_Add_To_GioHang);
        gioHangDAO = new GioHangDAO(context);

        getUser();
        useToolbar();
        getInfoLaptop();
        setInfoLaptop();
        addToCart();
        buyNowLaptop();

        if (openFrom != null) {
            if (openFrom.equals("viewer")) {
                layout.setVisibility(View.VISIBLE);
            } else {
                layout.setVisibility(View.GONE);
            }
        }
    }

    private void buyNowLaptop() {
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (laptop != null) {
                    if (laptop.getSoLuong() != 0) {
                        Intent intent = new Intent(context, KH_ThanhToan_Activity.class);
                        final Bundle bundle = new Bundle();
                        bundle.putBinder("laptop", laptop);
                        Log.d(TAG, "onBindViewHolder: Laptop: " + laptop.toString());
                        intent.putExtras(bundle);
                        intent.putExtra("input", "MuaNgay");
                        IdData.getInstance().setIdDC("");
                        IdData.getInstance().setIdVou("");
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Sản phẩm đang hết hàng!\nXin vui lòng đợi chúng tôi nhập sản phẩm!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUser(){
        SharedPreferences pref = getSharedPreferences("Who_Login", MODE_PRIVATE);
        if (pref == null) {
            khachHang = null;
        } else {
            String user = pref.getString("who", "");
            KhachHangDAO khachHangDAO = new KhachHangDAO(context);
            ArrayList<KhachHang> list = khachHangDAO.selectKhachHang(null, "maKH=?", new String[]{user}, null);
            if (list.size() > 0){
                khachHang = list.get(0);
            }
        }
    }

    private void addToCart() {
        listGio = gioHangDAO.selectGioHang(null, null, null, null);
        if (listGio != null) {
            themVaoGio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (laptop.getSoLuong() != 0) {
                        GioHang gioHang = new GioHang("GH" + listGio.size(), laptop.getMaLaptop(),
                                khachHang.getMaKH(), "2022-11-17", "No Data", 1);
                        gioHangDAO.insertGioHang(gioHang);

                        Date currentTime = Calendar.getInstance().getTime();
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
                        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context);
                        ThongBao thongBao = new ThongBao("TB", khachHang.getMaKH(), "Quản lý giỏ hàng",
                                "Bạn đã thêm Laptop " + laptop.getTenLaptop() + " với giá " + laptop.getGiaTien() + " vào giỏ hàng.", date);
                        thongBaoDAO.insertThongBao(thongBao, "kh");
                        finish();
                    } else {
                        Toast.makeText(context, "Sản phẩm đang hết hàng!\nXin vui lòng đợi chúng tôi nhập sản phẩm!", Toast.LENGTH_SHORT).show();
                    }
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
            if (laptop.getThongSoKT().equals("RAM 32GB")) {
                tsktLaptop.setText(R.string.tskt_ram_32g);
            }
            soLuong.setText("Số lượng còn lại: " + laptop.getSoLuong() + " sản phẩm");
        }
    }

    private void getInfoLaptop() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                laptop = (Laptop) intent.getExtras().getBinder("laptop");
                openFrom = intent.getStringExtra("openFrom");
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