package com.nhom5.quanlylaptop.KH_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.Entity.DiaChi;
import com.nhom5.quanlylaptop.Entity.IdData;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_DiaChi_Adapter extends RecyclerView.Adapter<KH_DiaChi_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<DiaChi> listDC;
    String TAG = "KH_DiaChi_Adapter_____";
    String selected;
    int selectedPos = -1;

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
        String maDC = IdData.getInstance().getIdDC();
        DiaChi diaChi = setRow(pos, author, maDC);

        if (pos == selectedPos){
            author.itemDC.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_square_selected));
        } else {
            author.itemDC.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_square));
        }

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                selectedPos = pos;

                IdData.getInstance().setIdDC(diaChi.getMaDC());
                setRow(pos, author, maDC);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDC.size();
    }



    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        TextView name, sdt, dc;
        LinearLayout itemDC;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_TenKH);
            sdt = itemView.findViewById(R.id.textView_SDT);
            dc = itemView.findViewById(R.id.textView_DiaChi);
            itemDC = itemView.findViewById(R.id.item_DiaChi);
        }
    }

    public DiaChi setRow(int pos, @NonNull KH_DiaChi_Adapter.AuthorViewHolder author, String maDC) {
        Log.d(TAG, "setRow: " + pos);
        DiaChi diaChi = listDC.get(pos);

        if (maDC != null){
            if (maDC.equals(diaChi.getMaDC())){
                selectedPos = pos;
            }
        }

        author.name.setText(diaChi.getTenNguoiNhan());
        author.sdt.setText(diaChi.getSDT());
        author.dc.setText(diaChi.getDiaChi() + " - " + diaChi.getXaPhuong()
                + " - " + diaChi.getQuanHuyen() + " - " + diaChi.getThanhPho());
        return diaChi;
    }
}
