package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

import java.util.Arrays;

public class Laptop extends Binder {
    private String maLaptop;
    private String maHangLap;
    private String maRate;
    private String tenLaptop;
    private String loaiLaptop;
    private String thongSoKT;
    private float giaTien;
    private byte[] anhLaptop;

    public Laptop(String maLaptop, String maHangLap, String maRate, String tenLaptop, String loaiLaptop, String thongSoKT, float giaTien, byte[] anhLaptop) {
        this.maLaptop = maLaptop;
        this.maHangLap = maHangLap;
        this.maRate = maRate;
        this.tenLaptop = tenLaptop;
        this.loaiLaptop = loaiLaptop;
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

    public String getMaRate() {
        return maRate;
    }

    public void setMaRate(String maRate) {
        this.maRate = maRate;
    }

    public String getTenLaptop() {
        return tenLaptop;
    }

    public void setTenLaptop(String tenLaptop) {
        this.tenLaptop = tenLaptop;
    }

    public String getLoaiLaptop() {
        return loaiLaptop;
    }

    public void setLoaiLaptop(String loaiLaptop) {
        this.loaiLaptop = loaiLaptop;
    }

    public String getThongSoKT() {
        return thongSoKT;
    }

    public void setThongSoKT(String thongSoKT) {
        this.thongSoKT = thongSoKT;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
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
                ", maRate = '" + maRate + '\'' +
                ", tenLaptop = '" + tenLaptop + '\'' +
                ", loaiLaptop = '" + loaiLaptop + '\'' +
                ", thongSoKT = '" + thongSoKT + '\'' +
                ", giaTien = " + giaTien +
                ", anhLaptop = " + anhLaptop +
                " : " + Arrays.toString(anhLaptop) +
                '}';
    }
}
