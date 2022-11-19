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
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_KhachHang_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_NhanVien_Fragment extends Fragment {

    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> listNV = new ArrayList<>();
    QL_NhanVien_Adapter ql_nhanVien_adapter;
    RecyclerView recyclerView;
    TextView countNV;
    String TAG = "QL_NhanVien_Fragment_____";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_nhan_vien, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_NVA_NhanVien);
        countNV = view.findViewById(R.id.textView_Soluong);

        nhanVienDAO = new NhanVienDAO(getContext());
        setUpReView();
        return view;
    }

    private void setCountNV(){
        countNV.setText(String.valueOf(listNV.size()));
    }

    private void setUpReView() {
        Log.d(TAG, "setUpReView: true");
        listNV = nhanVienDAO.selectNhanVien(null, null, null, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ql_nhanVien_adapter = new QL_NhanVien_Adapter(listNV, getContext());
        recyclerView.setAdapter(ql_nhanVien_adapter);
        setCountNV();
    }

}