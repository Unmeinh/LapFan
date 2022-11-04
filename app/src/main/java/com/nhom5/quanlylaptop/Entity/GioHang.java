package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

public class GioHang extends Binder {
    private String maGio;
    private String maLaptop;
    private String maKH;
    private String ngayThem;

    public GioHang(String maGio, String maLaptop, String maKH, String ngayThem) {
        this.maGio = maGio;
        this.maLaptop = maLaptop;
        this.maKH = maKH;
        this.ngayThem = ngayThem;
    }

    public String getMaGio() {
        return maGio;
    }

    public void setMaGio(String maGio) {
        this.maGio = maGio;
    }

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(String ngayThem) {
        this.ngayThem = ngayThem;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "maGio = '" + maGio + '\'' +
                ", maLaptop = '" + maLaptop + '\'' +
                ", maKH = '" + maKH + '\'' +
                ", ngayThem = '" + ngayThem + '\'' +
                '}';
    }
}
