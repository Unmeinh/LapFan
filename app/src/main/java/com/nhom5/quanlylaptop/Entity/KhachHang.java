package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

import java.util.Arrays;

public class KhachHang extends Binder {
    private String maKH;
    private String hoTen;
    private String gioiTinh;
    private String email;
    private String matKhau;
    private byte[] avatar;

    public KhachHang(String maKH, String hoTen, String gioiTinh, String email, String matKhau, byte[] avatar) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.matKhau = matKhau;
        this.avatar = avatar;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH = '" + maKH + '\'' +
                ", hoTen = '" + hoTen + '\'' +
                ", gioiTinh = '" + gioiTinh + '\'' +
                ", email = '" + email + '\'' +
                ", matKhau = '" + matKhau + '\'' +
                ", avatar = " +  avatar +
                " : " + Arrays.toString(avatar) +
                '}';
    }
}
