package com.nhom5.quanlylaptop.FragmentNV_Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.Activity.Account_Manager_Activity;
import com.nhom5.quanlylaptop.Activity.Webview_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_DonHang_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_Voucher_Activity;
import com.nhom5.quanlylaptop.ActivityNV_Admin.NV_DonHang_Activity;
import com.nhom5.quanlylaptop.R;

public class NV_Account_Fragment extends Fragment {

    LinearLayout cdnv, hdsd, qldh, meoBH, doiMatKhau, thietLapTaiKhoan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nv_account, container, false);
        qldh = view.findViewById(R.id.onclick_Don_Hang_Da_Ban);
        cdnv = view.findViewById(R.id.onclick_CD_NV);
        meoBH = view.findViewById(R.id.onclick_Meo_Ban_Hang);
        hdsd = view.findViewById(R.id.onclick_Huong_Dan_Su_Dung);
        doiMatKhau = view.findViewById(R.id.onclick_Doi_Mat_Khau);
        thietLapTaiKhoan = view.findViewById(R.id.onclick_Thiet_Lap_Tai_Khoan);
        clickQuanLyDonHang();
        clickCongDongNhanVien();
        clickMeoBanHang();
        clickHuongDanSuDung();
        clickDoiMatKhau();
        clickThietLapTaiKhoan();

        ImageView imageView = view.findViewById(R.id.imageView_Avatar);
        imageView.setImageResource(R.drawable.ryan_reynolds);
        return view;
    }

    private void clickQuanLyDonHang(){
        qldh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NV_DonHang_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void clickCongDongNhanVien(){
        cdnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Webview_Activity.class);
                intent.putExtra("titleWeb", "Cộng đồng nhân viên");
                intent.putExtra("urlWeb", "https://www.facebook.com/groups/500323005383352");
                startActivity(intent);
            }
        });
    }

    private void clickMeoBanHang(){
        meoBH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Webview_Activity.class);
                intent.putExtra("titleWeb", "Mẹo bán hàng");
                intent.putExtra("urlWeb", "https://www.youtube.com/watch?v=DR0K1LYZ5FQ");
                startActivity(intent);
            }
        });
    }

    private void clickHuongDanSuDung(){
        hdsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void clickDoiMatKhau(){
        View view = getLayoutInflater().inflate(R.layout.dialog_doi_pass, null);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(view);
        doiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Button cancel = view.findViewById(R.id.button_Cancel_Dialog);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout text1 = view.findViewById(R.id.textInput_Current_Password);
                TextInputLayout text2 = view.findViewById(R.id.textInput_New_Password);
                TextInputLayout text3 = view.findViewById(R.id.textInput_Confirm_Password);
                text1.getEditText().setText("");
                text2.getEditText().setText("");
                text3.getEditText().setText("");
                text1.getEditText().clearFocus();
                text2.getEditText().clearFocus();
                text3.getEditText().clearFocus();
                dialog.dismiss();
            }
        });
    }

    private void clickThietLapTaiKhoan(){
        thietLapTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Account_Manager_Activity.class);
                startActivity(intent);
            }
        });
    }
}