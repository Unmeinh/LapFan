package com.nhom5.quanlylaptop.ActivityKH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KH_Voucher_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Voucher> listVou = new ArrayList<>();
    VoucherDAO voucherDAO;
    QL_Voucher_Adapter ql_voucher_adapter;
    String TAG = "QL_Voucher_Fragment_____";
    Context context = this;
    ImageButton backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_voucher);
        recyclerView = findViewById(R.id.recyclerView_Voucher_KH);
        backButton = findViewById(R.id.imageButton_Back);
        voucherDAO = new VoucherDAO(context);

        listVou = voucherDAO.selectVoucher(null, null, null, null);
        setUpRecyclerView(context);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setUpRecyclerView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        ql_voucher_adapter = new QL_Voucher_Adapter(listVou, context);
        recyclerView.setAdapter(ql_voucher_adapter);
    }
}