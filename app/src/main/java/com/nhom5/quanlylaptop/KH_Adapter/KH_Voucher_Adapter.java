package com.nhom5.quanlylaptop.KH_Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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
        Voucher voucher = setRow(pos, author);

        author.buttonUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("voucher_thanhToan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String maVou = voucher.getMaVoucher();
                String giamGia = voucher.getGiamGia();

                editor.putString("maVou", maVou);
                editor.putString("giamGia", giamGia);
                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVou.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, ma, sale, buttonUse;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_TenVoucher);
            date = itemView.findViewById(R.id.textView_Date_Voucher);
            ma = itemView.findViewById(R.id.textView_MaVoucher);
            sale = itemView.findViewById(R.id.textView_GiamGia);
            buttonUse = itemView.findViewById(R.id.button_Use_Voucher);
        }
    }

    public Voucher setRow(int pos, @NonNull KH_Voucher_Adapter.AuthorViewHolder author) {
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
        return voucher;
    }
}