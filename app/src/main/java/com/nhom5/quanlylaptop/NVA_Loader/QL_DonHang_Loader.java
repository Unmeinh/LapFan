package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_DonHang_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class QL_DonHang_Loader extends AsyncTask<String, Void, ArrayList<DonHang>> {
    @SuppressLint("StaticFieldLeak")
    QL_DonHang_Fragment qlDonHangFragment;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_DonHang_Loader_____";
    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    KhachHangDAO khachHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<DonHang> listDon = new ArrayList<>();
    ArrayList<KhachHang> listKH = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public QL_DonHang_Loader(QL_DonHang_Fragment qlDonHangFragment, Context context) {
        this.qlDonHangFragment = qlDonHangFragment;
        this.context = context;
    }

    @Override
    protected ArrayList<DonHang> doInBackground(String... strings) {

        laptopDAO = new LaptopDAO(context);
        donHangDAO = new DonHangDAO(context);
        khachHangDAO = new KhachHangDAO(context);
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        listDon = donHangDAO.selectDonHang(null, null, null, null);
        listKH = khachHangDAO.selectKhachHang(null, null, null, null);

        if (listDon != null){
            if (listDon.size() == 0){
                addDemoDH();
                listDon = donHangDAO.selectDonHang(null, null, null, null);
            }
        } else {
            addDemoDH();
            listDon = donHangDAO.selectDonHang(null, null, null, null);
        }

        return listDon;
    }

    @Override
    protected void onPostExecute(ArrayList<DonHang> listDon) {
        super.onPostExecute(listDon);

        if (qlDonHangFragment != null){
            reView = qlDonHangFragment.getActivity().findViewById(R.id.recyclerView_NVA_DonHang);
            setupReView(listDon, reView);
        }
    }

    private void setupReView(ArrayList<DonHang> listDon, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_DonHang_Adapter ql_donHang_adapter = new QL_DonHang_Adapter(listLap, listDon, listKH, context);
        recyclerView.setAdapter(ql_donHang_adapter);
    }

    private void addDemoDH(){
        DonHang dh1 = new DonHang("DH1", "NV1", "KH1", "LP2", "VOU1", "Rate1",
                "Nam Từ Liêm, Hà Nội", "19/11/2022", "Ví FPT Pay", "false", "63.570.000đ", 3);
        donHangDAO.insertDonHang(dh1);
        DonHang dh2 = new DonHang("DH2", "NV2", "KH0", "LP5", "VOU4", "Rate9",
                "Quận Thủ Đức, TP Hồ CHí Minh", "20/11/2022", "Thanh toán khi nhận hàng", "true", "65.180.000đ", 2);
        donHangDAO.insertDonHang(dh2);
        DonHang dh3 = new DonHang("DH3", "NV2", "KH0", "LP7", "VOU6", "Rate6",
                "Ngũ Hành Sơn, Đà Nẵng", "21/11/2022", "Ví FPT Pay", "true", "25.590.000đ", 1);
        donHangDAO.insertDonHang(dh3);
    }
}

