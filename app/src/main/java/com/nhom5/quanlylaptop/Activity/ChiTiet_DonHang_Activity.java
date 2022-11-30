package com.nhom5.quanlylaptop.Activity;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.DiaChiDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DiaChi;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class ChiTiet_DonHang_Activity extends AppCompatActivity {

    Context context = this;
    DonHang donHang = null;
    String typeUser = "";
    String TAG = "ChiTiet_DonHang_Activity_____";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        useToolbar();
        getInfoDonHang();
        setLaptopView();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Chi tiết sản phẩm");
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
                typeUser = intent.getStringExtra("typeUser");
                Log.d(TAG, "getInfoLaptop: DonHang: " + donHang.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setReview(String isRate, String typeUser){
        LinearLayout layoutReview = findViewById(R.id.layout_Review);
        TextView titleReview = findViewById(R.id.textView_TitleReview);
        TextView detailReview = findViewById(R.id.textView_DetailReview);
        ImageView imageReview = findViewById(R.id.imageView_Review);

        if (typeUser.equals("KH")){
            if (isRate.equals("true")){
                titleReview.setText("Đơn hàng đã được đánh giá!");
                detailReview.setText("Cảm ơn bạn đã đánh giá. Chúc bạn có một trải nghiệm tuyệt vời với sản phẩm của chúng tôi.");
                layoutReview.setBackgroundColor(Color.parseColor("#26AB9A"));
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star_check_icon);
                imageReview.setImageBitmap(bitmap);
            } else {
                titleReview.setText("Đơn hàng chưa được đánh giá!");
                detailReview.setText("Cảm ơn bạn đã mua sản phẩm. Bạn hãy sớm đánh giá sản phẩm để chúng tôi biết trải nghiệm của bạn về sản phẩm nhé.");
                layoutReview.setBackgroundColor(Color.parseColor("#F44336"));
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star_crossed_icon);
                imageReview.setImageBitmap(bitmap);
            }
        } else {
            if (isRate.equals("true")){
                titleReview.setText("Đơn hàng đã được đánh giá!");
                detailReview.setText("Khách hàng đã đánh giá sản phẩm này. Xem chi tiết đánh giá thông qua nút đánh giá của đơn hàng.");
                layoutReview.setBackgroundColor(Color.parseColor("#26AB9A"));
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star_check_icon);
                imageReview.setImageBitmap(bitmap);
            } else {
                titleReview.setText("Đơn hàng chưa được đánh giá!");
                detailReview.setText("Khách hàng chưa đánh giá sản phẩm này. Xem chi tiết đánh giá thông qua nút đánh giá của đơn hàng.");
                layoutReview.setBackgroundColor(Color.parseColor("#F44336"));
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star_crossed_icon);
                imageReview.setImageBitmap(bitmap);
            }
        }
    }

    private void setLaptopView() {
        ImageView imageLaptop = findViewById(R.id.imageView_Laptop);
        TextView nameLap = findViewById(R.id.textView_TenLaptop);
        TextView soLuong = findViewById(R.id.textView_Soluong);
        TextView thanhTien = findViewById(R.id.textView_GiaTien);
        TextView nameKH = findViewById(R.id.textView_TenKH);
        TextView phone = findViewById(R.id.textView_SDT);
        TextView diaChi = findViewById(R.id.textView_DiaChi);
        TextView pttt = findViewById(R.id.textView_PTTT);
        TextView maDH = findViewById(R.id.textView_MaDH);
        TextView date = findViewById(R.id.textView_Date);
        Button hoanThanh = findViewById(R.id.button_HoanThanh);

        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", 0, 0, new byte[]{});
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
                "No Data", "No Data", "No Data", "No Data", "No Data", new byte[]{});
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

        imageLaptop.setImageBitmap(anhLap);
        nameLap.setText(laptop.getTenLaptop());
        thanhTien.setText(donHang.getThanhTien());
        soLuong.setText(String.valueOf(donHang.getSoLuong()));
        nameKH.setText(changeType.fullNameKhachHang(khachHang));
        phone.setText(khachHang.getPhone());
        diaChi.setText(donHang.getDiaChi());
        pttt.setText(donHang.getLoaiThanhToan());
        maDH.setText(donHang.getMaDH());
        date.setText(donHang.getNgayMua());

        hoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setReview(donHang.getIsDanhGia(), typeUser);
    }

}