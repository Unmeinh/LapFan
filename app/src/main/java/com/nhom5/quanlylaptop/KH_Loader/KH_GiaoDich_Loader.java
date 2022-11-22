package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_ViTien_Activity;
import com.nhom5.quanlylaptop.ActivityNV_Admin.NV_DonHang_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.GiaoDichDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.GiaoDich;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.KH_Adapter.KH_GiaoDich_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.NV_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class KH_GiaoDich_Loader extends AsyncTask<String, Void, ArrayList<GiaoDich>> {
    @SuppressLint("StaticFieldLeak")
    KH_ViTien_Activity khViTienActivity;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "NV_DonHang_Loader_____";
    GiaoDichDAO giaoDichDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    ChangeType changeType = new ChangeType();

    public KH_GiaoDich_Loader(KH_ViTien_Activity khViTienActivity, Context context) {
        this.khViTienActivity = khViTienActivity;
        this.context = context;
    }

    @Override
    protected ArrayList<GiaoDich> doInBackground(String... strings) {
        giaoDichDAO = new GiaoDichDAO(context);
        String maVi = strings[0];

        ArrayList<GiaoDich> listGD = giaoDichDAO.selectGiaoDich(null, null, null, null);

        if (listGD != null){
            if (listGD.size() == 0){
                addDemoGD();
            }
        }

        return giaoDichDAO.selectGiaoDich(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<GiaoDich> listGD) {
        super.onPostExecute(listGD);

        if (khViTienActivity != null){
            reView = khViTienActivity.findViewById(R.id.recyclerView_GiaoDich);
            setupReView(listGD, reView);
        }
    }

    private void setupReView(ArrayList<GiaoDich> listGD, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_GiaoDich_Adapter kh_giaoDich_adapter = new KH_GiaoDich_Adapter(listGD, context);
        recyclerView.setAdapter(kh_giaoDich_adapter);
    }

    private void addDemoGD(){
        GiaoDich gd0 = new GiaoDich("GD0", "No Data", "Nạp tiền", "Nạp tiền vào ví FPT Pay",
                changeType.intMoneyToString(100000), "2022-11-20");
        giaoDichDAO.insertGiaoDich(gd0);

        GiaoDich gd1 = new GiaoDich("GD1", "No Data", "Nạp tiền", "Nạp tiền vào ví FPT Pay",
                changeType.intMoneyToString(1000000000), "2022-11-20");
        giaoDichDAO.insertGiaoDich(gd1);

        GiaoDich gd2 = new GiaoDich("GD2", "No Data", "Thanh toán đơn hàng", "Thanh toán đơn hàng Laptop...",
                changeType.intMoneyToString(47900000), "2022-11-22");
        giaoDichDAO.insertGiaoDich(gd2);

        GiaoDich gd3 = new GiaoDich("GD3", "No Data", "Thanh toán đơn hàng", "Thanh toán đơn hàng Laptop...",
                changeType.intMoneyToString(35500000), "2022-11-25");
        giaoDichDAO.insertGiaoDich(gd3);

        GiaoDich gd4 = new GiaoDich("GD4", "No Data", "Thanh toán đơn hàng", "Thanh toán đơn hàng Laptop Macbook",
                changeType.intMoneyToString(25900000), "2022-11-27");
        giaoDichDAO.insertGiaoDich(gd4);

        GiaoDich gd5 = new GiaoDich("GD5", "No Data", "Nạp tiền", "Nạp tiền vào ví FPT Pay",
                changeType.intMoneyToString(100000000), "2022-11-29");
        giaoDichDAO.insertGiaoDich(gd5);
    }

}
