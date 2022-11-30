package com.nhom5.quanlylaptop.KH_Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_ThanhToan_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_Voucher_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.IdData;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;
import com.nhom5.quanlylaptop.Support.GetData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KH_ThanhToan_Adapter extends RecyclerView.Adapter<KH_ThanhToan_Adapter.AuthorViewHolder> {

    Context context;
    ArrayList<Laptop> listLap;
    ArrayList<GioHang> listGio;
    ArrayList<Voucher> listVou;
    String TAG = "KH_Laptop_Adapter_____";
    ChangeType changeType = new ChangeType();
    KH_ThanhToan_Activity khThanhToanActivity;
    TextView tongTienHang_View, voucherGiamGia_View, tongThanhToan_View, total_View;
    int giaTien, tongTienHang, tienGiamGia;
    KhachHang khachHang;
    ThongBaoDAO thongBaoDAO;
    GioHangDAO gioHangDAO;
    String maVou;
    GetData getData;

    public KH_ThanhToan_Adapter(ArrayList<Laptop> listLap, ArrayList<GioHang> listGio, ArrayList<Voucher> listVou,
                                Context context, KH_ThanhToan_Activity khThanhToanActivity) {
        this.context = context;
        this.listLap = listLap;
        this.listGio = listGio;
        this.listVou = listVou;
        this.khThanhToanActivity = khThanhToanActivity;
        thongBaoDAO = new ThongBaoDAO(context);
        gioHangDAO = new GioHangDAO(context);
        getData = new GetData(context);
        getUserKH();
    }

    @NonNull
    @Override
    public KH_ThanhToan_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_kh_thanhtoan, vGroup, false);
        return new KH_ThanhToan_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KH_ThanhToan_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);
        if (khThanhToanActivity != null) {
            Button datHang = khThanhToanActivity.findViewById(R.id.button_DatHang);
            datHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickDatHang();
                }
            });
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
        return listGio.size();
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

    private void getUserKH() {
        SharedPreferences pref = context.getSharedPreferences("Who_Login", MODE_PRIVATE);
        if (pref == null) {
            khachHang = null;
        } else {
            String user = pref.getString("who", "");
            KhachHangDAO khachHangDAO = new KhachHangDAO(context);
            ArrayList<KhachHang> list = khachHangDAO.selectKhachHang(null, "maKH=?", new String[]{user}, null);
            if (list.size() > 0) {
                khachHang = list.get(0);
            }
        }
    }

    private void onclickDatHang() {
        if (listGio.size() > 0) {
            for (GioHang gio : listGio) {
                Laptop laptop = null;
                for (int i = 0; i < listLap.size(); i++) {
                    Laptop getLap = listLap.get(i);
                    if (gio.getMaLaptop().equals(getLap.getMaLaptop())) {
                        laptop = getLap;
                    }
                }
                Voucher voucher = null;
                for (int i = 0; i < listVou.size(); i++) {
                    Voucher getVou = listVou.get(i);
                    if (gio.getMaVou().equals(getVou.getMaVoucher())) {
                        voucher = getVou;
                    }
                }

                TextView textViewDC = khThanhToanActivity.findViewById(R.id.textView_DiaChi);
                TextView textViewHTTT = khThanhToanActivity.findViewById(R.id.textView_HinhThucTT);
                String diaChi = textViewDC.getText().toString();
                String httt = textViewHTTT.getText().toString();

                if (diaChi.isEmpty()) {
                    Toast.makeText(context, "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (httt.isEmpty()) {
                    Toast.makeText(context, "Hình thức thanh toán không được trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (laptop != null && voucher != null && khachHang != null) {
                    DonHang donHang = new DonHang("", "Null", khachHang.getMaKH(), laptop.getMaLaptop(),
                            voucher.getMaVoucher(), "No Data", diaChi, getData.getNowDateSQL(), httt, "Đang chờ",
                            "false", changeType.stringToStringMoney(giaTien + "000"), gio.getSoLuong());
                    DonHangDAO donHangDAO = new DonHangDAO(context);
                    int check = donHangDAO.insertDonHang(donHang);
                    int size = donHangDAO.selectDonHang(null, null, null, null).size();

                    if (check == 1) {
                        ThongBao thongBaoKH = new ThongBao("TB", khachHang.getMaKH(), "Quản lý đơn hàng",
                                "Bạn đã đặt đơn hàng " + laptop.getTenLaptop() + " với giá " + donHang.getThanhTien(), getData.getNowDateSQL());
                        thongBaoDAO.insertThongBao(thongBaoKH, "kh");
                        ThongBao thongBaoAD = new ThongBao("TB", "Null", "Xác nhận đặt hàng " + size,
                                " Khách hàng " + changeType.fullNameKhachHang(khachHang) + " đã đặt đơn hàng "
                                        + laptop.getTenLaptop() + " với giá " + donHang.getThanhTien() + "\n Ấn vào để xác nhận đặt hàng.", getData.getNowDateSQL());
                        thongBaoDAO.insertThongBao(thongBaoAD, "ad");
                    }
                }

                if (laptop != null && khachHang != null) {
                    DonHang donHang = new DonHang("", "Null", khachHang.getMaKH(), laptop.getMaLaptop(),
                            "Null", "No Data", diaChi, getData.getNowDateSQL(), httt, "Đang chờ",
                            "false", changeType.stringToStringMoney(giaTien + "000"), gio.getSoLuong());
                    DonHangDAO donHangDAO = new DonHangDAO(context);
                    int check = donHangDAO.insertDonHang(donHang);
                    int size = donHangDAO.selectDonHang(null, null, null, null).size();

                    if (check == 1) {
                        ThongBao thongBaoKH = new ThongBao("TB", khachHang.getMaKH(), "Quản lý đơn hàng",
                                " Bạn đã đặt đơn hàng " + laptop.getTenLaptop() + " với giá " + donHang.getThanhTien(), getData.getNowDateSQL());
                        thongBaoDAO.insertThongBao(thongBaoKH, "kh");
                        ThongBao thongBaoAD = new ThongBao("TB", "Null", "Xác nhận đặt hàng " + size,
                                " Khách hàng " + changeType.fullNameKhachHang(khachHang) + " đã đặt đơn hàng " + laptop.getTenLaptop()
                                        + " với giá " + donHang.getThanhTien() + "\n Ấn vào để xác nhận đặt hàng", getData.getNowDateSQL());
                        thongBaoDAO.insertThongBao(thongBaoAD, "ad");
                    }
                }

                for (GioHang gioHang : listGio) {
                    gioHangDAO.deleteGioHang(gioHang);
                }
                Toast.makeText(context, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                khThanhToanActivity.finish();
            }
        }
    }

    public Laptop setRow(int pos, @NonNull KH_ThanhToan_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        GioHang gioHang = listGio.get(pos);
        Log.d(TAG, "setRow: GioHang: " + gioHang.toString());

        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", 0, 0, new byte[]{});

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (gioHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        Voucher voucher = null;

        for (int i = 0; i < listVou.size(); i++) {
            Voucher getVou = listVou.get(i);
            if (gioHang.getMaVou().equals(getVou.getMaVoucher())) {
                voucher = getVou;
            }
        }

        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        giaTien = changeType.stringMoneyToInt(laptop.getGiaTien()) / 1000;
        giaTien = giaTien * gioHang.getSoLuong();
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

        author.date.setText(date);
        author.imgLaptop.setImageBitmap(anhLap);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText(changeType.stringToStringMoney(giaTien + "000"));
        author.soLuong.setText(String.valueOf(gioHang.getSoLuong()));
        author.total.setText(changeType.stringToStringMoney(giaTien + "000"));
        author.countSP.setText("Tổng số tiền: (" + gioHang.getSoLuong() + " sản phẩm)");

        if (voucher != null) {
            int sale = changeType.voucherToInt(voucher.getGiamGia());
            int giamTien = (giaTien * sale) / 100;
            int totalDh = giaTien - giamTien;

            Log.d(TAG, "setRow: giaTien = " + giaTien);
            Log.d(TAG, "setRow: giamTien = " + giamTien);
            Log.d(TAG, "setRow: totalDh = " + totalDh);
            author.sale.setText("-" + changeType.stringToStringMoney(giamTien + "000"));
            author.sale.setTextColor(Color.parseColor("#FF5722"));
            author.total.setText(changeType.stringToStringMoney(totalDh + "000"));
            setThanhToan(pos, giaTien, giamTien);
        } else {
            String[] two = getVoucher(author.sale, giaTien);
            maVou = two[0];
            int giamTien = changeType.stringMoneyToInt(two[1]);
            int tongTien = giaTien - giamTien;

            Log.d(TAG, "setRow: giaTien = " + giaTien);
            Log.d(TAG, "setRow: giamTien = " + giamTien);
            Log.d(TAG, "setRow: tongTien = " + tongTien);
            author.total.setText(changeType.stringToStringMoney(tongTien + "000"));
            setThanhToan(pos, giaTien, giamTien);
        }

        author.clickVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KH_Voucher_Activity.class);
                intent.putExtra("openFrom", "ThanhToan");
                if (!gioHang.getMaGio().equals("No Data")) {
                    intent.putExtra("posLap", pos);
                    IdData.getInstance().setIdVou(gioHang.getMaVou());
                } else {
                    intent.putExtra("posLap", -1);
                    IdData.getInstance().setIdVou(maVou);
                }
                context.startActivity(intent);
            }
        });

        return laptop;
    }

    private void setThanhToan(int pos, int tienHang, int giamTien) {
        tongTienHang_View = khThanhToanActivity.findViewById(R.id.textView_TienHang);
        voucherGiamGia_View = khThanhToanActivity.findViewById(R.id.textView_TienGiamGia);
        tongThanhToan_View = khThanhToanActivity.findViewById(R.id.textView_Total);
        total_View = khThanhToanActivity.findViewById(R.id.textView_Total2);

        if (pos == 0) {
            tongTienHang_View.setText("0₫");
            voucherGiamGia_View.setText("0₫");
            tongThanhToan_View.setText("0₫");
            total_View.setText("0₫");
        }

        tongTienHang += tienHang;
        tienGiamGia += giamTien;
        int tien = tongTienHang - tienGiamGia;

        tongTienHang_View.setText(changeType.stringToStringMoney(tongTienHang + "000"));
        voucherGiamGia_View.setText("-" + changeType.stringToStringMoney(tienGiamGia + "000"));
        tongThanhToan_View.setText(changeType.stringToStringMoney(tien + "000"));
        total_View.setText(changeType.stringToStringMoney(tien + "000"));
    }

    private String[] getVoucher(TextView textView, int giaTien) {
        String maVou = IdData.getInstance().getIdVou();
        String after;
        VoucherDAO voucherDAO = new VoucherDAO(context);
        ArrayList<Voucher> list = voucherDAO.selectVoucher(null, "maVoucher=?", new String[]{maVou}, null);

        if (list.size() > 0) {
            Voucher voucher = list.get(0);
            String giamGia = voucher.getGiamGia();
            if (!giamGia.equals("")) {
                int sale = changeType.voucherToInt(giamGia);
                int giamTien = (giaTien * sale) / 100;

                textView.setText("-" + changeType.stringToStringMoney(giamTien + "000"));
                after = changeType.stringToStringMoney(giamTien+"");
                textView.setTextColor(Color.parseColor("#FF5722"));
            } else {
                textView.setText(R.string.thay_i_m);
                after = changeType.stringToStringMoney("0");
            }
        } else {
            textView.setText(R.string.thay_i_m);
            after = changeType.stringToStringMoney("0");
        }

        return new String[]{maVou, after};
    }

}