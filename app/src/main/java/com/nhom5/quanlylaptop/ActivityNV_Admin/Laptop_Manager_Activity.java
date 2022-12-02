package com.nhom5.quanlylaptop.ActivityNV_Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.List;

public class Laptop_Manager_Activity extends AppCompatActivity {

    Context context = this;
    FrameLayout uploadImageLaptop;
    Spinner ramSpinner, hangLapSpinner;
    Dialog dialogNhapLink;
    ImageView imageView_anhLapMoi, imageView_AddLaptop;
    TextInputLayout linkAnhMoi, tenLapMoi, giaTien, soLuong;
    AppCompatButton buttonFind, buttonThemLaptopNgay;
    String tenLaptop, hangLaptop, thongSoKT, tien;
    int soluong;
    LaptopDAO laptopDAO;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_manager);
        uploadImageLaptop = findViewById(R.id.upload_Image_Laptop);
        imageView_anhLapMoi = findViewById(R.id.imageView_Laptop);
        imageView_AddLaptop = findViewById(R.id.imageView_AddLaptop);
        tenLapMoi = findViewById(R.id.textInput_TenLaptop);
        hangLapSpinner = findViewById(R.id.spinner_HangLaptop);
        ramSpinner = findViewById(R.id.spinner_TSKT);
        giaTien = findViewById(R.id.textInput_GiaTien);
        soLuong = findViewById(R.id.textInput_SoLuong);
        buttonThemLaptopNgay = findViewById(R.id.button_Laptop_Manager);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.ram_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ramSpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hang_laptop_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hangLapSpinner.setAdapter(adapter2);

        uploadImageLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Laptop_Manager_Activity.this);
                builder.setTitle("Chọn ảnh laptop");
                final String[] arrAnhLap = getResources().getStringArray(R.array.chon_anh_laptop);
                builder.setSingleChoiceItems(arrAnhLap, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            // chọn thư viện
                        } else if (i == 1) {
                            openDialogAnhLapMoi();
                        } else {
                            Toast.makeText(Laptop_Manager_Activity.this, "Bạn chưa chọn chức năng nào!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        useToolbar();
        addLaptopNew();
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Thêm Laptop");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openDialogAnhLapMoi() {
        dialogNhapLink = new Dialog(Laptop_Manager_Activity.this);
        View view1 = getLayoutInflater().inflate(R.layout.dialog_add_laptop_image, null);
        dialogNhapLink.setContentView(view1);
        linkAnhMoi = view1.findViewById(R.id.textInput_linkAnhMoi);
        buttonFind = view1.findViewById(R.id.button_timLinkAnh);

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linkAnhMoi.getEditText().getText().length() == 0) {
                    linkAnhMoi.setError("Link ảnh không được bỏ trống!");
                } else {
                    linkAnhMoi.setError(null);

                    ChangeType changeType = new ChangeType();
                    Bitmap bitmap = changeType.urlToBitmap(context, linkAnhMoi.getEditText().getText().toString());
                    if (bitmap != null){
                        imageView_anhLapMoi.setImageBitmap(bitmap);
                        imageView_AddLaptop.setVisibility(View.GONE);
                        dialogNhapLink.dismiss();
                        dialogNhapLink.dismiss();
                    }
                }

            }
        });
        dialogNhapLink.show();
    }

    public void addLaptopNew() {
        laptopDAO = new LaptopDAO(this);
        List<Laptop> list = laptopDAO.selectLaptop(null, null, null, null);

        buttonThemLaptopNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenLaptop = tenLapMoi.getEditText().getText().toString();
                hangLaptop = (String) hangLapSpinner.getSelectedItem();
                thongSoKT = (String) ramSpinner.getSelectedItem();

                tien = giaTien.getEditText().getText().toString();
                soluong = Integer.parseInt(soLuong.getEditText().getText().toString());
                Bitmap bm = changeType.urlToBitmap(context, linkAnhMoi.getEditText().getText().toString());
                Laptop laptop = new Laptop("LP" + list.size(), "L" + hangLaptop, tenLaptop, thongSoKT,
                        changeType.stringToStringMoney(changeType.stringMoneyToInt(tien) + ""), soluong,
                        0, changeType.checkByteInput(changeType.bitmapToByte(bm)));
                laptopDAO.insertLaptop(laptop);

                finish();
            }

        });

    }
}