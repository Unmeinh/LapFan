package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.FragmentQuanLy.Tab_HangLaptop_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_HangLaptop_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_HangLaptop_Loader extends AsyncTask<String, Void, ArrayList<HangLaptop>> {
    @SuppressLint("StaticFieldLeak")
    GridView gridView;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "LaptopLoader_____";
    HangLaptopDAO hangLaptopDAO;
    ChangeType changeType = new ChangeType();

    public QL_HangLaptop_Loader(Context context, GridView gridView) {
        this.gridView = gridView;
        this.context = context;
    }

    @Override
    protected ArrayList<HangLaptop> doInBackground(String... strings) {

        hangLaptopDAO = new HangLaptopDAO(context);
        ArrayList<HangLaptop> listHang = hangLaptopDAO.selectHangLaptop(null, null, null, "maHangLap");

        if (listHang == null) {
            Log.d(TAG, "onCreateView: list null");
            addHangLaptop();
        } else {
            if (listHang.size() < 6) {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listHang.size());
                addHangLaptop();
            }
        }

        return hangLaptopDAO.selectHangLaptop(null, null, null, "maHangLap");
    }

    @Override
    protected void onPostExecute(ArrayList<HangLaptop> listHang) {
        super.onPostExecute(listHang);

        if (gridView != null){
            setupReView(listHang, gridView);
        }
    }

    private void setupReView(ArrayList<HangLaptop> listHang, GridView gridView) {
        QL_HangLaptop_Adapter ql_hangLaptop_adapter = new QL_HangLaptop_Adapter(listHang);
        gridView.setAdapter(ql_hangLaptop_adapter);
    }

    private void addHangLaptop() {
        Bitmap bmDell = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.img_laptop_dell);
        HangLaptop dell = new HangLaptop("LDell", "Laptop Dell",
                changeType.checkByteInput(changeType.bitmapToByte(bmDell)));
        hangLaptopDAO.insertHangLaptop(dell);

        Bitmap bmHp = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.img_laptop_hp);
        HangLaptop hp = new HangLaptop("LHP", "Laptop HP",
                changeType.checkByteInput(changeType.bitmapToByte(bmHp)));
        hangLaptopDAO.insertHangLaptop(hp);

        Bitmap bmAsus = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.img_laptop_asus);
        HangLaptop asus = new HangLaptop("LAsus", "Laptop Asus",
                changeType.checkByteInput(changeType.bitmapToByte(bmAsus)));
        hangLaptopDAO.insertHangLaptop(asus);

        Bitmap bmRazer = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.img_laptop_acer);
        HangLaptop razer = new HangLaptop("LAcer", "Laptop Acer",
                changeType.checkByteInput(changeType.bitmapToByte(bmRazer)));
        hangLaptopDAO.insertHangLaptop(razer);

        Bitmap bmSS = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.img_laptop_msi);
        HangLaptop ss = new HangLaptop("LMSi", "Laptop MSi",
                changeType.checkByteInput(changeType.bitmapToByte(bmSS)));
        hangLaptopDAO.insertHangLaptop(ss);

        Bitmap bmMac = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.img_macbook);
        HangLaptop mac = new HangLaptop("LMac", "MacBook",
                changeType.checkByteInput(changeType.bitmapToByte(bmMac)));
        hangLaptopDAO.insertHangLaptop(mac);
    }
}
