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

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_NhanVien_Adapter extends RecyclerView.Adapter<QL_NhanVien_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<NhanVien> listNV;
    NhanVienDAO nhanVienDAO;
    String TAG = "QL_KhachHang_Adapter_____";

    public QL_NhanVien_Adapter(ArrayList<NhanVien> listNV, Context context) {
        this.listNV = listNV;
        this.context = context;
        nhanVienDAO = new NhanVienDAO(context);
    }

    @NonNull
    @Override
    public QL_NhanVien_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_user, vGroup , false);
        return new QL_NhanVien_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QL_NhanVien_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        NhanVien nhanVien = setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, nhanVien.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNV.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder{
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

    public NhanVien setRow(int pos, @NonNull QL_NhanVien_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        NhanVien nhanVien = listNV.get(pos);
        Log.d(TAG, "setRow: NhanVien: " + nhanVien.toString());

        ChangeType changeType = new ChangeType();
        Bitmap avatar = changeType.byteToBitmap(nhanVien.getAvatar());

        author.avatar.setImageBitmap(avatar);
        author.name.setText(nhanVien.getHoNV() + " " + nhanVien.getTenNV());
        author.gender.setText(nhanVien.getGioiTinh());
        author.phone.setText(nhanVien.getPhone());
        return nhanVien;
    }

}
