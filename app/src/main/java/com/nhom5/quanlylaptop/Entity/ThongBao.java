package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

public class ThongBao extends Binder {
    private String maTB;
    private String maKH;
    private String chiTiet;
    private String ngayTB;

    public ThongBao(String maTB, String maKH, String chiTiet, String ngayTB) {
        this.maTB = maTB;
        this.maKH = maKH;
        this.chiTiet = chiTiet;
        this.ngayTB = ngayTB;
    }

    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getNgayTB() {
        return ngayTB;
    }

    public void setNgayTB(String ngayTB) {
        this.ngayTB = ngayTB;
    }

    @Override
    public String toString() {
        return "ThongBao{" +
                "maTB = '" + maTB + '\'' +
                ", maKH = '" + maKH + '\'' +
                ", chiTiet = '" + chiTiet + '\'' +
                ", ngayTB = '" + ngayTB + '\'' +
                '}';
    }
}
