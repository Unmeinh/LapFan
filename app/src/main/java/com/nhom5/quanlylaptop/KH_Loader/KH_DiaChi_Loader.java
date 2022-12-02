package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_DiaChi_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_Voucher_Activity;
import com.nhom5.quanlylaptop.DAO.DiaChiDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.DiaChi;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.KH_Adapter.KH_DiaChi_Adapter;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_DiaChi_Loader extends AsyncTask<String, Void, ArrayList<DiaChi>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "KH_DiaChi_Loader_____";
    DiaChiDAO diaChiDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    public KH_DiaChi_Loader(Context context, RecyclerView reView) {
        this.context = context;
        this.reView = reView;
    }

    @Override
    protected ArrayList<DiaChi> doInBackground(String... strings) {
        diaChiDAO = new DiaChiDAO(context);
        String maKH = strings[0];
        ArrayList<DiaChi> list = diaChiDAO.selectDiaChi(null, null, null, null);
        if (list != null) {
            if (list.size() == 0) {
                addDemoDiaChi();
            }
        }

        return diaChiDAO.selectDiaChi(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<DiaChi> listVou) {
        super.onPostExecute(listVou);

        if (reView != null) {
            setupReView(listVou, reView);
        }
    }

    private void setupReView(ArrayList<DiaChi> listDC, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_DiaChi_Adapter kh_diaChi_adapter = new KH_DiaChi_Adapter(listDC, context);
        recyclerView.setAdapter(kh_diaChi_adapter);
    }

    private void addDemoDiaChi() {
        DiaChi dc1 = new DiaChi("DC0", "0", "Donald Trump", "014061946"
                , "Số nhà 1601", "Hoa Kì", "Washington", "Đại lộ Pennsylvania NW");
        diaChiDAO.insertDiaChi(dc1);

        DiaChi dc2 = new DiaChi("DC1", "0", "Barack Obama", "004081961"
                , "Số nhà 1600", "Hoa Kì", "Washington", "Đại lộ Pennsylvania NW");
        diaChiDAO.insertDiaChi(dc2);

        DiaChi dc3 = new DiaChi("DC2", "1", "Vladimir Putin", "007101952"
                , "Dinh thự Novo-Ogaryevo", "Nga", "Moscow", "Phía tây");
        diaChiDAO.insertDiaChi(dc3);

        DiaChi dc4 = new DiaChi("DC3", "2", "Nguyễn Phú Trọng", "014041944"
                , "Số 5 phố Thiền Quang", "Hà Nội", "Hai Bà Trưng", "Nguyễn Du");
        diaChiDAO.insertDiaChi(dc4);

        DiaChi dc5 = new DiaChi("DC4", "2", "Nguyễn Xuân Phúc", "020071954"
                , "Nhà công vụ số 11", "Hà Nội", "Ba Đình", "Chùa Một Cột");
        diaChiDAO.insertDiaChi(dc5);
    }
}