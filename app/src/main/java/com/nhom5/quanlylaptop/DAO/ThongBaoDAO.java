package com.nhom5.quanlylaptop.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nhom5.quanlylaptop.Database.QLLaptopDB;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class ThongBaoDAO {
    QLLaptopDB qlLaptopDB;
    SQLiteDatabase db;
    Context context;
    String TAG = "ThongBaoDAO_____";
    ChangeType changeType = new ChangeType();

    public ThongBaoDAO(Context context) {
        this.context = context;
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
    }

    public ArrayList selectThongBao(String[] columns, String selection, String[] selectionArgs, String orderBy, String table) {
        ArrayList<ThongBao> listTB = new ArrayList<>();
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Cursor c = null;
        if (table != null){
            if (table.equals("kh")){
                c = db.query("KH_ThongBao", columns, selection, selectionArgs, null, null, orderBy);
            } else if (table.equals("nv")){
                c = db.query("NV_ThongBao", columns, selection, selectionArgs, null, null, orderBy);
            } else if (table.equals("ad")){
                c = db.query("AD_ThongBao", columns, selection, selectionArgs, null, null, orderBy);
            }
        }
        Log.d(TAG, "selectThongBao: Cursor: " + c.toString());

        if (c.getCount() > 0) {
            Log.d(TAG, "selectThongBao: Cursor not null");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.d(TAG, "selectThongBao: Cursor not last");
                String maTB = c.getString(0)+"";
                String id = c.getString(1);
                String title = c.getString(2);
                String chiTiet = c.getString(3);
                @SuppressLint("Range") String ngayTB = changeType.longDateToString(c.getLong(c.getColumnIndex("ngayTB")));
                ThongBao newThongBao = new ThongBao(maTB, id, title, chiTiet, ngayTB);
                Log.d(TAG, "selectThongBao: new ThongBao: " + newThongBao.toString());

                listTB.add(newThongBao);
                c.moveToNext();
            }
            c.close();
        } else {
            Log.d(TAG, "selectThongBao: Cursor null");
        }
        db.close();

        return listTB;
    }

    public void insertThongBao(ThongBao thongBao, String table) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (table != null){
            if (table.equals("kh")){
                values.put("maKH", thongBao.getId());
            } else if (table.equals("nv")){
                values.put("maNV", thongBao.getId());
            } else if (table.equals("ad")){
                values.put("admin", thongBao.getId());
            }
        }
        values.put("title", thongBao.getTitle());
        values.put("chiTiet", thongBao.getChiTiet());
        values.put("ngayTB", changeType.stringToLongDate(thongBao.getNgayTB()));
        Log.d(TAG, "insertThongBao: ThongBao: " + thongBao.toString());
        Log.d(TAG, "insertThongBao: Values: " + values);

        long ketqua = 0;
        if (table != null){
            if (table.equals("kh")){
                ketqua = db.insert("KH_ThongBao", null, values);
            } else if (table.equals("nv")){
                ketqua = db.insert("NV_ThongBao", null, values);
            } else if (table.equals("ad")){
                ketqua = db.insert("AD_ThongBao", null, values);
            }
        }
        if (ketqua > 0) {
            Log.d(TAG, "insertThongBao: Thêm thành công"); 
        } else {
            Log.d(TAG, "insertThongBao: Thêm thất bại");  
        }
        db.close();
    }

    public void updateThongBao(ThongBao thongBao, String table) {
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTB", thongBao.getMaTB());
        if (table != null){
            if (table.equals("kh")){
                values.put("maKH", thongBao.getId());
            } else if (table.equals("nv")){
                values.put("maNV", thongBao.getId());
            } else if (table.equals("ad")){
                values.put("admin", thongBao.getId());
            }
        }
        values.put("title", thongBao.getTitle());
        values.put("chiTiet", thongBao.getChiTiet());
        values.put("ngayTB", changeType.stringToLongDate(thongBao.getNgayTB()));
        Log.d(TAG, "updateThongBao: ThongBao: " + thongBao.toString());
        Log.d(TAG, "updateThongBao: Values: " + values);

        long ketqua = 0;
        if (table != null){
            if (table.equals("kh")){
                ketqua = db.update("KH_ThongBao", values, "maTB=?", new String[]{String.valueOf(thongBao.getMaTB())});
            } else if (table.equals("nv")){
                ketqua = db.update("NV_ThongBao", values, "maTB=?", new String[]{String.valueOf(thongBao.getMaTB())});
            } else if (table.equals("ad")){
                ketqua = db.update("AD_ThongBao", values, "maTB=?", new String[]{String.valueOf(thongBao.getMaTB())});
            }
        }
        if (ketqua > 0) {
            Log.d(TAG, "updateThongBao: Sửa thành công"); 
        } else {
            Log.d(TAG, "updateThongBao: Sửa thất bại");  
        }
        db.close();
    }

    public void deleteThongBao(ThongBao thongBao, String table){
        qlLaptopDB = new QLLaptopDB(context);
        db = qlLaptopDB.getWritableDatabase();
        Log.d(TAG, "deleteThongBao: ThongBao: " + thongBao.toString());

        long ketqua = 0;
        if (table != null){
            if (table.equals("kh")){
                ketqua = db.delete("KH_ThongBao", "maTB=?", new String[]{String.valueOf(thongBao.getMaTB())});
            } else if (table.equals("nv")){
                ketqua = db.delete("NV_ThongBao", "maTB=?", new String[]{String.valueOf(thongBao.getMaTB())});
            } else if (table.equals("ad")){
                ketqua = db.delete("AD_ThongBao", "maTB=?", new String[]{String.valueOf(thongBao.getMaTB())});
            }
        }

        if (ketqua > 0) {
            Log.d(TAG, "deleteThongBao: Xóa thành công"); 
        } else {
            Log.d(TAG, "deleteThongBao: Xóa thất bại");  
        }
        db.close();
    }
}
