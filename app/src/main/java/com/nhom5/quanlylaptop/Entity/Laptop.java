package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

import java.util.Arrays;

public class Laptop extends Binder {
    private String maLaptop;
    private String maHangLap;
    private String tenLaptop;
    private String thongSoKT;
    private String giaTien;
    private byte[] anhLaptop;

    public Laptop(String maLaptop, String maHangLap, String tenLaptop, String thongSoKT, String giaTien, byte[] anhLaptop) {
        this.maLaptop = maLaptop;
        this.maHangLap = maHangLap;
        this.tenLaptop = tenLaptop;
        this.thongSoKT = thongSoKT;
        this.giaTien = giaTien;
        this.anhLaptop = anhLaptop;
    }

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public String getMaHangLap() {
        return maHangLap;
    }

    public void setMaHangLap(String maHangLap) {
        this.maHangLap = maHangLap;
    }

    public String getTenLaptop() {
        return tenLaptop;
    }

    public void setTenLaptop(String tenLaptop) {
        this.tenLaptop = tenLaptop;
    }

    public String getThongSoKT() {
        return thongSoKT;
    }

    public void setThongSoKT(String thongSoKT) {
        this.thongSoKT = thongSoKT;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public byte[] getAnhLaptop() {
        return anhLaptop;
    }

    public void setAnhLaptop(byte[] anhLaptop) {
        this.anhLaptop = anhLaptop;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "maLaptop = '" + maLaptop + '\'' +
                ", maHangLap = '" + maHangLap + '\'' +
                ", tenLaptop = '" + tenLaptop + '\'' +
                ", thongSoKT = '" + thongSoKT + '\'' +
                ", giaTien = " + giaTien +
                ", anhLaptop = " + anhLaptop +
                " : " + Arrays.toString(anhLaptop) +
                '}';
    }
}
