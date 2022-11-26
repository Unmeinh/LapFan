package com.nhom5.quanlylaptop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class QLLaptopDB extends SQLiteOpenHelper {

    static String DB_Name = "db.QLLaptop";
    static int VER = 1;
    String TAG = "QLLaptopDB_____";

    public QLLaptopDB(@Nullable Context context) {
        super(context, DB_Name, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: DB: " + DB_Name);
        // Bảng NhanVien
        String tableNhanVien = "CREATE TABLE NhanVien( maNV VARCHAR(15) PRIMARY KEY not null, avatar BLOB," +
                " hoNV TEXT not null, tenNV TEXT not null, gioiTinh TEXT not null, email VARCHAR(50) UNIQUE not null, matKhau TEXT not null," +
                " queQuan TEXT, phone TEXT, doanhSo INT, soSP INT)";

        // Bảng KhachHang
        String tableKhachHang = "CREATE TABLE KhachHang( maKH VARCHAR(15) PRIMARY KEY not null, avatar BLOB," +
                " hoKH TEXT not null, tenKH TEXT not null, gioiTinh TEXT not null, email VARCHAR(50) UNIQUE not null, matKhau TEXT not null," +
                " queQuan TEXT, phone TEXT, haveVi TEXT)";

        // Bảng HangLaptop
        String tableHangLaptop = "CREATE TABLE HangLaptop( maHangLap VARCHAR(15) PRIMARY KEY not null" +
                ", anhHang BLOB, tenHangLap TEXT not null)";

        // Bảng Voucher
        String tableVoucher = "CREATE TABLE Voucher( maVoucher VARCHAR(15) PRIMARY KEY not null," +
                " tenVoucher TEXT not null, giamGia TEXT not null, ngayBD DATE, ngayKT DATE)";

        // Bảng Laptop
        String tableLaptop = "CREATE TABLE Laptop( maLaptop VARCHAR(15) PRIMARY KEY not null," +
                " maHangLap VARCHAR(15) not null, anhLaptop BLOB," +
                " tenLaptop TEXT not null, thongSoKT TEXT, giaTien TEXT, soLuong INT, daBan INT," +
                " FOREIGN KEY(maHangLap) REFERENCES HangLaptop (maHangLap))";

        // Bảng LaptopRate
        String tableLaptopRate = "CREATE TABLE LaptopRate( maRate VARCHAR(15) PRIMARY KEY not null," +
                " maLaptop VARCHAR(15) not null, danhGia TEXT, rating FLOAT," +
                " FOREIGN KEY(maLaptop) REFERENCES Laptop (maLaptop))";

        // Bảng DonHang
        String tableDonHang = "CREATE TABLE DonHang( maDH INT PRIMARY KEY not null, maNV VARCHAR(15) not null," +
                " maKH VARCHAR(15) not null, maLaptop VARCHAR(15) not null, maVoucher VARCHAR(15) not null," +
                " maRate VARCHAR(15) not null, soLuong INT not null, diaChi TEXT, ngayMua DATE not null," +
                " loaiThanhToan TEXT, isDanhGia TEXT, thanhTien TEXT not null," +
                " FOREIGN KEY(maNV) REFERENCES NhanVien (maNV), FOREIGN KEY(maKH) REFERENCES KhachHang (maKH)," +
                " FOREIGN KEY(maLaptop) REFERENCES Laptop (maLaptop), FOREIGN KEY(maVoucher) REFERENCES Voucher (maVoucher)," +
                " FOREIGN KEY(maRate) REFERENCES LaptopRate (maRate))";

        // Bảng GioHang
        String tableGioHang = "CREATE TABLE GioHang( maGio VARCHAR(15) PRIMARY KEY not null," +
                " maLaptop VARCHAR(15) not null, maKH VARCHAR(15) not null, soLuong INT, ngayThem DATE, maVou TEXT," +
                " FOREIGN KEY(maLaptop) REFERENCES Laptop (maLaptop), FOREIGN KEY(maKH) REFERENCES KhachHang (maKH))";

        // Bảng KH_ThongBao
        String tableThongBaoKH = "CREATE TABLE KH_ThongBao( maTB VARCHAR(15) PRIMARY KEY not null," +
                " maKH VARCHAR(15) not null, title TEXT not null, chiTiet TEXT not null, ngayTB DATE," +
                " FOREIGN KEY(maKH) REFERENCES KhachHang (maKH))";

        // Bảng NV_ThongBao
        String tableThongBaoNV = "CREATE TABLE NV_ThongBao( maTB VARCHAR(15) PRIMARY KEY not null," +
                " maNV VARCHAR(15) not null, title TEXT not null, chiTiet TEXT not null, ngayTB DATE," +
                " FOREIGN KEY(maNV) REFERENCES NhanVien (maNV))";

        // Bảng AD_ThongBao
        String tableThongBaoAD = "CREATE TABLE AD_ThongBao( maTB VARCHAR(15) PRIMARY KEY not null," +
                " admin VARCHAR(15) not null, title TEXT not null, chiTiet TEXT not null, ngayTB DATE)";

        // Bảng DiaChi
        String tableDiaChi = "CREATE TABLE DiaChi( maDC VARCHAR(15) PRIMARY KEY not null," +
                " maKH VARCHAR(15) not null, tenNguoiNhan TEXT not null, phone TEXT not null," +
                " diaChi TEXT, thanhPho TEXT, quanHuyen TEXT, phuongXa TEXT," +
                " FOREIGN KEY(maKH) REFERENCES KhachHang (maKH))";

        // Bảng DiaChi
        String tableViTien = "CREATE TABLE ViTien( maVi VARCHAR(15) PRIMARY KEY not null," +
                " maKH VARCHAR(15) not null, soTien TEXT not null, nganHang TEXT," +
                " FOREIGN KEY(maKH) REFERENCES KhachHang (maKH))";

        String tableGiaoDich = "CREATE TABLE GiaoDich( maGD VARCHAR(15) PRIMARY KEY not null," +
                " maVi VARCHAR(15) not null, title TEXT, chiTiet TEXT, soTien TEXT, ngayGD DATE," +
                " FOREIGN KEY(maVi) REFERENCES ViTien (maVi))";

        //execSQL
        db.execSQL(tableNhanVien);
        db.execSQL(tableKhachHang);
        db.execSQL(tableHangLaptop);
        db.execSQL(tableVoucher);
        db.execSQL(tableLaptop);
        db.execSQL(tableLaptopRate);
        db.execSQL(tableGioHang);
        db.execSQL(tableThongBaoKH);
        db.execSQL(tableThongBaoNV);
        db.execSQL(tableThongBaoAD);
        db.execSQL(tableDiaChi);
        db.execSQL(tableViTien);
        db.execSQL(tableGiaoDich);
        db.execSQL(tableDonHang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
