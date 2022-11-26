package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.KH_Adapter.All_ThongBao_Adapter;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class All_ThongBao_Loader extends AsyncTask<String, Void, ArrayList<ThongBao>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    ThongBaoDAO thongBaoDAO;
    String TAG = "NV_DonHang_Loader_____";
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView;
    String table;

    public All_ThongBao_Loader(Context context, RecyclerView reView, LinearLayout loadingView, String table) {
        this.context = context;
        this.reView = reView;
        this.loadingView = loadingView;
        this.table = table;
    }

    @Override
    protected ArrayList<ThongBao> doInBackground(String... strings) {
        thongBaoDAO = new ThongBaoDAO(context);

        String maKH = strings[0];

        ArrayList<ThongBao> listTB = thongBaoDAO.selectThongBao(null, null, null, null, table);
        if (listTB != null){
            if (listTB.size() == 0){
                addDemoDataTB();
            }
        }

        return thongBaoDAO.selectThongBao(null, null, null, "ngayTB", table);
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
        All_ThongBao_Adapter all_thongBao_adapter = new All_ThongBao_Adapter(listTB, context);
        recyclerView.setAdapter(all_thongBao_adapter);
    }


    private void addDemoDataTB(){
        ChangeType changeType = new ChangeType();
        ThongBao tb0 = new ThongBao("TB0", "KH0", "Chào mừng khách hàng",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-11-05");
        thongBaoDAO.insertThongBao(tb0, "kh");

        ThongBao tb1 = new ThongBao("TB1", "KH0", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop MSI Creator Z16P B12UGST i7 12700H với giá 79.490.000₫","2022-11-10");
        thongBaoDAO.insertThongBao(tb1, "kh");

        ThongBao tb2 = new ThongBao("TB2", "KH0", "Cảm ơn khách hàng",
                "Bạn vừa đặt mua 1 chiếc Laptop Apple MacBook Air M1 2020 16GB với giá 33.490.000₫","2022-11-15");
        thongBaoDAO.insertThongBao(tb2, "kh");

        ThongBao tb3 = new ThongBao("TB3", "NV0", "Chào mừng nhân viên",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-11-15");
        thongBaoDAO.insertThongBao(tb3, "nv");

        ThongBao tb4 = new ThongBao("TB4", "NV0", "Đơn hàng mới đặt",
                "Khách hàng Nguyễn Xuân Bắc muốn đặt mua 1 chiếc Laptop Apple MacBook Pro 16 M1 Max 2021 10 core-CPU với giá 85.990.000₫","2022-12-12");
        thongBaoDAO.insertThongBao(tb4, "nv");

        ThongBao tb5 = new ThongBao("TB5", "NV0", "Thiết lập tài khoản",
                "Bạn vừa đổi mật khẩu","2022-12-20");
        thongBaoDAO.insertThongBao(tb5, "nv");

        ThongBao tb6 = new ThongBao("TB6", "NV0", "Thiết lập tài khoản",
                "Bạn vừa thay đổi thông tin cá nhân","2023-01-01");
        thongBaoDAO.insertThongBao(tb6, "nv");

        ThongBao tb7 = new ThongBao("TB7", "Admin", "Chào mừng quản lý",
                "Mong rằng bạn sẽ có những trải nghiệm tuyệt vời với ứng dụng FPT shop","2022-12-05");
        thongBaoDAO.insertThongBao(tb7, "ad");

        ThongBao tb8 = new ThongBao("TB8", "Admin", "Quản lý nhân viên",
                "Bạn vừa thêm nhân viên Nguyễn Công Lý với mã nhân viên NV4","2023-01-25");
        thongBaoDAO.insertThongBao(tb8, "ad");

        ThongBao tb9 = new ThongBao("TB9", "Admin", "Quản lý đơn hàng",
                "Bạn vừa thêm đơn hàng mã DH3 với giá " + changeType.intMoneyToString(58942400),"2023-02-03");
        thongBaoDAO.insertThongBao(tb9, "ad");

        ThongBao tb10 = new ThongBao("TB10", "Admin", "Xác nhận đơn hàng",
                "Nhân viên Nguyễn Công Lý vừa xác nhận đơn hàng DH4","2023-02-12");
        thongBaoDAO.insertThongBao(tb10, "ad");

    }
}
