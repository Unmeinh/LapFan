package com.nhom5.quanlylaptop.KH_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.Entity.DiaChi;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_DiaChi_Adapter extends RecyclerView.Adapter<KH_DiaChi_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<DiaChi> listDC;
    String TAG = "KH_DiaChi_Adapter_____";

    public KH_DiaChi_Adapter(ArrayList<DiaChi> listDC, Context context) {
        this.listDC = listDC;
        this.context = context;
    }

    @NonNull
    @Override
    public KH_DiaChi_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_diachi, vGroup, false);
        return new KH_DiaChi_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_DiaChi_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        DiaChi diaChi = setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, diaChi.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDC.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView name, sdt, dc;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_TenKH);
            sdt = itemView.findViewById(R.id.textView_SDT);
            dc = itemView.findViewById(R.id.textView_DiaChi);
        }
    }

    public DiaChi setRow(int pos, @NonNull KH_DiaChi_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        DiaChi diaChi = listDC.get(pos);

        author.name.setText(diaChi.getTenNguoiNhan());
        author.sdt.setText(diaChi.getSDT());
        author.dc.setText(diaChi.getDiaChi() + " - " + diaChi.getXaPhuong()
                + " - " + diaChi.getQuanHuyen() + " - " + diaChi.getThanhPho());
        return diaChi;
    }
}
