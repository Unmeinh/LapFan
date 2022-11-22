package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.FragmentQuanLy.Tab_ThongKe_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_ThongKe_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

import java.util.ArrayList;

public class QL_ThongKe_Loader extends AsyncTask<String, Void, ArrayList<NhanVien>> {
    @SuppressLint("StaticFieldLeak")
    Tab_ThongKe_Fragment tabThongKeFragment;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_ThongKe_Loader_____";
    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> listNV = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public QL_ThongKe_Loader(Tab_ThongKe_Fragment tabThongKeFragment, Context context) {
        this.tabThongKeFragment = tabThongKeFragment;
        this.context = context;
    }

    @Override
    protected ArrayList<NhanVien> doInBackground(String... strings) {
        AddData data = new AddData(context);
        nhanVienDAO = new NhanVienDAO(context);
        listNV = nhanVienDAO.selectNhanVien(null, null, null, null);
        data.addDataDoanhSo(listNV);

        listNV.clear();
        return nhanVienDAO.selectNhanVien(null, null, null, "doanhSo DESC");
    }

    @Override
    protected void onPostExecute(ArrayList<NhanVien> listNV) {
        super.onPostExecute(listNV);

        if (tabThongKeFragment != null){
            reView = tabThongKeFragment.getActivity().findViewById(R.id.recyclerView_ThongKe);
            setupReView(listNV, reView);
        }
    }

    private void setupReView(ArrayList<NhanVien> listNV, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_ThongKe_Adapter ql_thongKe_adapter = new QL_ThongKe_Adapter(listNV, context);
        recyclerView.setAdapter(ql_thongKe_adapter);
    }

}
