package com.nhom5.quanlylaptop.FragmentKH;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nhom5.quanlylaptop.KH_Loader.All_ThongBao_Loader;
import com.nhom5.quanlylaptop.R;

public class KH_ThongBao_Fragment extends Fragment {

    String TAG = "KH_GioHang_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_thongbao, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_ThongBao);
        LinearLayout linearLayout = view.findViewById(R.id.loadingView);

        All_ThongBao_Loader all_thongBao_loader = new All_ThongBao_Loader(getContext(),
                recyclerView, linearLayout, "kh");
        all_thongBao_loader.execute("");

        return view;
    }

}