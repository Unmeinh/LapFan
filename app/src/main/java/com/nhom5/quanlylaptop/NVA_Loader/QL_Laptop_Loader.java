package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.FragmentQuanLy.Tab_Laptop_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_Laptop_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;

import java.util.ArrayList;

import me.ibrahimsn.lib.CirclesLoadingView;

public class QL_Laptop_Loader extends AsyncTask<String, Void, ArrayList<Laptop>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "LaptopLoader_____";
    LaptopDAO laptopDAO;
    ArrayList<HangLaptop> listHang = new ArrayList<>();
    HangLaptopDAO hangLaptopDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView;
    @SuppressLint("StaticFieldLeak")
    RelativeLayout relativeLayout;

    public QL_Laptop_Loader(Context context, RecyclerView reView, LinearLayout loadingView, RelativeLayout relativeLayout) {
        this.context = context;
        this.reView = reView;
        this.loadingView = loadingView;
        this.relativeLayout = relativeLayout;
    }

    @Override
    protected ArrayList<Laptop> doInBackground(String... strings) {
        laptopDAO = new LaptopDAO(context);
        hangLaptopDAO = new HangLaptopDAO(context);
        ArrayList<Laptop> list = laptopDAO.selectLaptop(null, null, null, "maHangLap");
        if (list != null) {
            if (list.size() == 0) {
                AddData addData = new AddData(context);
                addData.addDemoLaptopDell();
                addData.addDemoLaptopHP();
                addData.addDemoLaptopAcer();
                addData.addDemoLaptopAsus();
                addData.addDemoLaptopMsi();
                addData.addDemoLaptopMac();
            }
        }

        return laptopDAO.selectLaptop(null, null, null, "maHangLap");
    }

    @Override
    protected void onPostExecute(ArrayList<Laptop> listLap) {
        super.onPostExecute(listLap);

        if (loadingView != null && relativeLayout != null && reView != null) {
            loadingView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            setupReView(listLap, reView);
        }
    }

    private void setupReView(ArrayList<Laptop> listLap, RecyclerView recyclerView) {
        listHang = hangLaptopDAO.selectHangLaptop(null, null, null, null);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        QL_Laptop_Adapter ql_laptop_adapter = new QL_Laptop_Adapter(listLap, listHang, context);
        recyclerView.setAdapter(ql_laptop_adapter);
    }

}
