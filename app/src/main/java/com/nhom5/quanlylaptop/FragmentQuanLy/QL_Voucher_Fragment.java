package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Laptop_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.NVA_Loader.QL_Voucher_Loader;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_Voucher_Fragment extends Fragment {

    AppCompatButton addVoucher;
    ArrayList<Voucher> listVou = new ArrayList<>();
    VoucherDAO voucherDAO;
    String TAG = "QL_Voucher_Fragment_____";
    RecyclerView reView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_voucher, container, false);
        addVoucher = view.findViewById(R.id.button_Add_Voucher);
        reView = view.findViewById(R.id.recyclerView_NVA_Voucher);
        voucherDAO = new VoucherDAO(getContext());

        listVou = voucherDAO.selectVoucher(null, null, null, null);
        QL_Voucher_Loader ql_voucher_loader = new QL_Voucher_Loader(getContext(), reView);
        ql_voucher_loader.execute("");

        openDialog();
        return view;
    }

    private void openDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit_voucher, null);
        TextView titleDialog = view.findViewById(R.id.textView_Title_Dialog);
        TextInputLayout textInput_ID = view.findViewById(R.id.textInput_ID);
        TextInputLayout textInput_Name = view.findViewById(R.id.textInput_Name);
        TextInputLayout textInput_GiamGia = view.findViewById(R.id.textInput_GiamGia);
        TextInputLayout textInput_NSX = view.findViewById(R.id.textInput_NSX);
        TextInputLayout textInput_HSD = view.findViewById(R.id.textInput_HSD);
        AppCompatButton button = view.findViewById(R.id.button_Dialog);

        titleDialog.setText("Thêm Voucher");
        button.setText("Tạo mới");

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(view);
        addVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QL_Voucher_Loader ql_voucher_loader = new QL_Voucher_Loader(getContext(), reView);
                ql_voucher_loader.execute("");
            }
        });
    }
}