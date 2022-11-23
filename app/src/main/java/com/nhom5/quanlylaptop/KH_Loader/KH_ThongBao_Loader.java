package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.FragmentKH.KH_ThongBao_Fragment;
import com.nhom5.quanlylaptop.KH_Adapter.KH_ThongBao_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class KH_ThongBao_Loader extends AsyncTask<String, Void, ArrayList<ThongBao>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    ThongBaoDAO thongBaoDAO;
    String TAG = "NV_DonHang_Loader_____";
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView;

    public KH_ThongBao_Loader(Context context, RecyclerView reView, LinearLayout loadingView) {
        this.context = context;
        this.reView = reView;
        this.loadingView = loadingView;
    }

    @Override
    protected ArrayList<ThongBao> doInBackground(String... strings) {
        thongBaoDAO = new ThongBaoDAO(context);

        String maKH = strings[0];

        ArrayList<ThongBao> listTB = thongBaoDAO.selectThongBao(null, null, null, null);
        if (listTB != null){
            if (listTB.size() == 0){
                addDemoDataTB();
            }
        }

        return thongBaoDAO.selectThongBao(null, null, null, "ngayTB");
    }

    @Override
    protected void onPostExecute(ArrayList<ThongBao> listTB) {
        super.onPostExecute(listTB);

        if (reView != null){
            loadingView.setVisibility(View.GONE);
            setupReView(listTB, reView);
        }
    }

    private void setupReView(ArrayList<ThongBao> listTB, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        KH_ThongBao_Adapter kh_thongBao_adapter = new KH_ThongBao_Adapter(listTB, context);
        recyclerView.setAdapter(kh_thongBao_adapter);
    }


    private void addDemoDataTB(){
        ThongBao tb0 = new ThongBao("TB2", "KH0", "Chào mừng khách hàng",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-11-05");
        thongBaoDAO.insertThongBao(tb0);

        ThongBao tb1 = new ThongBao("TB1", "KH0", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Creator Z16P B12UGST i7 12700H với giá 79.490.000₫","2022-11-10");
        thongBaoDAO.insertThongBao(tb1);

        ThongBao tb2 = new ThongBao("TB2", "KH0", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Apple MacBook Air M1 2020 16GB với giá 33.490.000₫","2022-11-15");
        thongBaoDAO.insertThongBao(tb2);

        ThongBao tb3 = new ThongBao("TB3", "KH1", "Chào mừng khách hàng",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-11-15");
        thongBaoDAO.insertThongBao(tb3);

        ThongBao tb4 = new ThongBao("TB4", "KH1", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MacBook Pro 14 M1 Max 2021 10-core CPU với giá 71.900.000₫","2022-12-12");
        thongBaoDAO.insertThongBao(tb4);

        ThongBao tb5 = new ThongBao("TB5", "KH1", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Apple MacBook Pro 16 M1 Max 2021 10 core-CPU với giá 85.990.000₫","2022-12-20");
        thongBaoDAO.insertThongBao(tb5);

        ThongBao tb6 = new ThongBao("TB6", "KH1", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Asus Gaming ROG Flow Z13 GZ301Z i7 12700H với giá 48.690.000₫","2023-01-01");
        thongBaoDAO.insertThongBao(tb6);

        ThongBao tb7 = new ThongBao("TB7", "KH2", "Chào mừng khách hàng",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-12-05");
        thongBaoDAO.insertThongBao(tb7);

        ThongBao tb8 = new ThongBao("TB8", "KH2", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Asus Vivobook Pro 15 OLED K6502Z i7 12650H với giá 31.690.000₫","2023-01-25");
        thongBaoDAO.insertThongBao(tb8);

        ThongBao tb9 = new ThongBao("TB9", "KH2", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Gaming GS66 Stealth 11UG i7 11800H với giá 64.490.000₫","2023-02-03");
        thongBaoDAO.insertThongBao(tb9);

        ThongBao tb10 = new ThongBao("TB10", "KH2", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Gaming GE66 Raider 11UH i7 11800H với giá 77.490.000₫","2023-02-12");
        thongBaoDAO.insertThongBao(tb10);

    }
}
