package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.ActivityKH.KH_ThanhToan_Activity;
import com.nhom5.quanlylaptop.DAO.GioHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.GioHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Voucher_Fragment;
import com.nhom5.quanlylaptop.KH_Adapter.KH_ThanhToan_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Voucher_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_ThanhToan_Loader extends AsyncTask<String, Void, ArrayList<GioHang>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "KH_ThanhToan_Loader_____";
    GioHangDAO gioHangDAO;
    LaptopDAO laptopDAO;
    ArrayList<Laptop> listLap;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView;
    @SuppressLint("StaticFieldLeak")
    RelativeLayout relativeLayout;
    String type, onWhat;

    public KH_ThanhToan_Loader(Context context, ArrayList<Laptop> listLap, String type, RecyclerView reView,
                               LinearLayout loadingView, RelativeLayout relativeLayout, String onWhat) {
        this.context = context;
        this.listLap = listLap;
        this.type = type;
        this.reView = reView;
        this.loadingView = loadingView;
        this.relativeLayout = relativeLayout;
        this.onWhat = onWhat;
    }

    @Override
    protected ArrayList<GioHang> doInBackground(String... strings) {
        gioHangDAO = new GioHangDAO(context);
        laptopDAO = new LaptopDAO(context);
        String maKH = strings[0];

        if (type.equals("giohang")){
            listLap = laptopDAO.selectLaptop(null, null, null, null);
        }

        return gioHangDAO.selectGioHang(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<GioHang> listGio) {
        super.onPostExecute(listGio);

        if (loadingView != null && relativeLayout != null && reView != null){
            loadingView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            if (type.equals("giohang")){
                setupReViewGH(listGio, reView);
            } else {
                setUpReViewMN(reView);
            }
        }
    }

    private void setupReViewGH(ArrayList<GioHang> listGio, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (onWhat.equals("onCreate")){
            KH_ThanhToan_Adapter kh_thanhToan_adapter = new KH_ThanhToan_Adapter(listLap, listGio, context, "onCreate");
            recyclerView.setAdapter(kh_thanhToan_adapter);
        } else {
            KH_ThanhToan_Adapter kh_thanhToan_adapter = new KH_ThanhToan_Adapter(listLap, listGio, context, "onResume");
            recyclerView.setAdapter(kh_thanhToan_adapter);
        }
    }

    private void setUpReViewMN(RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (onWhat.equals("onCreate")){
            KH_ThanhToan_Adapter kh_thanhToan_adapter = new KH_ThanhToan_Adapter(listLap, context, "onCreate");
            recyclerView.setAdapter(kh_thanhToan_adapter);
        } else {
            KH_ThanhToan_Adapter kh_thanhToan_adapter = new KH_ThanhToan_Adapter(listLap, context, "onResume");
            recyclerView.setAdapter(kh_thanhToan_adapter);
        }
    }
}
