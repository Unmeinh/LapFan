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

        xacNhanDatHang(author, thongBao);
        xacNhanDonHang(author, thongBao);
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

    private void xacNhanDatHang(@NonNull All_ThongBao_Adapter.AuthorViewHolder author, ThongBao thongBao) {
        if (thongBao.getTitle().matches("Xác nhận đặt hàng.*")) {
            author.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String maDH = String.valueOf(changeType.stringMoneyToInt(thongBao.getTitle()));
                    ArrayList<DonHang> list = donHangDAO.selectDonHang(null, "maDH=?", new String[]{maDH}, null);
                    if (list.size() > 0) {
                        DonHang donHang = list.get(0);
                        if (donHang.getMaNV().equals("Null")) {
                            ArrayList<Laptop> listLap = laptopDAO.selectLaptop(null, "maLaptop=?", new String[]{donHang.getMaLaptop()}, null);
                            if (listLap.size() > 0){
                                laptop = listLap.get(0);
                            }

                            donHang.setMaNV("No Data");
                            int check = donHangDAO.updateDonHang(donHang);
                            if (check == 1) {
                                Toast.makeText(context, "Xác nhận đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                                ThongBao thongBaoAD = new ThongBao("TB", "ad", thongBao.getTitle(),
                                        " Bạn đã xác nhận đơn hàng " + donHang.getMaDH()
                                                + "\n Đơn hàng đã được chuyển đến cho nhân viên", getData.getNowDateSQL());
                                thongBaoDAO.insertThongBao(thongBaoAD, "ad");
                                if (laptop != null){
                                    ThongBao thongBaoNV = new ThongBao("TB", "No Data", "Xác nhận đơn hàng " + donHang.getMaDH(),
                                            " Đơn hàng " + laptop.getTenLaptop() + " đang chờ được xác nhận\n Ấn vào để xác nhận đơn hàng", getData.getNowDateSQL());
                                    thongBaoDAO.insertThongBao(thongBaoNV, "nv");
                                } else {
                                    ThongBao thongBaoNV = new ThongBao("TB", "No Data", "Xác nhận đơn hàng " + donHang.getMaDH(),
                                            " Đơn hàng No Data"+ " đang chờ được xác nhận\n Ấn vào để xác nhận đơn hàng", getData.getNowDateSQL());
                                    thongBaoDAO.insertThongBao(thongBaoNV, "nv");
                                }
                            } else {
                                Toast.makeText(context, "Xác nhận đặt hàng thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "Đơn hàng đã được xác nhận!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    private void xacNhanDonHang(@NonNull All_ThongBao_Adapter.AuthorViewHolder author, ThongBao thongBao) {
        if (thongBao.getTitle().matches("Xác nhận đơn hàng.*")) {
            author.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String maDH = String.valueOf(changeType.stringMoneyToInt(thongBao.getTitle()));
                    ArrayList<DonHang> list = donHangDAO.selectDonHang(null, "maDH=?", new String[]{maDH}, null);
                    if (list.size() > 0) {
                        DonHang donHang = list.get(0);
                        if (donHang.getMaNV().equals("No Data")) {
                            if (nhanVien != null){
                                ArrayList<Laptop> listLap = laptopDAO.selectLaptop(null, "maLaptop=?", new String[]{donHang.getMaLaptop()}, null);
                                if (listLap.size() > 0){
                                    laptop = listLap.get(0);
                                }

                                donHang.setMaNV(nhanVien.getMaNV());
                                donHang.setTrangThai("Đang giao");
                                int check = donHangDAO.updateDonHang(donHang);
                                if (check == 1) {
                                    Toast.makeText(context, "Xác nhận đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                                    ThongBao thongBaoNV = new ThongBao(thongBao.getMaTB(), nhanVien.getMaNV(), thongBao.getTitle(), " Bạn đã xác nhận đơn hàng "
                                            + donHang.getMaDH() + ", đơn hàng đã được lưu trong Đơn hàng đã bán", getData.getNowDateSQL());
                                    thongBaoDAO.updateThongBao(thongBaoNV, "nv");
                                    if (laptop != null){
                                        ThongBao thongBaoKH = new ThongBao("TB", donHang.getMaKH(), "Đơn hàng đã được xác nhận", " Đơn hàng "
                                                + laptop.getTenLaptop() + " đã được xác nhận\n Hãy vào Đơn hàng đã mua để xem đơn hàng nhé", getData.getNowDateSQL());
                                        thongBaoDAO.insertThongBao(thongBaoKH, "kh");
                                    } else {
                                        ThongBao thongBaoKH = new ThongBao("TB", donHang.getMaKH(), "Đơn hàng đã được xác nhận",
                                                " Đơn hàng No data" + " đã được xác nhận\n Hãy vào Đơn hàng đã mua để xem đơn hàng nhé", getData.getNowDateSQL());
                                        thongBaoDAO.insertThongBao(thongBaoKH, "kh");
                                    }
                                } else {
                                    Toast.makeText(context, "Xác nhận đơn hàng thất bại!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(context, "Đơn hàng đã được xác nhận!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}