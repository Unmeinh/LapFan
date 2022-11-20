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
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_Voucher_Fragment extends Fragment {


    AppCompatButton addVoucher;
    RecyclerView recyclerView;
    ArrayList<Voucher> listVou = new ArrayList<>();
    VoucherDAO voucherDAO;
    QL_Voucher_Adapter ql_voucher_adapter;
    String TAG = "QL_Voucher_Fragment_____";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_voucher, container, false);
        addVoucher = view.findViewById(R.id.button_Add_Voucher);
        recyclerView = view.findViewById(R.id.recyclerView_Voucher_NVA);
        voucherDAO = new VoucherDAO(getContext());

        listVou = voucherDAO.selectVoucher(null, null, null, null);
        setUpRecyclerView(getContext());

        openDialog();
        return view;
    }

    private void setUpRecyclerView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (listVou == null) {
            Log.d(TAG, "onCreateView: list null");
            addDemoVoucher();
            ql_voucher_adapter = new QL_Voucher_Adapter(listVou, context);
            recyclerView.setAdapter(ql_voucher_adapter);
        } else {
            if (listVou.size() == 0) {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listVou.size());
                addDemoVoucher();
                ql_voucher_adapter = new QL_Voucher_Adapter(listVou, context);
                recyclerView.setAdapter(ql_voucher_adapter);
            } else {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listVou.size());
                ql_voucher_adapter = new QL_Voucher_Adapter(listVou, context);
                recyclerView.setAdapter(ql_voucher_adapter);
            }
        }
    }

    private void addDemoVoucher() {
        Voucher vou1 = new Voucher("V0", "NOVEM1611", "20%", "2022-11-11", "2022-12-12");
        voucherDAO.insertVoucher(vou1);

        Voucher vou2 = new Voucher("V1", "DECEM1212", "12%", "2022-12-12", "2022-12-12");
        voucherDAO.insertVoucher(vou2);

        Voucher vou3 = new Voucher("V2", "NOEL2512", "25%", "2022-12-25", "2022-12-25");
        voucherDAO.insertVoucher(vou3);

        Voucher vou4 = new Voucher("V3", "LUNAR0101", "30%", "2022-01-01", "2022-01-01");
        voucherDAO.insertVoucher(vou4);

        Voucher vou5 = new Voucher("V4", "JANUA1501", "10%", "2022-01-15", "2023-02-01");
        voucherDAO.insertVoucher(vou5);

        Voucher vou6 = new Voucher("V5", "FEBRUA0202", "20%", "2023-02-02", "2023-03-03");
        voucherDAO.insertVoucher(vou6);

        Voucher vou7 = new Voucher("V6", "MARCH0303", "30%", "2023-03-03", "2023-04-04");
        voucherDAO.insertVoucher(vou7);

        Voucher vou8 = new Voucher("V7", "APRIL0104", "14%", "2023-04-01", "2023-04-02");
        voucherDAO.insertVoucher(vou8);

        Voucher vou9 = new Voucher("V8", "MAY1505", "25%", "2023-05-15", "2023-06-15");
        voucherDAO.insertVoucher(vou9);

        Voucher vou10 = new Voucher("V9", "SUM23", "23%", "2023-06-25", "2023-08-25");
        voucherDAO.insertVoucher(vou10);

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
                setUpRecyclerView(getContext());
            }
        });
    }
}