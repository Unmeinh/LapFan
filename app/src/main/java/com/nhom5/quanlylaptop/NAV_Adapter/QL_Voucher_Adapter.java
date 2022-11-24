package com.nhom5.quanlylaptop.NAV_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_Voucher_Adapter extends RecyclerView.Adapter<QL_Voucher_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Voucher> listVou;
    String TAG = "QL_Voucher_Adapter_____";

    public QL_Voucher_Adapter(ArrayList<Voucher> listVou, Context context) {
        this.listVou = listVou;
        this.context = context;
    }

    @NonNull
    @Override
    public QL_Voucher_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_voucher, vGroup, false);
        return new QL_Voucher_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QL_Voucher_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);
    }

    @Override
    public int getItemCount() {
        return listVou.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, ma, sale;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_TenVoucher);
            date = itemView.findViewById(R.id.textView_Date_Voucher);
            ma = itemView.findViewById(R.id.textView_MaVoucher);
            sale = itemView.findViewById(R.id.textView_GiamGia);
        }
    }

    public void setRow(int pos, @NonNull QL_Voucher_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        Voucher voucher = listVou.get(pos);
        author.ma.setText(voucher.getMaVoucher());
        author.sale.setText("Giảm giá\n" + voucher.getGiamGia());
        author.name.setText(voucher.getTenVoucher());
        if (voucher.getNgayBD().equals(voucher.getNgayKT())) {
            author.date.setText("Duy nhất trong\n" + voucher.getNgayBD());
        } else {
            author.date.setText("Từ  " + voucher.getNgayBD() + "\nđến " + voucher.getNgayKT());
        }
    }
}