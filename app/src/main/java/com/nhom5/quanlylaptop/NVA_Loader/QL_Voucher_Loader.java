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
    Context context;
    String TAG = "QL_Voucher_Loader_____";
    VoucherDAO voucherDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;

    public QL_Voucher_Loader(Context context, RecyclerView reView) {
        this.reView = reView;
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

        if (reView != null){
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
        Voucher vou1 = new Voucher("NOVEM1611", "Sale bất ngờ tháng 11", "20%", "2022-11-11", "2022-12-12");
        voucherDAO.insertVoucher(vou1);

        Voucher vou2 = new Voucher("DECEM1212", "Sale 12 tháng 12", "12%", "2022-12-12", "2022-12-12");
        voucherDAO.insertVoucher(vou2);

        Voucher vou3 = new Voucher("NOEL2512", "Siêu sale giáng sinh", "25%", "2022-12-25", "2022-12-25");
        voucherDAO.insertVoucher(vou3);

        Voucher vou4 = new Voucher("LUNAR0101", "Năm mới sale mạnh", "30%", "2022-01-01", "2022-01-01");
        voucherDAO.insertVoucher(vou4);

        Voucher vou5 = new Voucher("JANUA1501", "Sale nhẹ đón tết", "10%", "2022-01-15", "2023-02-01");
        voucherDAO.insertVoucher(vou5);

        Voucher vou6 = new Voucher("FEBRUA0202", "Siêu sale mùng 2 tháng 2", "20%", "2023-02-02", "2023-03-03");
        voucherDAO.insertVoucher(vou6);

        Voucher vou7 = new Voucher("MARCH0303", "Siêu sale mùng 3 tháng 3", "30%", "2023-03-03", "2023-04-04");
        voucherDAO.insertVoucher(vou7);

        Voucher vou8 = new Voucher("APRIL0104", "Ngày cá tháng tư giảm giá", "14%", "2023-04-01", "2023-04-02");
        voucherDAO.insertVoucher(vou8);

        Voucher vou9 = new Voucher("MAY1505", "Sale mùa đi thi", "25%", "2023-05-15", "2023-06-15");
        voucherDAO.insertVoucher(vou9);

        Voucher vou10 = new Voucher("SUM23", "Mùa hè sảng khoái", "23%", "2023-06-25", "2023-08-25");
        voucherDAO.insertVoucher(vou10);

    }

}
