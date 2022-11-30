package com.nhom5.quanlylaptop.NAV_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.Activity.ChiTiet_DonHang_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_DanhGia_Activity;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_DonHang_Adapter extends RecyclerView.Adapter<QL_DonHang_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Laptop> listLap;
    ArrayList<KhachHang> listKH;
    ArrayList<DonHang> listDon;
    TextView countDH;
    String TAG = "QL_DonHang_Adapter_____";

    public QL_DonHang_Adapter(ArrayList<Laptop> listLap, ArrayList<DonHang> listDon, ArrayList<KhachHang> listKH, Context context, TextView countDH) {
        this.listLap = listLap;
        this.listDon = listDon;
        this.listKH = listKH;
        this.context = context;
        this.countDH = countDH;
    }

    @NonNull
    @Override
    public QL_DonHang_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_don_hang, vGroup, false);
        return new QL_DonHang_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QL_DonHang_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        DonHang donHang = setRow(pos, author);
        if (countDH != null){
            countDH.setText(String.valueOf(listDon.size()));
        }

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (donHang != null) {
                    Intent intent = new Intent(context, ChiTiet_DonHang_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("donhang", donHang);
                    Log.d(TAG, "onBindViewHolder: DonHang: " + donHang.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("typeUser", "NV");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDon.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView tenKH, tenLaptop, phone, money, date;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKH = itemView.findViewById(R.id.textView_TenKH);
            tenLaptop = itemView.findViewById(R.id.textView_TenLaptop);
            phone = itemView.findViewById(R.id.textView_SDT);
            money = itemView.findViewById(R.id.textView_Soluong);
            date = itemView.findViewById(R.id.textView_Date);
        }
    }

    public DonHang setRow(int pos, @NonNull QL_DonHang_Adapter.AuthorViewHolder author) {
        ChangeType changeType = new ChangeType();
        Log.d(TAG, "setRow: " + pos);
        DonHang donHang = listDon.get(pos);
        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", 0, 0, new byte[]{});
        KhachHang khachHang = new KhachHang("No Data", "No Data", "No Data",  "No Data", "No Data", "No Data", "No Data",
                "No Data", "No Data", new byte[]{});
        Log.d(TAG, "setRow: DonHang: " + donHang.toString());

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (donHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        for (int i = 0; i < listKH.size(); i++) {
            KhachHang getKH = listKH.get(i);
            if (donHang.getMaKH().equals(getKH.getMaKH())) {
                khachHang = getKH;
            }
        }

        author.tenKH.setText(changeType.fullNameKhachHang(khachHang));
        author.tenLaptop.setText(laptop.getTenLaptop());
        author.phone.setText(khachHang.getPhone());
        author.money.setText(donHang.getThanhTien());
        author.date.setText(donHang.getNgayMua());
        return donHang;
    }
}