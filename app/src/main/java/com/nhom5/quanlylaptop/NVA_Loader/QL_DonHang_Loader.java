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

        DonHang dh0 = new DonHang("DH0", "NV0", "KH0", "LP0", "VOU1", "Rate0",
                "The Reverie Saigon nằm bên trong tòa nhà Times Square Sài Gòn - Nguyễn Huệ - Quận 1 - TP Hồ Chí Minh",
                "2022-11-19", "Ví FPT Pay", "false", changeType.intMoneyToString(62013600), 3);
        donHangDAO.insertDonHang(dh0);

        DonHang dh1 = new DonHang("DH1", "NV0", "KH0", "LP1", "VOU4", "Rate1",
                "The Reverie Saigon nằm bên trong tòa nhà Times Square Sài Gòn - Nguyễn Huệ - Quận 1 - TP Hồ Chí Minh",
                "2022-11-20", "Ví FPT Pay", "true", changeType.intMoneyToString(21762000), 2);
        donHangDAO.insertDonHang(dh1);

        DonHang dh2 = new DonHang("DH2", "NV1", "KH1", "LP2", "VOU0", "Rate2",
                "Sofitel Metropole Hà Nội Số 15 - Phố Ngô Quyền - Hoàn Kiếm - Hà Nội",
                "2022-11-26", "Ví FPT Pay", "true", changeType.intMoneyToString(49312000), 1);
        donHangDAO.insertDonHang(dh2);

        DonHang dh3 = new DonHang("DH3", "NV2", "KH2", "LP3", "VOU1", "Rate3",
                "Evason Hideaway - Đường Trần Phú - Lộc Thọ - Nha Trang - Khánh Hòa",
                "2022-12-04", "Ví FPT Pay", "true", changeType.intMoneyToString(58942400), 2);
        donHangDAO.insertDonHang(dh3);

        DonHang dh4 = new DonHang("DH4", "NV2", "KH4", "LP4", "VOU7", "Rate4",
                "InterContinental Hanoi Westlake 05 Phố Từ Hoa - Quảng An - Tây Hồ - Hà Nội",
                "2022-12-15", "Ví FPT Pay", "true", changeType.intMoneyToString(204645600), 4);
        donHangDAO.insertDonHang(dh4);

        DonHang dh5 = new DonHang("DH5", "NV1", "KH3", "LP5", "VOU5", "Rate5",
                "Khách sạn JW Marriott Hanoi - Đường Đỗ Đức Dục - Mễ Trì - Từ Liêm - Hà Nội",
                "2022-12-21", "Ví FPT Pay", "true", changeType.intMoneyToString(63816000), 3);
        donHangDAO.insertDonHang(dh5);

        DonHang dh6 = new DonHang("DH6", "NV3", "KH3", "LP9", "VOU6", "Rate6",
                "Khách sạn JW Marriott Hanoi - Đường Đỗ Đức Dục - Mễ Trì - Từ Liêm - Hà Nội",
                "2022-12-25", "Ví FPT Pay", "true", changeType.intMoneyToString(19166000), 2);
        donHangDAO.insertDonHang(dh6);

        DonHang dh7 = new DonHang("DH7", "NV4", "KH4", "LP11", "VOU6", "Rate7",
                "InterContinental Hanoi Westlake 05 Phố Từ Hoa - Quảng An - Tây Hồ - Hà Nội",
                "2022-12-31", "Thanh toán khi nhận hàng", "true", changeType.intMoneyToString(44086000), 2);
        donHangDAO.insertDonHang(dh7);

        DonHang dh8 = new DonHang("DH8", "NV3", "KH2", "LP17", "VOU4", "Rate8",
                "Evason Hideaway - Đường Trần Phú - Lộc Thọ - Nha Trang - Khánh Hòa",
                "2022-01-01", "Thanh toán khi nhận hàng", "true", changeType.intMoneyToString(25821000), 1);
        donHangDAO.insertDonHang(dh8);

        DonHang dh9 = new DonHang("DH9", "NV0", "KH3", "LP24", "VOU3", "Rate9",
                "Khách sạn JW Marriott Hanoi - Đường Đỗ Đức Dục - Mễ Trì - Từ Liêm - Hà Nội",
                "2022-01-21", "Thanh toán khi nhận hàng", "true", changeType.intMoneyToString(42343000), 1);
        donHangDAO.insertDonHang(dh9);

        DonHang dh10 = new DonHang("DH10", "NV2", "KH4", "LP28", "VOU2", "Rate10",
                "InterContinental Hanoi Westlake 05 Phố Từ Hoa - Quảng An - Tây Hồ - Hà Nội",
                "2022-02-12", "Thanh toán khi nhận hàng", "true", changeType.intMoneyToString(26992500), 1);
        donHangDAO.insertDonHang(dh10);
    }
}

