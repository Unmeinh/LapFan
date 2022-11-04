package com.nhom5.quanlylaptop.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nhom5.quanlylaptop.Database.QLLaptopDB;
import com.nhom5.quanlylaptop.Entity.HoaDon;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class HoaDonDAO {
    QLLaptopDB qlLaptopDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "HoaDonDAO_____";
    ChangeType changeType = new ChangeType();

    public HoaDonDAO(Context context) {
        this.context = context;
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
    }

    public ArrayList selectHoaDon(String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ArrayList<HoaDon> listHD = new ArrayList<>();
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Cursor c = db.query("TB_HoaDon", columns, selection, selectionArgs, null, null, orderBy);
        Log.d(TAG, "selectHoaDon: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectHoaDon: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectHoaDon: Cursor not last");
                String maHD = c.getString(0);
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
                float thanhTien = 0;
                try {
                    thanhTien = Float.parseFloat(c.getString(10));
                } catch (Exception e){
                    e.printStackTrace();
                }
                HoaDon newHD = new HoaDon(maHD, maNV, maKH, maLaptop, maVoucher, maRate, diaChi, ngayMua, loaiThanhToan, soLuong, thanhTien);
                Log.d(TAG, "selectHoaDon: new HoaDon: " + newHD.toString());

                listHD.add(newHD);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectHoaDon: Cursor null");
        }
        db.close();

        return listHD;
    }

    public void insertHoaDon(HoaDon hoaDon) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHD", hoaDon.getMaHD());
        values.put("maNV", hoaDon.getMaNV());
        values.put("maKH", hoaDon.getMaKH());
        values.put("maLaptop", hoaDon.getMaLaptop());
        values.put("maVoucher", hoaDon.getMaVoucher());
        values.put("maRate", hoaDon.getMaRate());
        values.put("soLuong", hoaDon.getSoLuong());
        values.put("diaChi", hoaDon.getDiaChi());
        values.put("ngayMua", changeType.stringToLongDate(hoaDon.getNgayMua()));
        values.put("loaiThanhToan", hoaDon.getLoaiThanhToan());
        values.put("thanhTien", hoaDon.getThanhTien());
        Log.d(TAG, "insertHoaDon: HoaDon: " + hoaDon.toString());
        Log.d(TAG, "insertHoaDon: Values: " + values);

        long ketqua = db.insert("TB_HoaDon", null, values);
        if (ketqua > 0) {
            Log.d(TAG, "insertHoaDon: Thêm thành công");
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "insertHoaDon: Thêm thất bại");
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void updateHoaDon(HoaDon hoaDon) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maHD", hoaDon.getMaHD());
        values.put("maNV", hoaDon.getMaNV());
        values.put("maKH", hoaDon.getMaKH());
        values.put("maLaptop", hoaDon.getMaLaptop());
        values.put("maVoucher", hoaDon.getMaVoucher());
        values.put("maRate", hoaDon.getMaRate());
        values.put("soLuong", hoaDon.getSoLuong());
        values.put("diaChi", hoaDon.getDiaChi());
        values.put("ngayMua", changeType.stringToLongDate(hoaDon.getNgayMua()));
        values.put("loaiThanhToan", hoaDon.getLoaiThanhToan());
        values.put("thanhTien", hoaDon.getThanhTien());
        Log.d(TAG, "updateHoaDon: HoaDon: " + hoaDon.toString());
        Log.d(TAG, "updateHoaDon: Values: " + values);

        long ketqua = db.update("TB_HoaDon", values, "maHD=?", new String[]{String.valueOf(hoaDon.getMaHD())});
        if (ketqua > 0) {
            Log.d(TAG, "updateHoaDon: Sửa thành công");
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "updateHoaDon: Sửa thất bại");
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void deleteHoaDon(HoaDon hoaDon){
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Log.d(TAG, "deleteHoaDon: HoaDon: " + hoaDon.toString());

        long ketqua = db.delete("TB_HoaDon", "maHD=?", new String[]{String.valueOf(hoaDon.getMaHD())});
        if (ketqua > 0) {
            Log.d(TAG, "deleteHoaDon: Xóa thành công");
            Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "deleteHoaDon: Xóa thất bại");
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
