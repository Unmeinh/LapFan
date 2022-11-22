package com.nhom5.quanlylaptop.FragmentKH;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhom5.quanlylaptop.KH_Loader.KH_ThongBao_Loader;
import com.nhom5.quanlylaptop.R;

public class KH_ThongBao_Fragment extends Fragment {

    String TAG = "KH_GioHang_Fragment_____";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kh_thongbao, container, false);

        KH_ThongBao_Loader kh_thongBao_loader = new KH_ThongBao_Loader(KH_ThongBao_Fragment.this, getContext(), view.findViewById(R.id.recyclerView_ThongBao));
        kh_thongBao_loader.execute("");

        return view;
    }

}