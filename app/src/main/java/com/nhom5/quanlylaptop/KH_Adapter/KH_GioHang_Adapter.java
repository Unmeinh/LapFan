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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.FragmentKH.KH_GioHang_Fragment;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class KH_GioHang_Adapter extends RecyclerView.Adapter<KH_GioHang_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Laptop> listLap;
    ArrayList<GioHang> listGio;
    GioHangDAO gioHangDAO;
    KH_GioHang_Fragment kh_gioHang_fragment;
    ChangeType changeType = new ChangeType();
    TextView totalTV;
    int total;
    String TAG = "KH_GioHang_Adapter_____";

    public KH_GioHang_Adapter(ArrayList<Laptop> listLap, ArrayList<GioHang> listGio, Context context, KH_GioHang_Fragment kh_gioHang_fragment) {
        this.listLap = listLap;
        this.listGio = listGio;
        this.context = context;
        this.kh_gioHang_fragment = kh_gioHang_fragment;
        gioHangDAO = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public KH_GioHang_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_gio_hang, vGroup, false);
        return new KH_GioHang_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_GioHang_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        Laptop laptop = setRow(pos, author, "none");

        author.giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang old = listGio.get(pos);
                int soLuong = old.getSoLuong();
                if (soLuong > 1) {
                    soLuong--;
                    GioHang gioHang = new GioHang(old.getMaGio(), old.getMaLaptop(), old.getMaKH(), old.getNgayThem(), soLuong);
                    gioHangDAO.updateGioHang(gioHang);
                    listGio = gioHangDAO.selectGioHang(null, null, null, null);
                    setRow(pos, author, "down");
                } else {
                    Toast.makeText(context, "!!!Tối thiểu một sản phẩm trong giỏ hàng!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        author.tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang old = listGio.get(pos);
                int soLuong = old.getSoLuong();
                soLuong++;
                GioHang gioHang = new GioHang(old.getMaGio(), old.getMaLaptop(), old.getMaKH(), old.getNgayThem(), soLuong);
                gioHangDAO.updateGioHang(gioHang);
                listGio = gioHangDAO.selectGioHang(null, null, null, null);
                setRow(pos, author, "up");
            }
        });

        author.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang gio = listGio.get(pos);
                gioHangDAO.deleteGioHang(gio);
            }
        });

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (laptop != null) {
                    Intent intent = new Intent(context, Info_Laptop_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("laptop", laptop);
                    Log.d(TAG, "onBindViewHolder: Laptop: " + laptop.toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGio.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLaptop, tang, giam;
        TextView name, gia, soLuong;
        ImageButton delete;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_Laptop);
            name = itemView.findViewById(R.id.textView_TenLaptop);
            gia = itemView.findViewById(R.id.textView_GiaTien);
            soLuong = itemView.findViewById(R.id.textView_Soluong);
            delete = itemView.findViewById(R.id.imageView_Delete);
            tang = itemView.findViewById(R.id.imageButton_Tang);
            giam = itemView.findViewById(R.id.imageButton_Giam);
        }
    }

    public String setTotal(int giaTien, String change){
        if (change.equals("down")){
            total = total - giaTien;
        } else {
            total = total + giaTien;
        }
        Log.d(TAG, "setTotal: total: " + total);
        Log.d(TAG, "setTotal: total String: " + changeType.intMoneyToString(total));
        return changeType.intMoneyToString(total);
    }

    public Laptop setRow(int pos, @NonNull KH_GioHang_Adapter.AuthorViewHolder author, String change) {
        Log.d(TAG, "setRow: " + pos);
        GioHang gioHang = listGio.get(pos);
        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", new byte[]{});
        Log.d(TAG, "setRow: GioHang: " + gioHang.toString());

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (gioHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        int giaTien = changeType.stringMoneyToInt(laptop.getGiaTien());
        int tongTien = giaTien * gioHang.getSoLuong();

        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(changeType.intMoneyToString(tongTien));
        author.soLuong.setText(String.valueOf(gioHang.getSoLuong()));

        //Total
        totalTV = kh_gioHang_fragment.getActivity().findViewById(R.id.textView_Total);
        if (pos == 0){
            totalTV.setText("0₫");
        }

        if (change.equals("none")){
            totalTV.setText(setTotal(tongTien, change));
        } else {
            totalTV.setText(setTotal(giaTien, change));
        }

        return laptop;
    }
}