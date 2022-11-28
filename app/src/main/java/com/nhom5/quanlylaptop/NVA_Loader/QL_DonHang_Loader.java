package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_DonHang_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_DonHang_Loader extends AsyncTask<String, Void, ArrayList<DonHang>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_DonHang_Loader_____";
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    TextView countDH;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView;
    @SuppressLint("StaticFieldLeak")
    RelativeLayout relativeLayout;
    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    KhachHangDAO khachHangDAO;
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<DonHang> listDon = new ArrayList<>();
    ArrayList<KhachHang> listKH = new ArrayList<>();

    public QL_DonHang_Loader(Context context, RecyclerView reView, TextView countDH, LinearLayout loadingView, RelativeLayout relativeLayout) {
        this.context = context;
        this.reView = reView;
        this.countDH = countDH;
        this.loadingView = loadingView;
        this.relativeLayout = relativeLayout;
    }

    @Override
    protected ArrayList<DonHang> doInBackground(String... strings) {

        laptopDAO = new LaptopDAO(context);
        donHangDAO = new DonHangDAO(context);
        khachHangDAO = new KhachHangDAO(context);
        listLap = laptopDAO.selectLaptop(null, null, null, null);
        listDon = donHangDAO.selectDonHang(null, null, null, null);
        listKH = khachHangDAO.selectKhachHang(null, null, null, null);

        if (listDon != null) {
            if (listDon.size() == 0) {
                addDemoDH();
            }
        }

        return donHangDAO.selectDonHang(null, null, null, "ngayMua");
    }

    @Override
    protected void onPostExecute(ArrayList<DonHang> listDon) {
        super.onPostExecute(listDon);

        if (loadingView != null && relativeLayout != null && reView != null && countDH != null) {
            loadingView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            setupReView(listDon, reView);
        }
    }

    private void setupReView(ArrayList<DonHang> listDon, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_DonHang_Adapter ql_donHang_adapter = new QL_DonHang_Adapter(listLap, listDon, listKH, context);
        recyclerView.setAdapter(ql_donHang_adapter);
        setCountDH(listDon);
    }

    private void setCountDH(ArrayList<DonHang> listDon) {
        countDH.setText(String.valueOf(listDon.size()));
    }

    private void addDemoDH() {
        ChangeType changeType = new ChangeType();

        DonHang dh0 = new DonHang("DH0", "0", "0", "0", "1", "0",
                "The Reverie Saigon nằm bên trong tòa nhà Times Square Sài Gòn - Nguyễn Huệ - Quận 1 - TP Hồ Chí Minh",
                "2022-11-19", "Ví FPT Pay", "false", changeType.stringMoneyToString("62013600"), 3);
        donHangDAO.insertDonHang(dh0);

        DonHang dh1 = new DonHang("DH1", "0", "0", "1", "4", "1",
                "The Reverie Saigon nằm bên trong tòa nhà Times Square Sài Gòn - Nguyễn Huệ - Quận 1 - TP Hồ Chí Minh",
                "2022-11-20", "Ví FPT Pay", "true", changeType.stringMoneyToString("21762000"), 2);
        donHangDAO.insertDonHang(dh1);

        DonHang dh2 = new DonHang("DH2", "1", "1", "2", "0", "2",
                "Sofitel Metropole Hà Nội Số 15 - Phố Ngô Quyền - Hoàn Kiếm - Hà Nội",
                "2022-11-26", "Ví FPT Pay", "true", changeType.stringMoneyToString("49312000"), 1);
        donHangDAO.insertDonHang(dh2);

        DonHang dh3 = new DonHang("DH3", "2", "2", "3", "1", "3",
                "Evason Hideaway - Đường Trần Phú - Lộc Thọ - Nha Trang - Khánh Hòa",
                "2022-12-04", "Ví FPT Pay", "true", changeType.stringMoneyToString("58942400"), 2);
        donHangDAO.insertDonHang(dh3);

        DonHang dh4 = new DonHang("DH4", "2", "4", "4", "7", "4",
                "InterContinental Hanoi Westlake 05 Phố Từ Hoa - Quảng An - Tây Hồ - Hà Nội",
                "2022-12-15", "Ví FPT Pay", "true", changeType.stringMoneyToString("204645600"), 4);
        donHangDAO.insertDonHang(dh4);

        DonHang dh5 = new DonHang("DH5", "1", "3", "5", "5", "5",
                "Khách sạn JW Marriott Hanoi - Đường Đỗ Đức Dục - Mễ Trì - Từ Liêm - Hà Nội",
                "2022-12-21", "Ví FPT Pay", "true", changeType.stringMoneyToString("63816000"), 3);
        donHangDAO.insertDonHang(dh5);

        DonHang dh6 = new DonHang("DH6", "3", "3", "9", "6", "6",
                "Khách sạn JW Marriott Hanoi - Đường Đỗ Đức Dục - Mễ Trì - Từ Liêm - Hà Nội",
                "2022-12-25", "Ví FPT Pay", "true", changeType.stringMoneyToString("19166000"), 2);
        donHangDAO.insertDonHang(dh6);

        DonHang dh7 = new DonHang("DH7", "4", "4", "11", "6", "7",
                "InterContinental Hanoi Westlake 05 Phố Từ Hoa - Quảng An - Tây Hồ - Hà Nội",
                "2022-12-31", "Thanh toán khi nhận hàng", "true", changeType.stringMoneyToString("44086000"), 2);
        donHangDAO.insertDonHang(dh7);

        DonHang dh8 = new DonHang("DH8", "3", "2", "17", "4", "8",
                "Evason Hideaway - Đường Trần Phú - Lộc Thọ - Nha Trang - Khánh Hòa",
                "2022-01-01", "Thanh toán khi nhận hàng", "true", changeType.stringMoneyToString("25821000"), 1);
        donHangDAO.insertDonHang(dh8);

        DonHang dh9 = new DonHang("DH9", "0", "3", "24", "3", "9",
                "Khách sạn JW Marriott Hanoi - Đường Đỗ Đức Dục - Mễ Trì - Từ Liêm - Hà Nội",
                "2022-01-21", "Thanh toán khi nhận hàng", "true", changeType.stringMoneyToString("42343000"), 1);
        donHangDAO.insertDonHang(dh9);

        DonHang dh10 = new DonHang("DH10", "2", "4", "28", "2", "10",
                "InterContinental Hanoi Westlake 05 Phố Từ Hoa - Quảng An - Tây Hồ - Hà Nội",
                "2022-02-12", "Thanh toán khi nhận hàng", "true", changeType.stringMoneyToString("26992500"), 1);
        donHangDAO.insertDonHang(dh10);
    }
}

