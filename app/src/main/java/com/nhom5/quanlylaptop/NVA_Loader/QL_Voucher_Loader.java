package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_NhanVien_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Voucher_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class QL_Voucher_Loader extends AsyncTask<String, Void, ArrayList<Voucher>> {
    @SuppressLint("StaticFieldLeak")
    QL_Voucher_Fragment qlVoucherFragment;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_Voucher_Loader_____";
    VoucherDAO voucherDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public QL_Voucher_Loader(QL_Voucher_Fragment qlVoucherFragment, Context context) {
        this.qlVoucherFragment = qlVoucherFragment;
        this.context = context;
    }

    @Override
    protected ArrayList<Voucher> doInBackground(String... strings) {
        voucherDAO = new VoucherDAO(context);
        ArrayList<Voucher> listVou = voucherDAO.selectVoucher(null, null, null, null);

        if (listVou == null) {
            Log.d(TAG, "onCreateView: list null");
            addDemoVoucher();
        } else {
            if (listVou.size() == 0) {
                Log.d(TAG, "onCreateView: list not null");
                Log.d(TAG, "onCreateView: list size = " + listVou.size());
                addDemoVoucher();
            }
        }

        return voucherDAO.selectVoucher(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<Voucher> listVou) {
        super.onPostExecute(listVou);

        if (qlVoucherFragment != null){
            reView = qlVoucherFragment.getActivity().findViewById(R.id.recyclerView_NVA_Voucher);
            setupReView(listVou, reView);
        }
    }

    private void setupReView(ArrayList<Voucher> listVou, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_Voucher_Adapter ql_voucher_adapter = new QL_Voucher_Adapter(listVou, context);
        recyclerView.setAdapter(ql_voucher_adapter);
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

}
