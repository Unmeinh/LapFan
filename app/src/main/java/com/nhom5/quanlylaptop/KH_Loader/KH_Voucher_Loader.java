package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_Voucher_Activity;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Voucher_Fragment;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Voucher_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_Voucher_Loader extends AsyncTask<String, Void, ArrayList<Voucher>> {
    @SuppressLint("StaticFieldLeak")
    KH_Voucher_Activity khVoucherActivity;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "KH_Voucher_Loader_____";
    VoucherDAO voucherDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public KH_Voucher_Loader(KH_Voucher_Activity khVoucherActivity, Context context) {
        this.khVoucherActivity = khVoucherActivity;
        this.context = context;
    }

    @Override
    protected ArrayList<Voucher> doInBackground(String... strings) {
        voucherDAO = new VoucherDAO(context);

        return voucherDAO.selectVoucher(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<Voucher> listVou) {
        super.onPostExecute(listVou);

        if (khVoucherActivity != null){
            reView = khVoucherActivity.findViewById(R.id.recyclerView_Voucher_KH);
            setupReView(listVou, reView);
        }
    }

    private void setupReView(ArrayList<Voucher> listVou, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_Voucher_Adapter kh_voucher_adapter = new KH_Voucher_Adapter(listVou, context);
        recyclerView.setAdapter(kh_voucher_adapter);
    }


}
