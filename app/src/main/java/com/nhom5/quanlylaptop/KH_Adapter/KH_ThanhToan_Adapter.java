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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_Voucher_Activity;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KH_ThanhToan_Adapter extends RecyclerView.Adapter<KH_ThanhToan_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Laptop> listLap;
    ArrayList<GioHang> listGio;
    String kieuTT;
    String TAG = "KH_Laptop_Adapter_____";
    ChangeType changeType = new ChangeType();
    int giaTien;
    String onWhat;

    public KH_ThanhToan_Adapter(ArrayList<Laptop> listLap, Context context, String onWhat) {
        this.listLap = listLap;
        this.context = context;
        this.kieuTT = "MuaNgay";
        this.onWhat = onWhat;
    }

    public KH_ThanhToan_Adapter(ArrayList<Laptop> listLap, ArrayList<GioHang> listGio, Context context, String onWhat) {
        this.context = context;
        this.listLap = listLap;
        this.listGio = listGio;
        this.kieuTT = "MuaGioHang";
        this.onWhat = onWhat;
    }

    @NonNull
    @Override
    public KH_ThanhToan_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_thanhtoan, vGroup, false);
        return new KH_ThanhToan_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_ThanhToan_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        if (onWhat.equals("onCreate")){
            if (kieuTT.equals("MuaNgay")) {
                setRowLP(pos, author);
            } else {
                setRowGH(pos, author);
            }
        } else {
            if (kieuTT.equals("MuaNgay")) {
                setDataLP(pos, author, giaTien);
            } else {
                setDataGH(pos, author, giaTien);
            }
        }

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
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (kieuTT.equals("MuaNgay")) {
            return 1;
        } else {
            return listGio.size();
        }
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLaptop;
        TextView name, gia, soLuong, date, sale, total, countSP;
        RelativeLayout clickVoucher;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_Laptop);
            name = itemView.findViewById(R.id.textView_TenLaptop);
            gia = itemView.findViewById(R.id.textView_GiaTien);
            soLuong = itemView.findViewById(R.id.textView_Soluong);
            date = itemView.findViewById(R.id.textView_Date);
            sale = itemView.findViewById(R.id.textView_GiamGia);
            total = itemView.findViewById(R.id.textView_SoTien);
            countSP = itemView.findViewById(R.id.textView_TongSP);
            clickVoucher = itemView.findViewById(R.id.onclick_Doi_Voucher);
        }
    }

    public Laptop setRowGH(int pos, @NonNull KH_ThanhToan_Adapter.AuthorViewHolder author) {
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
        giaTien = changeType.stringMoneyToInt(laptop.getGiaTien());
        giaTien = giaTien * gioHang.getSoLuong();
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

        author.date.setText(date);
        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(changeType.intMoneyToString(giaTien));
        author.soLuong.setText(String.valueOf(gioHang.getSoLuong()));
        author.total.setText(changeType.intMoneyToString(giaTien));
        author.countSP.setText("Tổng số tiền: (" + gioHang.getSoLuong() + " sản phẩm)");
        author.clickVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KH_Voucher_Activity.class);
                context.startActivity(intent);
            }
        });
        return laptop;
    }

    public void setRowLP(int pos, @NonNull KH_ThanhToan_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        Laptop laptop = listLap.get(pos);
        Log.d(TAG, "setRow: Laptop: " + laptop.toString());

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

        author.date.setText(date);
        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(laptop.getGiaTien());
        author.soLuong.setText("1");
        author.total.setText(laptop.getGiaTien());
        author.countSP.setText("Tổng số tiền: (1 sản phẩm)");
        author.clickVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KH_Voucher_Activity.class);
                context.startActivity(intent);
            }
        });
    }

    private void setDataGH(int pos, @NonNull KH_ThanhToan_Adapter.AuthorViewHolder author, int giaTien){
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
        giaTien = changeType.stringMoneyToInt(laptop.getGiaTien());
        giaTien = giaTien * gioHang.getSoLuong();
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

        author.date.setText(date);
        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(changeType.intMoneyToString(giaTien));
        author.soLuong.setText(String.valueOf(gioHang.getSoLuong()));
        author.total.setText(changeType.intMoneyToString(giaTien));
        author.countSP.setText("Tổng số tiền: (" + gioHang.getSoLuong() + " sản phẩm)");
        author.clickVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KH_Voucher_Activity.class);
                context.startActivity(intent);
            }
        });
        AddData addData = new AddData(context);
        String[] two = addData.getSetVoucher(author.sale, giaTien);
        author.total.setText(changeType.intMoneyToString(giaTien - changeType.stringMoneyToInt(two[1])));
    }

    public void setDataLP(int pos, @NonNull KH_ThanhToan_Adapter.AuthorViewHolder author, int giaTien){
        Log.d(TAG, "setRow: " + pos);
        Laptop laptop = listLap.get(pos);
        Log.d(TAG, "setRow: Laptop: " + laptop.toString());

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

        author.date.setText(date);
        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(laptop.getGiaTien());
        author.soLuong.setText("1");
        author.total.setText(laptop.getGiaTien());
        author.countSP.setText("Tổng số tiền: (1 sản phẩm)");
        author.clickVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KH_Voucher_Activity.class);
                context.startActivity(intent);
            }
        });
        AddData addData = new AddData(context);
        String[] two = addData.getSetVoucher(author.sale, giaTien);
        author.total.setText(changeType.intMoneyToString(giaTien - changeType.stringMoneyToInt(two[1])));
    }

}