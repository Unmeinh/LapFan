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
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_ThongKe_Adapter;
import com.nhom5.quanlylaptop.NVA_Loader.QL_ThongKe_Loader;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tab_ThongKe_Fragment extends Fragment {

    String TAG = "Tab_ThongKe_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_thong_ke, container, false);
        RecyclerView reView = view.findViewById(R.id.recyclerView_ThongKe);
        LinearLayout linearLayout = view.findViewById(R.id.loadingView);
        QL_ThongKe_Loader qlThongKeLoader = new QL_ThongKe_Loader(getContext(), reView, linearLayout);
        qlThongKeLoader.execute("");
        return view;
    }

}