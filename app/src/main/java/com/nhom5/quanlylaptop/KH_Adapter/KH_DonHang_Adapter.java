package com.nhom5.quanlylaptop.KH_Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.Activity.ChiTiet_DonHang_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_DanhGia_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopRateDAO;
import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.LaptopRate;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;
import com.nhom5.quanlylaptop.Support.GetData;

import java.util.ArrayList;

public class KH_DonHang_Adapter extends RecyclerView.Adapter<KH_DonHang_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Laptop> listLap;
    ArrayList<DonHang> listDon;
    LaptopRateDAO laptopRateDAO;
    Laptop getLaptop;
    DonHangDAO donHangDAO;
    ChangeType changeType = new ChangeType();
    String TAG = "KH_DonHang_Adapter_____";

    public KH_DonHang_Adapter(ArrayList<Laptop> listLap, ArrayList<DonHang> listDon, Context context) {
        this.listLap = listLap;
        this.listDon = listDon;
        this.context = context;
        laptopRateDAO = new LaptopRateDAO(context);
        donHangDAO = new DonHangDAO(context);
    }

    @NonNull
    @Override
    public KH_DonHang_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_donhang_laptop, vGroup, false);
        return new KH_DonHang_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_DonHang_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        DonHang donHang = setRow(pos, author);

        if (donHang != null) {
            onclickDanhGia(author, donHang);
        } else {
            Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
        }

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (donHang != null) {
                    Intent intent = new Intent(context, ChiTiet_DonHang_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("donhang", donHang);
                    Log.d(TAG, "onBindViewHolder: DonHang: " + donHang.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("typeUser", "KH");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDon.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLaptop, imgTrangThai;
        TextView name, gia, soLuong, trangThai, tienDo, hint;
        RatingBar ratingBar;
        Button danhGia;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_Laptop);
            imgTrangThai = itemView.findViewById(R.id.imageView_TrangThai);
            name = itemView.findViewById(R.id.textView_TenLaptop);
            gia = itemView.findViewById(R.id.textView_GiaTien);
            soLuong = itemView.findViewById(R.id.textView_Soluong);
            danhGia = itemView.findViewById(R.id.button_Open_DanhGia);
            trangThai = itemView.findViewById(R.id.textView_TrangThai);
            tienDo = itemView.findViewById(R.id.textView_TienDo);
            hint = itemView.findViewById(R.id.textView_Hint);
            ratingBar = itemView.findViewById(R.id.ratingBar_DanhGia);
        }
    }

    public DonHang setRow(int pos, @NonNull KH_DonHang_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        DonHang donHang = listDon.get(pos);
        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", 0, 0, new byte[]{});
        Log.d(TAG, "setRow: DonHang: " + donHang.toString());

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (donHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());

        setDonHangDanhGia(author, donHang);
        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(donHang.getThanhTien());
        author.soLuong.setText(String.valueOf(donHang.getSoLuong()));
        return donHang;
    }

    private void setDonHangDanhGia(@NonNull KH_DonHang_Adapter.AuthorViewHolder author, DonHang donHang) {
        if (donHang.getTrangThai().equals("Hoàn thành")) {
            author.tienDo.setText("Hoàn thành");
            author.imgTrangThai.setImageResource(R.drawable.check_icon);
            author.imgTrangThai.setColorFilter(Color.parseColor("#4CAF50"));
            author.trangThai.setText("Đơn hàng giao thành công");
            author.trangThai.setTextColor(Color.parseColor("#C93852B0"));
        } else if (donHang.getTrangThai().equals("Đang chờ")) {
            author.tienDo.setText("Đang chờ");
            author.imgTrangThai.setImageResource(R.drawable.crossed_icon);
            author.imgTrangThai.setColorFilter(Color.parseColor("#FF9800"));
            author.trangThai.setText("Đơn hàng đang chờ xác nhận");
            author.trangThai.setTextColor(Color.parseColor("#FF9800"));
        } else {
            author.tienDo.setText("Đang giao");
            author.imgTrangThai.setImageResource(R.drawable.crossed_icon);
            author.imgTrangThai.setColorFilter(Color.parseColor("#FF9800"));
            author.trangThai.setText("Đơn hàng đang được giao");
            author.trangThai.setTextColor(Color.parseColor("#FF9800"));
        }

        if (donHang.getIsDanhGia().equals("false")) {
            author.hint.setVisibility(View.VISIBLE);
            author.ratingBar.setRating(0f);
            author.ratingBar.setIsIndicator(true);
        } else {
            ArrayList<LaptopRate> list = laptopRateDAO.selectLaptopRate(null, "maRate=?", new String[]{donHang.getMaRate()}, null);
            LaptopRate laptopRate = null;
            if (list.size() > 0) {
                Log.d(TAG, "setLayout: yo");
                laptopRate = list.get(0);
                Log.d(TAG, "setLayout: Laptop rate: " + laptopRate.toString());
            }
            if (laptopRate != null) {
                author.hint.setVisibility(View.GONE);
                author.ratingBar.setRating(changeType.getRatingFloat(laptopRate.getRating()));
                author.ratingBar.setIsIndicator(true);
            }
        }
    }

    private void onclickDanhGia(@NonNull KH_DonHang_Adapter.AuthorViewHolder author, DonHang donHang) {
        LaptopDAO laptopDAO = new LaptopDAO(context);
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context);
        GetData getData = new GetData(context);
        ArrayList<Laptop> list = laptopDAO.selectLaptop(null, "maLaptop=?", new String[]{donHang.getMaLaptop()}, null);
        if (list.size() > 0) {
            getLaptop = list.get(0);
        }

        if (donHang.getTrangThai().equals("Hoàn thành")) {
            if (donHang.getIsDanhGia().equals("true")) {
                author.danhGia.setText("Xem đánh giá");
                author.danhGia.setEnabled(true);
            } else {
                author.danhGia.setText("Đánh giá ngay");
                author.danhGia.setEnabled(true);
            }
            author.danhGia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, KH_DanhGia_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("donhang", donHang);
                    Log.d(TAG, "onBindViewHolder: DonHang: " + donHang.toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
        if (donHang.getTrangThai().equals("Đang chờ")) {
            author.danhGia.setText("Chờ xác nhận");
            author.danhGia.setEnabled(false);
        }
        if (donHang.getTrangThai().equals("Đang giao")) {
            author.danhGia.setText("Đã nhận hàng");
            author.danhGia.setEnabled(true);
            author.danhGia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    donHang.setTrangThai("Hoàn thành");
                    int check = donHangDAO.updateDonHang(donHang);
                    if (check == 1) {
                        Toast.makeText(context, "Xác nhận nhận hàng thành công", Toast.LENGTH_SHORT).show();
                        if (getLaptop != null) {
                            ThongBao thongBaoKH = new ThongBao("TB", donHang.getMaKH(), "Quản lý đơn hàng",
                                    " Đơn hàng " + getLaptop.getTenLaptop() + "đã được bạn xác nhận nhận hàng\n " +
                                            "Hãy đánh giá sớm để chúng tôi biết suy nghĩ của bạn vể sản phẩm của chúng tôi nhé", getData.getNowDateSQL());
                            thongBaoDAO.insertThongBao(thongBaoKH, "kh");
                        } else {
                            ThongBao thongBaoKH = new ThongBao("TB", donHang.getMaKH(), "Quản lý đơn hàng",
                                    " Đơn hàng Nota" + "đã được bạn xác nhận nhận hàng\n " +
                                            "Hãy đánh giá sớm để chúng tôi biết suy nghĩ của bạn vể sản phẩm của chúng tôi nhé", getData.getNowDateSQL());
                            thongBaoDAO.insertThongBao(thongBaoKH, "kh");
                        }
                    } else {
                        Toast.makeText(context, "Xác nhận nhận hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}