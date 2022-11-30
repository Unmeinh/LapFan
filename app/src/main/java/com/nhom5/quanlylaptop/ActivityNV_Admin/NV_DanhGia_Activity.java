package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class NV_DanhGia_Activity extends AppCompatActivity {

    Context context = this;
    DonHang donHang = null;
    Laptop laptop;
    String TAG = "NV_DanhGia_Activity_____";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nv_danh_gia);
        useToolbar();
        getInfoDonHang();
        setLaptopView();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Đánh giá Sản phẩm");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getInfoDonHang() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                donHang = (DonHang) intent.getExtras().getBinder("donhang");
                Log.d(TAG, "getInfoLaptop: DonHang: " + donHang.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setReview(String isRate){
        LinearLayout layoutReview = findViewById(R.id.layout_Review);
        TextView detailReview = findViewById(R.id.textView_DetailReview);
        ImageView imageReview = findViewById(R.id.imageView_Review);
        EditText reviewKH = findViewById(R.id.editText_DanhGia);

        if (isRate.equals("true")){
            detailReview.setText("Khách hàng đã đánh giá đơn hàng này!");
            layoutReview.setBackgroundColor(Color.parseColor("#26AB9A"));
            imageReview.setImageResource(R.drawable.check_icon);
        }
        if (isRate.equals("false")){
            detailReview.setText("Khách hàng chưa đánh giá đơn hàng này!");
            layoutReview.setBackgroundColor(Color.parseColor("#F44336"));
            imageReview.setImageResource(R.drawable.crossed_icon);
        }
    }

    private void setLaptopView() {
        ImageView imageLaptop = findViewById(R.id.imageView_Laptop);
        ImageView imageUser = findViewById(R.id.imageView_Avatar);
        TextView nameLap = findViewById(R.id.textView_TenLaptop);
        TextView soLuong = findViewById(R.id.textView_Soluong);
        TextView giaTien = findViewById(R.id.textView_GiaTien);
        TextView nameKH = findViewById(R.id.textView_TenUser);
        TextView email = findViewById(R.id.textView_Email);
        LinearLayout onclickLaptop = findViewById(R.id.onclick_Laptop);

        laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", 0, 0, new byte[]{});
        Log.d(TAG, "setRow: DonHang: " + donHang.toString());
        LaptopDAO laptopDAO = new LaptopDAO(context);
        ArrayList<Laptop> listLap = laptopDAO.selectLaptop(null, null, null, null);

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (donHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        KhachHang khachHang = new KhachHang("No Data", "No Data", "No Data", "No Data",
                "0", "No Data", "No Data", "No Data", "No Data", new byte[]{});
        Log.d(TAG, "setRow: KhachHang: " + khachHang.toString());
        KhachHangDAO khachHangDAO = new KhachHangDAO(context);
        ArrayList<KhachHang> listKH = khachHangDAO.selectKhachHang(null, null, null, null);

        for (int i = 0; i < listKH.size(); i++) {
            KhachHang getKH = listKH.get(i);
            if (donHang.getMaKH().equals(getKH.getMaKH())) {
                khachHang = getKH;
            }
        }

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        Bitmap anhKH = changeType.byteToBitmap(khachHang.getAvatar());

        imageLaptop.setImageBitmap(anhLap);
        imageUser.setImageBitmap(anhKH);
        nameLap.setText(laptop.getTenLaptop());
        giaTien.setText(donHang.getThanhTien());
        soLuong.setText(String.valueOf(donHang.getSoLuong()));
        nameKH.setText(changeType.fullNameKhachHang(khachHang));
        email.setText(khachHang.getEmail());

        setReview(donHang.getIsDanhGia());

        onclickLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info_Laptop_Activity.class);
                if (laptop != null) {
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("laptop", laptop);
                    Log.d(TAG, "onBindViewHolder: Laptop: " + laptop.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("openFrom", "other");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}