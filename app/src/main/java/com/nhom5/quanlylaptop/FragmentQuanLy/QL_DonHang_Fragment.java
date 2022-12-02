package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nhom5.quanlylaptop.Activity.DonHang_Manager_Activity;
import com.nhom5.quanlylaptop.NVA_Loader.QL_DonHang_Loader;
import com.nhom5.quanlylaptop.R;

public class QL_DonHang_Fragment extends Fragment {

    String TAG = "KH_DonHang_Activity_____";
    AppCompatButton addDHButton;
    RecyclerView recyclerView;
    TextView count;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout, linearDonHangEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_don_hang, container, false);
        addDHButton = view.findViewById(R.id.button_AddDH);
        recyclerView = view.findViewById(R.id.recyclerView_NVA_DonHang);
        count = view.findViewById(R.id.textView_Soluong);
        relativeLayout = view.findViewById(R.id.layoutView);
        linearLayout = view.findViewById(R.id.loadingView);
        linearDonHangEmpty = view.findViewById(R.id.linearDonHangEmpty);

        QL_DonHang_Loader ql_donHang_loader = new QL_DonHang_Loader(getContext(), recyclerView, count, linearLayout, linearDonHangEmpty, relativeLayout);
        ql_donHang_loader.execute("");

        addDonHang();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        QL_DonHang_Loader ql_donHang_loader = new QL_DonHang_Loader(getContext(), recyclerView, count, linearLayout, linearDonHangEmpty, relativeLayout);
        ql_donHang_loader.execute("");
    }

    private void addDonHang(){
        addDHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonHang_Manager_Activity.class);
                startActivity(intent);
            }
        });
    }

}