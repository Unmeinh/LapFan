package com.nhom5.quanlylaptop.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nhom5.quanlylaptop.Database.QLLaptopDB;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class DonHangDAO {
    QLLaptopDB qlLaptopDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "DonHangDAO_____";
    ChangeType changeType = new ChangeType();

    public DonHangDAO(Context context) {
        this.context = context;
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
    }

    public ArrayList selectDonHang(String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ArrayList<DonHang> listDH = new ArrayList<>();
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Cursor c = db.query("TB_DonHang", columns, selection, selectionArgs, null, null, orderBy);
        Log.d(TAG, "selectDonHang: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectDonHang: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectDonHang: Cursor not last");
                String maDH = c.getString(0);
                String maNV = c.getString(1);
                String maKH = c.getString(2);
                String maLaptop = c.getString(3);
                String maVoucher = c.getString(4);
                String maRate = c.getString(5);
                int soLuong = 0;
                try {
                    soLuong = Integer.parseInt(c.getString(6));
                } catch (Exception e){
                    e.printStackTrace();
                }
                String diaChi = c.getString(7);
                @SuppressLint("Range") String ngayMua = changeType.longDateToString(c.getLong(c.getColumnIndex("ngayMua")));
                String loaiThanhToan = c.getString(9);
                String isDanhGia = c.getString(10);
                String thanhTien = c.getString(11);
                DonHang newDH = new DonHang(maDH, maNV, maKH, maLaptop, maVoucher, maRate, diaChi, ngayMua, loaiThanhToan, isDanhGia, thanhTien, soLuong);
                Log.d(TAG, "selectDonHang: new DonHang: " + newDH.toString());

                listDH.add(newDH);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectDonHang: Cursor null");
        }
        db.close();

        return listDH;
    }

    public void insertDonHang(DonHang donHang) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", donHang.getMaDH());
        values.put("maNV", donHang.getMaNV());
        values.put("maKH", donHang.getMaKH());
        values.put("maLaptop", donHang.getMaLaptop());
        values.put("maVoucher", donHang.getMaVoucher());
        values.put("maRate", donHang.getMaRate());
        values.put("soLuong", donHang.getSoLuong());
        values.put("diaChi", donHang.getDiaChi());
        values.put("ngayMua", changeType.stringToLongDate(donHang.getNgayMua()));
        values.put("loaiThanhToan", donHang.getLoaiThanhToan());
        values.put("isDanhGia", donHang.getIsDanhGia());
        values.put("thanhTien", donHang.getThanhTien());
        Log.d(TAG, "insertDonHang: DonHang: " + donHang.toString());
        Log.d(TAG, "insertDonHang: Values: " + values);

        long ketqua = db.insert("TB_DonHang", null, values);
        if (ketqua > 0) {
            Log.d(TAG, "insertDonHang: Thêm thành công");
        } else {
            Log.d(TAG, "insertDonHang: Thêm thất bại");
        }
        db.close();
    }

    public void updateDonHang(DonHang donHang) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDH", donHang.getMaDH());
        values.put("maNV", donHang.getMaNV());
        values.put("maKH", donHang.getMaKH());
        values.put("maLaptop", donHang.getMaLaptop());
        values.put("maVoucher", donHang.getMaVoucher());
        values.put("maRate", donHang.getMaRate());
        values.put("soLuong", donHang.getSoLuong());
        values.put("diaChi", donHang.getDiaChi());
        values.put("ngayMua", changeType.stringToLongDate(donHang.getNgayMua()));
        values.put("loaiThanhToan", donHang.getLoaiThanhToan());
        values.put("isDanhGia", donHang.getIsDanhGia());
        values.put("thanhTien", donHang.getThanhTien());
        Log.d(TAG, "updateDonHang: DonHang: " + donHang.toString());
        Log.d(TAG, "updateDonHang: Values: " + values);

        long ketqua = db.update("TB_DonHang", values, "maDH=?", new String[]{String.valueOf(donHang.getMaDH())});
        if (ketqua > 0) {
            Log.d(TAG, "updateDonHang: Sửa thành công");
        } else {
            Log.d(TAG, "updateDonHang: Sửa thất bại");
        }
        db.close();
    }

    public void deleteDonHang(DonHang donHang){
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Log.d(TAG, "deleteDonHang: DonHang: " + donHang.toString());

        long ketqua = db.delete("TB_DonHang", "maDH=?", new String[]{String.valueOf(donHang.getMaDH())});
        if (ketqua > 0) {
            Log.d(TAG, "deleteDonHang: Xóa thành công");
        } else {
            Log.d(TAG, "deleteDonHang: Xóa thất bại");
        }
        db.close();
    }
}
