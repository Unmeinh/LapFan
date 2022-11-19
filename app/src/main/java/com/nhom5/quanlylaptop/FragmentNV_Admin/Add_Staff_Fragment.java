package com.nhom5.quanlylaptop.FragmentNV_Admin;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class Add_Staff_Fragment extends Fragment {

    ChangeType changeType = new ChangeType();
    NhanVienDAO nhanVienDAO;
    ArrayList<NhanVien> listNV = new ArrayList<NhanVien>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_staff, container, false);
        nhanVienDAO = new NhanVienDAO(getContext());
        listNV = nhanVienDAO.selectNhanVien(null, null, null, null);
        openDialog(view);
        return view;
    }

    private void openDialog(View view) {
        TextInputLayout textInput_LastName = view.findViewById(R.id.textInput_LastName);
        TextInputLayout textInput_FirstName = view.findViewById(R.id.textInput_FirstName);
        TextInputLayout textInput_Email = view.findViewById(R.id.textInput_Email);
        TextInputLayout textInput_SDT = view.findViewById(R.id.textInput_SDT);
        TextInputLayout textInput_Password = view.findViewById(R.id.textInput_Password);
        AppCompatButton button = view.findViewById(R.id.button_AddNV);

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
                NhanVien nhanVien = new NhanVien("NV" + listNV.size(), lastName, firstName, "No Data",
                        email, password, "No Data", sdt, avatar);
                nhanVienDAO.insertNhanVien(nhanVien);
            }
        });
    }
}