package com.nhom5.quanlylaptop.KH_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_ThongBao_Adapter  extends RecyclerView.Adapter<KH_ThongBao_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<ThongBao> listTB;
    String TAG = "KH_ThongBao_Adapter_____";

    public KH_ThongBao_Adapter(ArrayList<ThongBao> listTB, Context context) {
        this.listTB = listTB;
        this.context = context;
    }

    @NonNull
    @Override
    public KH_ThongBao_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_thongbao, vGroup, false);
        return new KH_ThongBao_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_ThongBao_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);
    }

    @Override
    public int getItemCount() {
        return listTB.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView title, chiTiet, date;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_Title_Notifi);
            chiTiet = itemView.findViewById(R.id.textView_Content_Notifi);
            date = itemView.findViewById(R.id.textView_Date_Notifi);
        }
    }

    public void setRow(int pos, @NonNull KH_ThongBao_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        ThongBao thongBao = listTB.get(pos);
        author.title.setText(thongBao.getTitle());
        author.chiTiet.setText(thongBao.getChiTiet());
        author.date.setText(thongBao.getNgayTB());
    }
}