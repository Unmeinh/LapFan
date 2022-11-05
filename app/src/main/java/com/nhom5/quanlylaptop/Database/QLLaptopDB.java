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
        String tableNhanVien = "CREATE TABLE TB_NhanVien( maNV VARCHAR(15) PRIMARY KEY not null, avatar BLOB," +
                " hoTen TEXT not null, gioiTinh TEXT not null, email VARCHAR(50) UNIQUE not null, matKhau TEXT not null)";

        // Bảng KhachHang
        String tableKhachHang = "CREATE TABLE TB_KhachHang( maKH VARCHAR(15) PRIMARY KEY not null, avatar BLOB," +
                " hoTen TEXT not null, gioiTinh TEXT not null, email VARCHAR(50) UNIQUE not null, matKhau TEXT not null)";

        // Bảng HangLaptop
        String tableHangLaptop = "CREATE TABLE TB_HangLaptop( maHangLap VARCHAR(15) PRIMARY KEY not null" +
                ", anhHang BLOB, tenHangLap TEXT not null)";

        // Bảng Voucher
        String tableVoucher = "CREATE TABLE TB_Voucher( maVoucher VARCHAR(15) PRIMARY KEY not null," +
                " tenVoucher TEXT not null, giamGia TEXT not null, ngayBD DATE, ngayKT DATE)";

        // Bảng Laptop
        String tableLaptop = "CREATE TABLE TB_Laptop( maLaptop VARCHAR(15) PRIMARY KEY not null," +
                " maHangLap VARCHAR(15) not null, maRate VARCHAR(15) not null, anhLaptop BLOB," +
                " tenLaptop TEXT not null, loaiLaptop TEXT not null, thongSoKT TEXT, giaTien MONEY," +
                " FOREIGN KEY(maHangLap) REFERENCES TB_HangLaptop (maHangLap)," +
                " FOREIGN KEY(maRate) REFERENCES TB_LaptopRate (maRate))";

        // Bảng LaptopRate
        String tableLaptopRate = "CREATE TABLE TB_LaptopRate( maRate VARCHAR(15) PRIMARY KEY not null," +
                " maLaptop VARCHAR(15) not null, danhGia TEXT, rating FLOAT," +
                " FOREIGN KEY(maLaptop) REFERENCES TB_Laptop (maLaptop))";

        // Bảng HoaDon
        String tableHoaDon = "CREATE TABLE TB_HoaDon( maHD INT PRIMARY KEY not null, maNV VARCHAR(15) not null," +
                " maKH VARCHAR(15) not null, maLaptop VARCHAR(15) not null, maVoucher VARCHAR(15) not null," +
                " maRate VARCHAR(15) not null, soLuong INT not null, diaChi TEXT, ngayMua DATE not null," +
                " loaiThanhToan TEXT, thanhTien MONEY not null," +
                " FOREIGN KEY(maNV) REFERENCES TB_NhanVien (maNV), FOREIGN KEY(maKH) REFERENCES TB_KhachHang (maKH)," +
                " FOREIGN KEY(maLaptop) REFERENCES TB_Laptop (maLaptop), FOREIGN KEY(maVoucher) REFERENCES TB_Voucher (maVoucher)," +
                " FOREIGN KEY(maRate) REFERENCES TB_LaptopRate (maRate))";

        // Bảng GioHang
        String tableGioHang = "CREATE TABLE TB_GioHang( maGio VARCHAR(15) PRIMARY KEY not null," +
                " maLaptop VARCHAR(15) not null, maKH VARCHAR(15) not null, ngayThem DATE," +
                " FOREIGN KEY(maLaptop) REFERENCES TB_Laptop (maLaptop), FOREIGN KEY(maKH) REFERENCES TB_KhachHang (maKH))";

        // Bảng ThongBao
        String tableThongBao = "CREATE TABLE TB_ThongBao( maTB VARCHAR(15) PRIMARY KEY not null," +
                " maKH VARCHAR(15) not null, chiTiet TEXT not null, ngayTB DATE," +
                " FOREIGN KEY(maKH) REFERENCES TB_KhachHang (maKH))";

        //execSQL
        db.execSQL(tableNhanVien);
        db.execSQL(tableKhachHang);
        db.execSQL(tableHangLaptop);
        db.execSQL(tableVoucher);
        db.execSQL(tableLaptop);
        db.execSQL(tableLaptopRate);
        db.execSQL(tableHoaDon);
        db.execSQL(tableGioHang);
        db.execSQL(tableThongBao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
