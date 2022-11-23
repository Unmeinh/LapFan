package com.nhom5.quanlylaptop.NVA_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.DAO.ViTienDAO;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.Entity.ViTien;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_NhanVien_Fragment;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_NhanVien_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_NhanVien_Loader extends AsyncTask<String, Void, ArrayList<NhanVien>> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "QL_NhanVien_Loader_____";
    NhanVienDAO nhanVienDAO;
    @SuppressLint("StaticFieldLeak")
    RecyclerView reView;
    @SuppressLint("StaticFieldLeak")
    TextView countNV;
    @SuppressLint("StaticFieldLeak")
    LinearLayout loadingView;
    @SuppressLint("StaticFieldLeak")
    RelativeLayout relativeLayout;

    public QL_NhanVien_Loader(Context context, RecyclerView reView, TextView countNV, LinearLayout loadingView, RelativeLayout relativeLayout) {
        this.context = context;
        this.reView = reView;
        this.countNV = countNV;
        this.loadingView = loadingView;
        this.relativeLayout = relativeLayout;
    }

    @Override
    protected ArrayList<NhanVien> doInBackground(String... strings) {
        nhanVienDAO = new NhanVienDAO(context);
        ArrayList<KhachHang> list = nhanVienDAO.selectNhanVien(null, null, null, null);
        if (list != null){
            if (list.size() == 0){
                addDemoNV();
            }
        }

        return nhanVienDAO.selectNhanVien(null, null, null, null);
    }

    @Override
    protected void onPostExecute(ArrayList<NhanVien> listNV) {
        super.onPostExecute(listNV);

        if (loadingView != null && relativeLayout != null && reView != null && countNV != null){
            loadingView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            setupReView(listNV, reView);
        }
    }

    private void setupReView(ArrayList<NhanVien> listNV, RecyclerView recyclerView) {
        Log.d(TAG, "setUpReView: true");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        QL_NhanVien_Adapter ql_nhanVien_adapter = new QL_NhanVien_Adapter(listNV, context);
        recyclerView.setAdapter(ql_nhanVien_adapter);
        setCountKH(listNV);
    }

    private void setCountKH(ArrayList<NhanVien> listNV){
        countNV.setText(String.valueOf(listNV.size()));
    }

    private void addDemoNV(){
        ChangeType changeType = new ChangeType();

        NhanVien nv0 = new NhanVien("NV0", "Leonardo", "DiCaprio", "Nam", "leo@gmail.com",
                "leonardo", "Hoa Kỳ", "011111974", 0, 0,
                changeType.checkByteInput(changeType.bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.leonardo_dicaprio))));
        nhanVienDAO.insertNhanVien(nv0);

        NhanVien nv1 = new NhanVien("NV1", "Hoàng", "Thái Vũ", "Nam", "vu@gmail.com",
                "vu1995", "Việt Nam", "003101995", 0, 0,
                changeType.checkByteInput(changeType.bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.vu))));
        nhanVienDAO.insertNhanVien(nv1);

        NhanVien nv2 = new NhanVien("NV2", "Nguyễn", "Đức Cường", "Nam", "den@gmail.com",
                "denvau", "Việt Nam", "013051989", 0, 0,
                changeType.checkByteInput(changeType.bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.den_vau))));
        nhanVienDAO.insertNhanVien(nv2);

        NhanVien nv3 = new NhanVien("NV3", "Martin", "Garrix", "Nam", "martin@gmail.com",
                "martingarrix", "Hà Lan", "014051996", 0, 0,
                changeType.checkByteInput(changeType.bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.martin_garrix))));
        nhanVienDAO.insertNhanVien(nv3);

        NhanVien nv4 = new NhanVien("NV4", "Nguyễn", "Công Lý", "Nam", "congly@gmail.com",
                "congly", "Việt Nam", "016101973", 0, 0,
                changeType.checkByteInput(changeType.bitmapToByte(BitmapFactory.decodeResource(context.getResources(), R.drawable.cong_ly))));
        nhanVienDAO.insertNhanVien(nv4);
    }
}

