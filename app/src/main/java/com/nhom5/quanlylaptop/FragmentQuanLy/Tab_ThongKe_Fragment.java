package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_ThongKe_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tab_ThongKe_Fragment extends Fragment {

    ArrayList<NhanVien> listNV = new ArrayList<>();
    NhanVienDAO nhanVienDAO;
    QL_ThongKe_Adapter ql_thongKe_adapter;
    RecyclerView recyclerView;
    String TAG = "Tab_ThongKe_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_thong_ke, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_ThongKe);

        AddData data = new AddData(getContext());
        nhanVienDAO = new NhanVienDAO(getContext());
        listNV = nhanVienDAO.selectNhanVien(null, null, null, null);
        data.addDataDoanhSo(listNV);

        setUpReView();
        return view;
    }

    private void setUpReView() {
        Log.d(TAG, "setUpReView: true");
        listNV.clear();
        listNV = nhanVienDAO.selectNhanVien(null, null, null, "doanhSo DESC");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ql_thongKe_adapter = new QL_ThongKe_Adapter(listNV, getContext());
        recyclerView.setAdapter(ql_thongKe_adapter);
    }

}