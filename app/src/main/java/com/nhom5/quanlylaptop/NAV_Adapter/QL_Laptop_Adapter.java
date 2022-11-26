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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_Laptop_Adapter extends RecyclerView.Adapter<QL_Laptop_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<HangLaptop> listHang;
    ArrayList<Laptop> listLap;
    HangLaptopDAO hangLaptopDAO;
    LaptopDAO laptopDAO;
    String TAG = "QL_Laptop_Adapter_____";

    public QL_Laptop_Adapter(ArrayList<Laptop> listLap, ArrayList<HangLaptop> listHang, Context context) {
        this.listLap = listLap;
        this.listHang = listHang;
        this.context = context;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_laptop, vGroup, false);
        return new AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);

        author.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
            }
        });

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info_Laptop_Activity.class);
                Laptop laptop = listLap.get(pos);
                if (laptop != null) {
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("laptop", laptop);
                    Log.d(TAG, "onBindViewHolder: Laptop: " + laptop.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("openFrom", "other");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLap.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLaptop, imgHang;
        TextView name, gia, soLuong, daBan;
        ImageButton delete;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_Laptop);
            imgHang = itemView.findViewById(R.id.imageView_HangLaptop);
            name = itemView.findViewById(R.id.textView_TenLaptop);
            gia = itemView.findViewById(R.id.textView_GiaTien);
            soLuong = itemView.findViewById(R.id.textView_Soluong);
            delete = itemView.findViewById(R.id.imageButton_Delete);
            daBan = itemView.findViewById(R.id.textView_SoSP_DaBan);
        }
    }

    public void setRow(int pos, @NonNull AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        Laptop laptop = listLap.get(pos);
        HangLaptop hangLaptop = new HangLaptop("No Data", "No Data", new byte[]{});
        Log.d(TAG, "setRow: Laptop: " + laptop.toString());


        for (int i = 0; i < listHang.size(); i++) {
            HangLaptop getHang = listHang.get(i);
            if (laptop.getMaHangLap().equals(getHang.getMaHangLap())) {
                hangLaptop = getHang;
            }
        }

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        Bitmap anhHang = changeType.byteToBitmap(hangLaptop.getAnhHang());

        author.imgLaptop.setImageBitmap(anhLap);
        author.imgHang.setImageBitmap(anhHang);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText("Giá tiền: " + laptop.getGiaTien());
//        author.soLuong.setText(laptop.getSoLuong());
//        author.daBan.setText(laptop.getDaBan());
    }
}