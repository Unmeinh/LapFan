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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;
import com.nhom5.quanlylaptop.Support.GetData;

import java.util.ArrayList;

public class All_ThongBao_Adapter extends RecyclerView.Adapter<All_ThongBao_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<ThongBao> listTB;
    ChangeType changeType = new ChangeType();
    DonHangDAO donHangDAO;
    ThongBaoDAO thongBaoDAO;
    LaptopDAO laptopDAO;
    NhanVien nhanVien;
    Laptop laptop;
    GetData getData;
    String TAG = "KH_ThongBao_Adapter_____";

    public All_ThongBao_Adapter(ArrayList<ThongBao> listTB, Context context) {
        this.listTB = listTB;
        this.context = context;
        donHangDAO = new DonHangDAO(context);
        thongBaoDAO = new ThongBaoDAO(context);
        laptopDAO = new LaptopDAO(context);
        getData = new GetData(context);
        getUser();
    }

    @NonNull
    @Override
    public All_ThongBao_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_thongbao, vGroup, false);
        return new All_ThongBao_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull All_ThongBao_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        ThongBao thongBao = setRow(pos, author);
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

    public ThongBao setRow(int pos, @NonNull All_ThongBao_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        ThongBao thongBao = listTB.get(pos);
        author.title.setText(thongBao.getTitle());
        author.chiTiet.setText(thongBao.getChiTiet());
        author.date.setText(thongBao.getNgayTB());
        return thongBao;
    }

    private void getUser() {
        SharedPreferences pref = context.getSharedPreferences("Who_Login", MODE_PRIVATE);
        if (pref == null) {
            nhanVien = null;
        } else {
            String user = pref.getString("who", "");
            NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
            ArrayList<NhanVien> list = nhanVienDAO.selectNhanVien(null, "maNV=?", new String[]{user}, null);
            if (list.size() > 0) {
                nhanVien = list.get(0);
            }
        }
    }

}