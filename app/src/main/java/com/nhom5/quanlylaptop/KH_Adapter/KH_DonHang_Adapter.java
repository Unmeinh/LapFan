package com.nhom5.quanlylaptop.KH_Adapter;

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
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class KH_DonHang_Adapter extends RecyclerView.Adapter<KH_DonHang_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Laptop> listLap;
    ArrayList<DonHang> listDon;
    String TAG = "KH_DonHang_Adapter_____";

    public KH_DonHang_Adapter(ArrayList<Laptop> listLap, ArrayList<DonHang> listDon, Context context) {
        this.listLap = listLap;
        this.listDon = listDon;
        this.context = context;
    }

    @NonNull
    @Override
    public KH_DonHang_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_donhang_laptop, vGroup, false);
        return new KH_DonHang_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_DonHang_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        DonHang donHang = setRow(pos, author);

        author.danhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (donHang != null) {
                    Intent intent = new Intent(context, KH_DanhGia_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("donhang", donHang);
                    Log.d(TAG, "onBindViewHolder: DonHang: " + donHang.toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (donHang != null) {
                    Intent intent = new Intent(context, ChiTiet_DonHang_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("donhang", donHang);
                    Log.d(TAG, "onBindViewHolder: DonHang: " + donHang.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("typeUser", "KH");
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
        ImageView imgLaptop;
        TextView name, gia, soLuong;
        Button danhGia;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_Laptop);
            name = itemView.findViewById(R.id.textView_TenLaptop);
            gia = itemView.findViewById(R.id.textView_GiaTien);
            soLuong = itemView.findViewById(R.id.textView_Soluong);
            danhGia = itemView.findViewById(R.id.button_Open_DanhGia);
        }
    }

    public DonHang setRow(int pos, @NonNull KH_DonHang_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        DonHang donHang = listDon.get(pos);
        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", new byte[]{});
        Log.d(TAG, "setRow: DonHang: " + donHang.toString());

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (donHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());

        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(donHang.getThanhTien());
        author.soLuong.setText(String.valueOf(donHang.getSoLuong()));
        return donHang;
    }
}