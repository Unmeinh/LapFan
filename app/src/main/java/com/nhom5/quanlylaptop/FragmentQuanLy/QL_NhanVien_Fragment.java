package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_KhachHang_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.NVA_Loader.QL_NhanVien_Loader;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_NhanVien_Fragment extends Fragment {

    String TAG = "QL_NhanVien_Fragment_____";
    RecyclerView reView;
    TextView countNV;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_nhan_vien, container, false);
        countNV = view.findViewById(R.id.textView_Soluong);
        reView = view.findViewById(R.id.recyclerView_NVA_NhanVien);
        linearLayout = view.findViewById(R.id.loadingView);
        relativeLayout = view.findViewById(R.id.layoutView);
        QL_NhanVien_Loader ql_nhanVien_loader = new QL_NhanVien_Loader(getContext(), reView, countNV, linearLayout, relativeLayout);
        ql_nhanVien_loader.execute("");
        return view;
    }

}