package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityNV_Admin.NV_DonHang_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.NAV_Adapter.NV_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class NV_DonHang_Loader extends AsyncTask<String, Void, ArrayList<DonHang>> {
    @SuppressLint("StaticFieldLeak")
    NV_DonHang_Activity nvDonHangActivity;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "NV_DonHang_Loader_____";
    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<DonHang> listDon = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public NV_DonHang_Loader(NV_DonHang_Activity nvDonHangActivity, Context context) {
        this.nvDonHangActivity = nvDonHangActivity;
        this.context = context;
    }

    @Override
    protected ArrayList<DonHang> doInBackground(String... strings) {

        laptopDAO = new LaptopDAO(context);
        donHangDAO = new DonHangDAO(context);
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        listDon = donHangDAO.selectDonHang(null, null, null, null);

        return listDon;
    }

    @Override
    protected void onPostExecute(ArrayList<DonHang> listDon) {
        super.onPostExecute(listDon);

        if (nvDonHangActivity != null){
            reView = nvDonHangActivity.findViewById(R.id.recyclerView_NV_DonHang);
            setupReView(listDon, reView);
        }
    }

    private void setupReView(ArrayList<DonHang> listDon, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        NV_DonHang_Adapter nv_donHang_adapter = new NV_DonHang_Adapter(listLap, listDon, context);
        recyclerView.setAdapter(nv_donHang_adapter);
    }

}
