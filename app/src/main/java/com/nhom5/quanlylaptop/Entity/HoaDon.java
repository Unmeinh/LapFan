package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

public class HoaDon extends Binder {
    private String maHD;
    private String maNV;
    private String maKH;
    private String maLaptop;
    private String maVoucher;
    private String maRate;
    private String diaChi;
    private String ngayMua;
    private String loaiThanhToan;
    private String isDanhGia;
    private int soLuong;
    private double thanhTien;

    public HoaDon(String maHD, String maNV, String maKH, String maLaptop, String maVoucher, String maRate,
                  String diaChi, String ngayMua, String loaiThanhToan, String isDanhGia, int soLuong, double thanhTien) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maLaptop = maLaptop;
        this.maVoucher = maVoucher;
        this.maRate = maRate;
        this.diaChi = diaChi;
        this.ngayMua = ngayMua;
        this.loaiThanhToan = loaiThanhToan;
        this.isDanhGia = isDanhGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaLaptop() {
        return maLaptop;
    }

    public void setMaLaptop(String maLaptop) {
        this.maLaptop = maLaptop;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMaRate() {
        return maRate;
    }

    public void setMaRate(String maRate) {
        this.maRate = maRate;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public String getIsDanhGia() {
        return isDanhGia;
    }

    public void setIsDanhGia(String isDanhGia) {
        this.isDanhGia = isDanhGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHD = '" + maHD + '\'' +
                ", maNV = '" + maNV + '\'' +
                ", maKH = '" + maKH + '\'' +
                ", maLaptop = '" + maLaptop + '\'' +
                ", maVoucher = '" + maVoucher + '\'' +
                ", maRate = '" + maRate + '\'' +
                ", diaChi = '" + diaChi + '\'' +
                ", ngayMua = '" + ngayMua + '\'' +
                ", loaiThanhToan = '" + loaiThanhToan + '\'' +
                ", soLuong = " + soLuong +
                ", thanhTien = " + thanhTien +
                '}';
    }
}
