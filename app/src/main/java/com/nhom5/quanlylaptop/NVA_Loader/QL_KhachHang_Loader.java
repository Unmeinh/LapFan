package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_KhachHang_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_KhachHang_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class QL_KhachHang_Loader extends AsyncTask<String, Void, ArrayList<KhachHang>> {
    @SuppressLint("StaticFieldLeak")
    QL_KhachHang_Fragment qlKhachHangFragment;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_KhachHang_Loader_____";
    KhachHangDAO khachHangDAO;
    ArrayList<KhachHang> listKH = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    TextView countKH;

    public QL_KhachHang_Loader(QL_KhachHang_Fragment qlKhachHangFragment, Context context) {
        this.qlKhachHangFragment = qlKhachHangFragment;
        this.context = context;
    }

    @Override
    protected ArrayList<KhachHang> doInBackground(String... strings) {
        khachHangDAO = new KhachHangDAO(context);

        return khachHangDAO.selectKhachHang(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<KhachHang> listKH) {
        super.onPostExecute(listKH);

        if (qlKhachHangFragment != null){
            countKH = qlKhachHangFragment.getActivity().findViewById(R.id.textView_Soluong);
            reView = qlKhachHangFragment.getActivity().findViewById(R.id.recyclerView_NVA_KhachHang);
            setupReView(listKH, reView);
        }
    }

    private void setupReView(ArrayList<KhachHang> listKH, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_KhachHang_Adapter ql_khachHang_adapter = new QL_KhachHang_Adapter(listKH, context);
        recyclerView.setAdapter(ql_khachHang_adapter);
        setCountKH(listKH);
    }

    private void setCountKH(ArrayList<KhachHang> listKH){
        countKH.setText(String.valueOf(listKH.size()));
    }

}

