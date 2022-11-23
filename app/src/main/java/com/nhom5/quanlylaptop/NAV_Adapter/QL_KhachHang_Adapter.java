package com.nhom5.quanlylaptop.NAV_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_KhachHang_Adapter extends RecyclerView.Adapter<QL_KhachHang_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<KhachHang> listKH;
    KhachHangDAO khachHangDAO;
    String TAG = "QL_KhachHang_Adapter_____";

    public QL_KhachHang_Adapter(ArrayList<KhachHang> listKH, Context context) {
        this.listKH = listKH;
        this.context = context;
        khachHangDAO = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public QL_KhachHang_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_user, vGroup, false);
        return new QL_KhachHang_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QL_KhachHang_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        KhachHang khachHang = setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, khachHang.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKH.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name, gender, phone;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.imageView_Avatar);
            name = itemView.findViewById(R.id.textView_TenUser);
            gender = itemView.findViewById(R.id.textView_GioiTinh);
            phone = itemView.findViewById(R.id.textView_SDT);
        }
    }

    public KhachHang setRow(int pos, @NonNull QL_KhachHang_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        KhachHang khachHang = listKH.get(pos);
        Log.d(TAG, "setRow: KhachHang: " + khachHang.toString());

        ChangeType changeType = new ChangeType();
        Bitmap avatar = changeType.byteToBitmap(khachHang.getAvatar());

        author.avatar.setImageBitmap(avatar);
        author.name.setText(khachHang.getHoKH() + " " + khachHang.getTenKH());
        author.gender.setText(khachHang.getGioiTinh());
        author.phone.setText(khachHang.getPhone());
        return khachHang;
    }
}