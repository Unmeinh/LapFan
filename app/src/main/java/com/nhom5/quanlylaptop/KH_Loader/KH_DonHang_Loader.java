package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_DonHang_Activity;
import com.nhom5.quanlylaptop.ActivityNV_Admin.NV_DonHang_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_DonHang_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.NV_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_DonHang_Loader extends AsyncTask<String, Void, ArrayList<DonHang>> {
    @SuppressLint("StaticFieldLeak")
    KH_DonHang_Activity khDonHangActivity;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "NV_DonHang_Loader_____";
    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public KH_DonHang_Loader(KH_DonHang_Activity khDonHangActivity, Context context) {
        this.khDonHangActivity = khDonHangActivity;
        this.context = context;
    }

    @Override
    protected ArrayList<DonHang> doInBackground(String... strings) {

        laptopDAO = new LaptopDAO(context);
        donHangDAO = new DonHangDAO(context);
        String maKH = "";
        listLap = laptopDAO.selectLaptop(null, null, null, null);

        return donHangDAO.selectDonHang(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<DonHang> listDon) {
        super.onPostExecute(listDon);

        if (khDonHangActivity != null){
            reView = khDonHangActivity.findViewById(R.id.recyclerView_KH_DonHang);
            setupReView(listDon, reView);
        }
    }

    private void setupReView(ArrayList<DonHang> listDon, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_DonHang_Adapter kh_donHang_adapter = new KH_DonHang_Adapter(listLap, listDon, context);
        recyclerView.setAdapter(kh_donHang_adapter);
    }

}
