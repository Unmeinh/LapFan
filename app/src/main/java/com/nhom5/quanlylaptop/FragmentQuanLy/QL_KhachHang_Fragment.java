package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_DonHang_Adapter;
import com.nhom5.quanlylaptop.NAV_Adapter.QL_KhachHang_Adapter;
import com.nhom5.quanlylaptop.NVA_Loader.QL_KhachHang_Loader;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_KhachHang_Fragment extends Fragment {


    AppCompatButton addKH;
    KhachHangDAO khachHangDAO;
    ArrayList<KhachHang> listKH = new ArrayList<>();
    ChangeType changeType = new ChangeType();
    String TAG = "QL_KhachHang_Fragment_____";
    TextView countKH;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    LinearLayout linearKhachHangEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_khach_hang, container, false);
        addKH = view.findViewById(R.id.button_AddKH);
        countKH = view.findViewById(R.id.textView_Soluong);
        recyclerView = view.findViewById(R.id.recyclerView_NVA_KhachHang);
        relativeLayout = view.findViewById(R.id.layoutView);
        linearLayout = view.findViewById(R.id.loadingView);
        linearKhachHangEmpty = view.findViewById(R.id.linearKhachHangEmpty);

        khachHangDAO = new KhachHangDAO(getContext());
        QL_KhachHang_Loader qlKhachHangLoader = new QL_KhachHang_Loader(getContext(), recyclerView, countKH, linearLayout, linearKhachHangEmpty, relativeLayout);
        qlKhachHangLoader.execute("");
        openDialog();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        QL_KhachHang_Loader qlKhachHangLoader = new QL_KhachHang_Loader(getContext(), recyclerView, countKH, linearLayout, linearKhachHangEmpty, relativeLayout);
        qlKhachHangLoader.execute("");
    }

    private void openDialog() {
        listKH = khachHangDAO.selectKhachHang(null, null, null, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inft = ((Activity) getActivity()).getLayoutInflater();
        View view = inft.inflate(R.layout.dialog_add_edit_sth, null);

        TextView titleDialog = view.findViewById(R.id.textView_Title_Dialog);
        TextInputLayout textInput_LastName = view.findViewById(R.id.textInput_LastName);
        TextInputLayout textInput_FirstName = view.findViewById(R.id.textInput_FirstName);
        TextInputLayout textInput_Email = view.findViewById(R.id.textInput_Email);
        TextInputLayout textInput_SDT = view.findViewById(R.id.textInput_SDT);
        TextInputLayout textInput_Password = view.findViewById(R.id.textInput_Password);
        AppCompatButton button = view.findViewById(R.id.button_Dialog);

        builder.setView(view);
        Dialog dialog = builder.create();
        addKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleDialog.setText("Thêm Khách Hàng");
                button.setText("Tạo mới");
                dialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastName = textInput_LastName.getEditText().getText().toString();
                String firstName = textInput_FirstName.getEditText().getText().toString();
                String email = textInput_Email.getEditText().getText().toString();
                String sdt = textInput_SDT.getEditText().getText().toString();
                String password = textInput_Password.getEditText().getText().toString();
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.image_avatar);
                byte[] avatar = changeType.checkByteInput(changeType.bitmapToByte(bitmap));
                KhachHang khachHang = new KhachHang("KH", lastName, firstName, "No Data",
                        email, password, "No Data", sdt, "false", avatar);
                khachHangDAO.insertKhachHang(khachHang);
                dialog.cancel();
                QL_KhachHang_Loader qlKhachHangLoader = new QL_KhachHang_Loader(getContext(), recyclerView, countKH, linearLayout, linearKhachHangEmpty, relativeLayout);
                qlKhachHangLoader.execute("");
            }
        });
    }
}