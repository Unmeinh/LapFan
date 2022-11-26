package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_Voucher_Activity;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Voucher_Fragment;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Voucher_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_Voucher_Loader extends AsyncTask<String, Void, ArrayList<Voucher>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "KH_Voucher_Loader_____";
    VoucherDAO voucherDAO;
    GioHangDAO gioHangDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    GioHang gioHang;
    String openFrom;
    int pos;

    public KH_Voucher_Loader(Context context, RecyclerView reView, String openFrom, int pos) {
        this.context = context;
        this.reView = reView;
        this.openFrom = openFrom;
        this.pos = pos;
    }

    @Override
    protected ArrayList<Voucher> doInBackground(String... strings) {
        voucherDAO = new VoucherDAO(context);

        if (openFrom.equals("ThanhToan")){
            if (pos != -1){
                gioHangDAO = new GioHangDAO(context);
                ArrayList<GioHang> list = gioHangDAO.selectGioHang(null, null, null, null);
                gioHang = list.get(pos);
            } else {
                gioHang = new GioHang("Null");
            }
        } else {
            gioHang = null;
        }

        return voucherDAO.selectVoucher(null, null, null, "ngayBD");
    }

    @Override
    protected void onPostExecute(ArrayList<Voucher> listVou) {
        super.onPostExecute(listVou);

        if (reView != null){
            setupReView(listVou, reView);
        }
    }

    private void setupReView(ArrayList<Voucher> listVou, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_Voucher_Adapter kh_voucher_adapter = new KH_Voucher_Adapter(listVou, context, gioHang);
        recyclerView.setAdapter(kh_voucher_adapter);
    }

}
