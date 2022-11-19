package com.nhom5.quanlylaptop.Support;

import android.content.Context;

import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.NhanVien;

import java.util.ArrayList;

public class AddData {
    Context context;
    NhanVienDAO nhanVienDAO;
    DonHangDAO donHangDAO;
    ChangeType changeType = new ChangeType();

    public AddData(Context context){
        this.context = context;
        nhanVienDAO = new NhanVienDAO(context);
        donHangDAO = new DonHangDAO(context);
    }


    public void addDataDoanhSo(ArrayList<NhanVien> listNV) {
        if (listNV != null){
            if (listNV.size() > 0){
                for (int i = 0; i < listNV.size(); i++) {
                    NhanVien nhanVien = listNV.get(i);
                    ArrayList<DonHang> listDon = donHangDAO.selectDonHang(null, "maNV=?", new String[]{nhanVien.getMaNV()}, null);

                    int doanhSo = 0;
                    int cout = 0;
                    if (listDon != null) {
                        if (listDon.size() > 0) {
                            for (int j = 0; j < listDon.size(); j++) {
                                doanhSo += changeType.stringMoneyToInt(listDon.get(j).getThanhTien());
                            }
                            cout = listDon.size();
                        }
                    }

                    NhanVien update = new NhanVien(nhanVien.getMaNV(), nhanVien.getHoNV(), nhanVien.getTenNV(), nhanVien.getGioiTinh(),
                            nhanVien.getEmail(), nhanVien.getMatKhau(), nhanVien.getQueQuan(), nhanVien.getPhone(),
                            changeType.intMoneyToString(doanhSo), cout, nhanVien.getAvatar());
                    nhanVienDAO.updateNhanVien(update);
                }
            }
        }
    }
}
