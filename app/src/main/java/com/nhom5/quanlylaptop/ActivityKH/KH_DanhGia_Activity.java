package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class KH_DanhGia_Activity extends AppCompatActivity {

    Context context = this;
    DonHang donHang = null;
    String TAG = "KH_DanhGia_Activity_____";
    EditText reviewInput;
    Laptop laptop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_danh_gia);
        reviewInput = findViewById(R.id.editText_DanhGia);
        useToolbar();
        getInfoDonHang();
        setLaptopView();
        setReviewText();
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

    private void setLaptopView(){
        ImageView imageLaptop = findViewById(R.id.imageView_Laptop);
        TextView name = findViewById(R.id.textView_TenLaptop);
        TextView soLuong = findViewById(R.id.textView_Soluong);
        TextView giaTien = findViewById(R.id.textView_GiaTien);
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

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());

        imageLaptop.setImageBitmap(anhLap);
        name.setText(laptop.getTenLaptop());
        giaTien.setText(donHang.getThanhTien());
        soLuong.setText(String.valueOf(donHang.getSoLuong()));

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

    private void setReviewText(){
        AppCompatButton review1 = findViewById(R.id.button_Hint_DanhGia1);
        AppCompatButton review2 = findViewById(R.id.button_Hint_DanhGia2);
        AppCompatButton review3 = findViewById(R.id.button_Hint_DanhGia3);
        AppCompatButton review4 = findViewById(R.id.button_Hint_DanhGia4);

        review1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewInput.setText(R.string.ch_t_l_ng_s_n_ph_m_tuy_t_v_i);
            }
        });

        review2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewInput.setText(R.string.r_t_ng_ti_n);
            }
        });

        review3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewInput.setText(R.string.shop_ph_c_v_t_t);
            }
        });

        review4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewInput.setText(R.string.th_i_gian_giao_h_ng_r_t_nhanh);
            }
        });
    }
}