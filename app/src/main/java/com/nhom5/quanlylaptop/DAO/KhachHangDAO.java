package com.nhom5.quanlylaptop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nhom5.quanlylaptop.Database.QLLaptopDB;
import com.nhom5.quanlylaptop.Entity.KhachHang;

import java.util.ArrayList;

public class KhachHangDAO {
    QLLaptopDB qlLaptopDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "KhachHangDAO_____";

    public KhachHangDAO(Context context) {
        this.context = context;
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
    }

    public ArrayList selectKhachHang(String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Cursor c = db.query("TB_KhachHang", columns, selection, selectionArgs, null, null, orderBy);
        Log.d(TAG, "selectKhachHang: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectKhachHang: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectKhachHang: Cursor not last");
                String maKH = c.getString(0);
                byte[] avatar = c.getBlob(1);
                String hoTen = c.getString(2);
                String gioiTinh = c.getString(3);
                String email = c.getString(4);
                String matKhau = c.getString(5);
                String queQuan = c.getString(6);
                String phone = c.getString(7);
                String haveVi = c.getString(8);
                KhachHang newKH = new KhachHang(maKH, hoTen, gioiTinh, email, matKhau, queQuan, phone, haveVi, avatar);
                Log.d(TAG, "selectKhachHang: new KhachHang: " + newKH.toString());

                listKH.add(newKH);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectKhachHang: Cursor null");
        }
        db.close();

        return listKH;
    }

    public void insertKhachHang(KhachHang khachHang) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", khachHang.getMaKH());
        values.put("avatar", khachHang.getAvatar());
        values.put("hoTen", khachHang.getHoTen());
        values.put("gioiTinh", khachHang.getGioiTinh());
        values.put("email", khachHang.getEmail());
        values.put("matKhau", khachHang.getMatKhau());
        values.put("queQuan", khachHang.getQueQuan());
        values.put("phone", khachHang.getPhone());
        values.put("haveVi", khachHang.getHaveVi());
        Log.d(TAG, "insertKhachHang: KhachHang: " + khachHang.toString());
        Log.d(TAG, "insertKhachHang: Values: " + values);

        long ketqua = db.insert("TB_KhachHang", null, values);
        if (ketqua > 0) {
            Log.d(TAG, "insertKhachHang: Thêm thành công"); 
        } else {
            Log.d(TAG, "insertKhachHang: Thêm thất bại");  
        }
        db.close();
    }

    public void updateKhachHang(KhachHang khachHang) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", khachHang.getMaKH());
        values.put("avatar", khachHang.getAvatar());
        values.put("hoTen", khachHang.getHoTen());
        values.put("gioiTinh", khachHang.getGioiTinh());
        values.put("email", khachHang.getEmail());
        values.put("matKhau", khachHang.getMatKhau());
        values.put("queQuan", khachHang.getQueQuan());
        values.put("phone", khachHang.getPhone());
        values.put("haveVi", khachHang.getHaveVi());
        Log.d(TAG, "updateKhachHang: KhachHang: " + khachHang.toString());
        Log.d(TAG, "updateKhachHang: Values: " + values);

        long ketqua = db.update("TB_KhachHang", values, "maKH=?", new String[]{String.valueOf(khachHang.getMaKH())});
        if (ketqua > 0) {
            Log.d(TAG, "updateKhachHang: Sửa thành công"); 
        } else {
            Log.d(TAG, "updateKhachHang: Sửa thất bại");  
        }
        db.close();
    }

    public void deleteKhachHang(KhachHang khachHang){
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Log.d(TAG, "deleteKhachHang: KhachHang: " + khachHang.toString());

        long ketqua = db.delete("TB_KhachHang", "maKH=?", new String[]{String.valueOf(khachHang.getMaKH())});
        if (ketqua > 0) {
            Log.d(TAG, "deleteKhachHang: Xóa thành công"); 
        } else {
            Log.d(TAG, "deleteKhachHang: Xóa thất bại");  
        }
        db.close();
    }
}
