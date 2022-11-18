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

import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_Voucher_Adapter extends RecyclerView.Adapter<KH_Voucher_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Voucher> listVou;
    String TAG = "KH_Voucher_Adapter_____";

    public KH_Voucher_Adapter(ArrayList<Voucher> listVou, Context context) {
        this.listVou = listVou;
        this.context = context;
    }

    @NonNull
    @Override
    public KH_Voucher_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_voucher, vGroup, false);
        return new KH_Voucher_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_Voucher_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);
    }

    @Override
    public int getItemCount() {
        return listVou.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, ma;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_TenVoucher);
            date = itemView.findViewById(R.id.textView_Date_Voucher);
            ma = itemView.findViewById(R.id.textView_MaVoucher);
        }
    }

    public void setRow(int pos, @NonNull KH_Voucher_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        Voucher voucher = listVou.get(pos);
        author.ma.setText("Mã Voucher\n" + voucher.getTenVoucher());
        author.name.setText("Giảm giá\n" + voucher.getGiamGia());
        if (voucher.getNgayBD().equals(voucher.getNgayKT())) {
            author.date.setText("Duy nhất trong\n" + voucher.getNgayBD());
        } else {
            author.date.setText("Từ  " + voucher.getNgayBD() + "\nđến " + voucher.getNgayKT());
        }
    }
}