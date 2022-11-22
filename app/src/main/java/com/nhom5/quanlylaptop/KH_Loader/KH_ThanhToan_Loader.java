package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_ThanhToan_Activity;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Voucher_Fragment;
import com.nhom5.quanlylaptop.KH_Adapter.KH_ThanhToan_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_ThanhToan_Loader extends AsyncTask<String, Void, ArrayList<GioHang>> {
    @SuppressLint("StaticFieldLeak")
    KH_ThanhToan_Activity khThanhToanActivity;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "KH_ThanhToan_Loader_____";
    GioHangDAO gioHangDAO;
    LaptopDAO laptopDAO;
    ArrayList<Laptop> listLap;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    String type;

    public KH_ThanhToan_Loader(KH_ThanhToan_Activity khThanhToanActivity, Context context, ArrayList<Laptop> listLap, String type) {
        this.khThanhToanActivity = khThanhToanActivity;
        this.context = context;
        this.listLap = listLap;
        this.type = type;
    }

    @Override
    protected ArrayList<GioHang> doInBackground(String... strings) {
        gioHangDAO = new GioHangDAO(context);
        laptopDAO = new LaptopDAO(context);
        String maKH = strings[0];

        if (type.equals("giohang")){
            listLap = laptopDAO.selectLaptop(null, null, null, null);
        }

        return gioHangDAO.selectGioHang(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<GioHang> listGio) {
        super.onPostExecute(listGio);

        if (khThanhToanActivity != null){
            reView = khThanhToanActivity.findViewById(R.id.recyclerView_DonHang);
            if (type.equals("giohang")){
                setupReViewGH(listGio, reView);
            } else {
                setUpReViewMN(reView);
            }
        }
    }

    private void setupReViewGH(ArrayList<GioHang> listGio, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_ThanhToan_Adapter kh_thanhToan_adapter = new KH_ThanhToan_Adapter(listLap, listGio, context);
        recyclerView.setAdapter(kh_thanhToan_adapter);
    }

    private void setUpReViewMN(RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_ThanhToan_Adapter kh_thanhToan_adapter = new KH_ThanhToan_Adapter(listLap, context);
        recyclerView.setAdapter(kh_thanhToan_adapter);
    }
}
