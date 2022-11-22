package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_NhanVien_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class QL_NhanVien_Loader extends AsyncTask<String, Void, ArrayList<NhanVien>> {
    @SuppressLint("StaticFieldLeak")
    QL_NhanVien_Fragment qlNhanVienFragment;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_NhanVien_Loader_____";
    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> listNV = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    TextView countNV;

    public QL_NhanVien_Loader(QL_NhanVien_Fragment qlNhanVienFragment, Context context) {
        this.qlNhanVienFragment = qlNhanVienFragment;
        this.context = context;
    }

    @Override
    protected ArrayList<NhanVien> doInBackground(String... strings) {
        nhanVienDAO = new NhanVienDAO(context);

        return nhanVienDAO.selectNhanVien(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<NhanVien> listNV) {
        super.onPostExecute(listNV);

        if (qlNhanVienFragment != null){
            countNV = qlNhanVienFragment.getActivity().findViewById(R.id.textView_Soluong);
            reView = qlNhanVienFragment.getActivity().findViewById(R.id.recyclerView_NVA_NhanVien);
            setupReView(listNV, reView);
        }
    }

    private void setupReView(ArrayList<NhanVien> listNV, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_NhanVien_Adapter ql_nhanVien_adapter = new QL_NhanVien_Adapter(listNV, context);
        recyclerView.setAdapter(ql_nhanVien_adapter);
        setCountKH(listNV);
    }

    private void setCountKH(ArrayList<NhanVien> listNV){
        countNV.setText(String.valueOf(listNV.size()));
    }

}

