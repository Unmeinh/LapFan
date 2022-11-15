package com.nhom5.quanlylaptop.BaseAdapter;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_Laptop_Adapter extends BaseAdapter {

    ArrayList<HangLaptop> listHang;
    ArrayList<Laptop> listLap;
    HangLaptopDAO hangLaptopDAO;
    LaptopDAO laptopDAO;
    String TAG = "QL_Laptop_Adapter_____";
    ImageView imgLaptop, imgHang;
    TextView name, gia, soLuong;
    ImageButton delete;

    public QL_Laptop_Adapter(ArrayList<HangLaptop> listHang, ArrayList<Laptop> listLap) {
        this.listHang = listHang;
        this.listLap = listLap;
    }

    @Override
    public int getCount() {
        int count = listLap.size();
        Log.d(TAG, "getCount: count: " + count);
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int pos, View v, ViewGroup vGroup) {
        View row = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.cardview_nva_laptop, vGroup, false);
        imgLaptop = row.findViewById(R.id.imageView_Laptop);
        imgHang = row.findViewById(R.id.imageView_HangLaptop);
        name = row.findViewById(R.id.textView_TenLaptop);
        gia = row.findViewById(R.id.textView_Soluong);
        soLuong = row.findViewById(R.id.textView_GiaTien);
        delete = row.findViewById(R.id.imageButton_Delete);

        hangLaptopDAO = new HangLaptopDAO(vGroup.getContext());
        setRow(pos);

        return row;
    }

    public void setRow(int pos) {
        Log.d(TAG, "setRow: " + pos);
        Laptop laptop = listLap.get(pos);
        HangLaptop hangLaptop = new HangLaptop("null", "null", new byte[]{});
        Log.d(TAG, "setRow: Laptop: " + laptop.toString());
//        byte[] avatar = bitmapToByte(bitmap);
//        avatar = checkByteInput(avatar);


        for (int i = 0; i < listHang.size(); i++) {
            HangLaptop getHang = listHang.get(i);
            if (laptop.getMaHangLap().equals(getHang.getMaHangLap())){
                hangLaptop = getHang;
            }
        }


        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        Bitmap anhHang = changeType.byteToBitmap(hangLaptop.getAnhHang());

        imgLaptop.setImageBitmap(anhLap);
        imgHang.setImageBitmap(anhHang);
        name.setText(laptop.getTenLaptop());
        gia.setText(String.valueOf(laptop.getGiaTien()));
        soLuong.setText("19");
    }
}
