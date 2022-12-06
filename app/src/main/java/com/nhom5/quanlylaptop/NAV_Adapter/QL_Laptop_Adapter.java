package com.nhom5.quanlylaptop.NAV_Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.ActivityKH.Info_Laptop_Activity;
import com.nhom5.quanlylaptop.DAO.HangLaptopDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.HangLaptop;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_Laptop_Adapter extends RecyclerView.Adapter<QL_Laptop_Adapter.AuthorViewHolder> {

    Spinner ramSpinner, hangLapSpinner;
    ChangeType changeType = new ChangeType();
    TextInputLayout textInput_TenLaptop, textInput_GiaTien, textInput_SoLuong;
    ImageView imageView_Laptop;
    AppCompatButton button_Laptop_Manager;
    Context context;
    ArrayList<HangLaptop> listHang;
    ArrayList<Laptop> listLap;
    Laptop laptop;
    LaptopDAO laptopDAO;
    int posLap;
    String TAG = "QL_Laptop_Adapter_____";

    public QL_Laptop_Adapter(ArrayList<Laptop> listLap, ArrayList<HangLaptop> listHang, Context context) {
        this.listLap = listLap;
        this.listHang = listHang;
        this.context = context;
        laptopDAO = new LaptopDAO(context);
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_laptop, vGroup, false);
        return new AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        setRow(pos, author);

        author.delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                laptop = listLap.get(getPosLap());
                laptopDAO.deleteLaptop(laptop);
                listLap.remove(getPosLap());
                notifyDataSetChanged();
            }
        });

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info_Laptop_Activity.class);
                Laptop laptop = listLap.get(pos);
                if (laptop != null) {
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("laptop", laptop);
                    Log.d(TAG, "onBindViewHolder: Laptop: " + laptop.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("openFrom", "other");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        author.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosLap(pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLap.size();
    }

    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView imgLaptop, imgHang;
        TextView name, gia, soLuong, daBan;
        ImageButton delete;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLaptop = itemView.findViewById(R.id.imageView_Laptop);
            imgHang = itemView.findViewById(R.id.imageView_HangLaptop);
            name = itemView.findViewById(R.id.textView_TenLaptop);
            gia = itemView.findViewById(R.id.textView_GiaTien);
            soLuong = itemView.findViewById(R.id.textView_Soluong);
            delete = itemView.findViewById(R.id.imageButton_Delete);
            daBan = itemView.findViewById(R.id.textView_SoSP_DaBan);
            itemView.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(Menu.NONE, R.id.item_CapNhat, Menu.NONE, "Cập nhật");
            edit.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Laptop laptop = listLap.get(getPosLap());
                if (item.getItemId() == R.id.item_CapNhat) {
                    openDialogUpdate(laptop);
                }
                return true;
            }
        };
    }

    public void setRow(int pos, @NonNull AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        Laptop laptop = listLap.get(pos);
        HangLaptop hangLaptop = new HangLaptop("No Data", "No Data", new byte[]{});
        Log.d(TAG, "setRow: Laptop: " + laptop.toString());


        for (int i = 0; i < listHang.size(); i++) {
            HangLaptop getHang = listHang.get(i);
            if (laptop.getMaHangLap().equals(getHang.getMaHangLap())) {
                hangLaptop = getHang;
            }
        }

        ChangeType changeType = new ChangeType();
        Bitmap anhLap = changeType.byteToBitmap(laptop.getAnhLaptop());
        Bitmap anhHang = changeType.byteToBitmap(hangLaptop.getAnhHang());

        author.imgLaptop.setImageBitmap(anhLap);
        author.imgHang.setImageBitmap(anhHang);
        author.name.setText(laptop.getTenLaptop());
        author.gia.setText("Giá tiền: " + laptop.getGiaTien());
        author.soLuong.setText(String.valueOf(laptop.getSoLuong()));
        author.daBan.setText(String.valueOf(laptop.getDaBan()));
    }

    private void openDialogUpdate(Laptop laptop) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inft = ((Activity) context).getLayoutInflater();
        View view = inft.inflate(R.layout.dialog_laptop_manager, null);
        ramSpinner = view.findViewById(R.id.spinner_TSKT);
        hangLapSpinner = view.findViewById(R.id.spinner_HangLaptop);
        textInput_GiaTien = view.findViewById(R.id.textInput_GiaTien);
        textInput_TenLaptop = view.findViewById(R.id.textInput_TenLaptop);
        textInput_SoLuong = view.findViewById(R.id.textInput_SoLuong);
        button_Laptop_Manager = view.findViewById(R.id.button_Laptop_Manager);
        imageView_Laptop = view.findViewById(R.id.imageView_Laptop);

        setTextInput(laptop);
        button_Laptop_Manager.setText("Cập nhật");
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        button_Laptop_Manager.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                Laptop update = getTextInput(laptop);
                if (update != null) {
                    laptopDAO.updateLaptop(update);
                    listLap.set(posLap, update);
                    dialog.dismiss();
                    notifyDataSetChanged();
                }
            }
        });
    }

    private Laptop getTextInput(Laptop laptop) {
        ChangeType changeType = new ChangeType();
        String ten = changeType.deleteSpaceText(textInput_TenLaptop.getEditText().getText().toString());
        String gia = changeType.deleteSpaceText(textInput_GiaTien.getEditText().getText().toString());
        String soluong = changeType.deleteSpaceText(textInput_SoLuong.getEditText().getText().toString());
        String hang = "L" + hangLapSpinner.getSelectedItem().toString();
        String thongso = ramSpinner.getSelectedItem().toString();

        if (ten.equals("")) {
            textInput_TenLaptop.setError("Tên Laptop không được trống!");
            textInput_TenLaptop.setErrorEnabled(true);
            return null;
        } else {
            laptop.setTenLaptop(ten);
            textInput_TenLaptop.setError("");
            textInput_TenLaptop.setErrorEnabled(false);
        }

        if (gia.equals("")) {
            textInput_GiaTien.setError("Giá tiền không được trống!");
            textInput_GiaTien.setErrorEnabled(true);
            return null;
        } else {
            laptop.setGiaTien(changeType.stringToStringMoney(changeType.stringMoneyToInt(gia) + ""));
            textInput_GiaTien.setError("");
            textInput_GiaTien.setErrorEnabled(false);
        }

        if (changeType.stringMoneyToInt(soluong) == 0) {
            textInput_SoLuong.setError("Số lượng phải lớn hơn 0");
            textInput_SoLuong.setErrorEnabled(true);
            return null;
        } else {
            laptop.setSoLuong(Integer.parseInt(soluong));
            textInput_SoLuong.setError("");
            textInput_SoLuong.setErrorEnabled(false);
        }

        laptop.setMaHangLap(hang);
        laptop.setThongSoKT(thongso);
        return laptop;
    }

    private void setTextInput(Laptop laptop) {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(context,
                R.array.ram_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ramSpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context,
                R.array.hang_laptop_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hangLapSpinner.setAdapter(adapter2);

        imageView_Laptop.setImageBitmap(changeType.byteToBitmap(laptop.getAnhLaptop()));
        textInput_TenLaptop.getEditText().setText(laptop.getTenLaptop());
        textInput_GiaTien.getEditText().setText(laptop.getGiaTien());
        textInput_SoLuong.getEditText().setText(String.valueOf(laptop.getSoLuong()));

        if (laptop.getMaHangLap().equals("LDell")) {
            hangLapSpinner.setSelection(0);
        }
        if (laptop.getMaHangLap().equals("LHP")) {
            hangLapSpinner.setSelection(1);
        }
        if (laptop.getMaHangLap().equals("LAsus")) {
            hangLapSpinner.setSelection(2);
        }
        if (laptop.getMaHangLap().equals("LAcer")) {
            hangLapSpinner.setSelection(3);
        }
        if (laptop.getMaHangLap().equals("LMSi")) {
            hangLapSpinner.setSelection(4);
        }
        if (laptop.getMaHangLap().equals("LMacBook")) {
            hangLapSpinner.setSelection(5);
        }
        if (laptop.getThongSoKT().equals("RAM 4GB")) {
            ramSpinner.setSelection(0);
        }
        if (laptop.getThongSoKT().equals("RAM 8GB")) {
            ramSpinner.setSelection(1);
        }
        if (laptop.getThongSoKT().equals("RAM 16GB")) {
            ramSpinner.setSelection(2);
        }
        if (laptop.getThongSoKT().equals("RAM 32GB")) {
            ramSpinner.setSelection(3);
        }
    }

    public int getPosLap() {
        return posLap;
    }

    public void setPosLap(int posLap) {
        this.posLap = posLap;
    }
}