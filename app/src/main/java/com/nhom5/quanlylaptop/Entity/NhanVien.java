package com.nhom5.quanlylaptop.Entity;

import android.os.Binder;

import java.util.Arrays;

public class NhanVien extends Binder {
    private String maNV;
    private String hoTen;
    private String gioiTinh;
    private String email;
    private String matKhau;
    private String queQuan;
    private String phone;
    private byte[] avatar;

    public NhanVien(String maNV, String hoTen, String gioiTinh, String email, String matKhau, String queQuan, String phone, byte[] avatar) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.matKhau = matKhau;
        this.queQuan = queQuan;
        this.phone = phone;
        this.avatar = avatar;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV = '" + maNV + '\'' +
                ", hoTen = '" + hoTen + '\'' +
                ", gioiTinh = '" + gioiTinh + '\'' +
                ", email = '" + email + '\'' +
                ", matKhau = '" + matKhau + '\'' +
                ", queQuan = '" + queQuan + '\'' +
                ", phone = '" + phone + '\'' +
                ", avatar = " +  avatar +
                " : " + Arrays.toString(avatar) +
                '}';
    }
}
