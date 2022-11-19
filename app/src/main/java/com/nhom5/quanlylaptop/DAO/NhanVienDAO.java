package com.nhom5.quanlylaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nhom5.quanlylaptop.Database.QLLaptopDB;
import com.nhom5.quanlylaptop.Entity.NhanVien;

import java.util.ArrayList;

public class NhanVienDAO {
    QLLaptopDB qlLaptopDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "NhanVienDAO_____";

    public NhanVienDAO(Context context) {
        this.context = context;
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
    }

    public ArrayList selectNhanVien(String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ArrayList<NhanVien> listNV = new ArrayList<>();
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Cursor c = db.query("TB_NhanVien", columns, selection, selectionArgs, null, null, orderBy);
        Log.d(TAG, "selectNhanVien: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectNhanVien: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectNhanVien: Cursor not last");
                String maNV = c.getString(0);
                byte[] avatar = c.getBlob(1);
                String hoNV = c.getString(2);
                String tenNV = c.getString(3);
                String gioiTinh = c.getString(4);
                String email = c.getString(5);
                String matKhau = c.getString(6);
                String queQuan = c.getString(7);
                String phone = c.getString(8);
                NhanVien newNV = new NhanVien(maNV, hoNV, tenNV, gioiTinh, email, matKhau, queQuan, phone, avatar);
                Log.d(TAG, "selectNhanVien: new NhanVien: " + newNV.toString());

                listNV.add(newNV);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectNhanVien: Cursor null");
        }
        db.close();

        return listNV;
    }

    public void insertNhanVien(NhanVien nhanVien) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNV", nhanVien.getMaNV());
        values.put("avatar", nhanVien.getAvatar());
        values.put("hoNV", nhanVien.getHoNV());
        values.put("tenNV", nhanVien.getTenNV());
        values.put("gioiTinh", nhanVien.getGioiTinh());
        values.put("email", nhanVien.getEmail());
        values.put("matKhau", nhanVien.getMatKhau());
        values.put("queQuan", nhanVien.getQueQuan());
        values.put("phone", nhanVien.getPhone());
        Log.d(TAG, "insertNhanVien: NhanVien: " + nhanVien.toString());
        Log.d(TAG, "insertNhanVien: Values: " + values);

        long ketqua = db.insert("TB_NhanVien", null, values);
        if (ketqua > 0) {
            Log.d(TAG, "insertNhanVien: Thêm thành công"); 
        } else {
            Log.d(TAG, "insertNhanVien: Thêm thất bại");  
        }
        db.close();
    }

    public void updateNhanVien(NhanVien nhanVien) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNV", nhanVien.getMaNV());
        values.put("avatar", nhanVien.getAvatar());
        values.put("hoNV", nhanVien.getHoNV());
        values.put("tenNV", nhanVien.getTenNV());
        values.put("gioiTinh", nhanVien.getGioiTinh());
        values.put("email", nhanVien.getEmail());
        values.put("matKhau", nhanVien.getMatKhau());
        values.put("queQuan", nhanVien.getQueQuan());
        values.put("phone", nhanVien.getPhone());
        Log.d(TAG, "insertNhanVien: NhanVien: " + nhanVien.toString());
        Log.d(TAG, "insertNhanVien: Values: " + values);

        long ketqua = db.update("TB_NhanVien", values, "maNV=?", new String[]{String.valueOf(nhanVien.getMaNV())});
        if (ketqua > 0) {
            Log.d(TAG, "updateVoucher: Sửa thành công"); 
        } else {
            Log.d(TAG, "updateVoucher: Sửa thất bại");  
        }
        db.close();
    }

    public void deleteNhanVien(NhanVien nhanVien){
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Log.d(TAG, "deleteNhanVien: NhanVien: " + nhanVien.toString());

        long ketqua = db.delete("TB_NhanVien", "maNV=?", new String[]{String.valueOf(nhanVien.getMaNV())});
        if (ketqua > 0) {
            Log.d(TAG, "deleteNhanVien: Xóa thành công"); 
        } else {
            Log.d(TAG, "deleteNhanVien: Xóa thất bại");  
        }
        db.close();
    }
}
