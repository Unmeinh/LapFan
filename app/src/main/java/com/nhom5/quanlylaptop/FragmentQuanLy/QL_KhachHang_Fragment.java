package com.nhom5.quanlylaptop.FragmentQuanLy;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QL_KhachHang_Fragment extends Fragment {

    List<HashMap<String, String>> list = new ArrayList<>();
    ListView listView;
    AppCompatButton addKH;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_khach_hang, container, false);
        listView = view.findViewById(R.id.listView_KhachHang);
        addKH = view.findViewById(R.id.button_AddKH);

        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.cardview_nva_user, new String[]{}, new int[]{});
        listView.setAdapter(simpleAdapter);

        openDialog();
        return view;
    }

    private void openDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit_sth, null);
        TextView titleDialog = view.findViewById(R.id.textView_Title_Dialog);
        TextInputLayout textInput_Name = view.findViewById(R.id.textInput_Name);
        TextInputLayout textInput_Email = view.findViewById(R.id.textInput_Email);
        TextInputLayout textInput_SDT = view.findViewById(R.id.textInput_SDT);
        TextInputLayout textInput_Password = view.findViewById(R.id.textInput_Password);
        AppCompatButton button = view.findViewById(R.id.button_Dialog);

        titleDialog.setText("Thêm Khách Hàng");
        button.setText("Tạo mới");

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(view);
        addKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}