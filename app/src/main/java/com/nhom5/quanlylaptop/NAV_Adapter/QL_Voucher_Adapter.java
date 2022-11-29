package com.nhom5.quanlylaptop.NAV_Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.util.ArrayList;

public class QL_Voucher_Adapter extends RecyclerView.Adapter<QL_Voucher_Adapter.AuthorViewHolder> {

    Context context;
    int posVou;
    ArrayList<Voucher> listVou;
    VoucherDAO voucherDAO;
    String TAG = "QL_Voucher_Adapter_____";

    public QL_Voucher_Adapter(ArrayList<Voucher> listVou, Context context) {
        this.listVou = listVou;
        this.context = context;
        voucherDAO = new VoucherDAO(context);
    }

    @NonNull
    @Override
    public QL_Voucher_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_voucher, vGroup, false);
        return new QL_Voucher_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QL_Voucher_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        Voucher voucher = setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, voucher.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        author.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosVou(pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVou.size();
    }

    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView name, date, ma, sale;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_TenVoucher);
            date = itemView.findViewById(R.id.textView_Date_Voucher);
            ma = itemView.findViewById(R.id.textView_MaVoucher);
            sale = itemView.findViewById(R.id.textView_GiamGia);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(Menu.NONE, R.id.item_CapNhat, Menu.NONE, "Cập nhật");
            MenuItem delete = menu.add(Menu.NONE, R.id.item_Xoa, Menu.NONE, "Xóa");
            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Voucher voucher = listVou.get(getPosVou());

                switch (item.getItemId()) {
                    case R.id.item_Xoa:
                        voucherDAO.deleteVoucher(voucher);
                        listVou.clear();
                        listVou.addAll(voucherDAO.selectVoucher(null, null, null, null));
                        notifyDataSetChanged();
                        break;
                    case R.id.item_CapNhat:
                        openDialogUpdate(voucher);
                        break;
                }

                return true;
            }
        };
    }

    public Voucher setRow(int pos, @NonNull QL_Voucher_Adapter.AuthorViewHolder author) {
        Log.d(TAG, "setRow: " + pos);
        Voucher voucher = listVou.get(pos);

        author.sale.setText("Giảm giá\n" + voucher.getGiamGia());
        author.name.setText(voucher.getTenVoucher());
        if (voucher.getNgayBD().equals(voucher.getNgayKT())) {
            author.date.setText("Duy nhất trong\n" + voucher.getNgayBD());
        } else {
            author.date.setText("Từ  " + voucher.getNgayBD() + "\nđến " + voucher.getNgayKT());
        }
        return voucher;
    }

    @Override
    public void onViewRecycled(@NonNull QL_Voucher_Adapter.AuthorViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    private void openDialogUpdate(Voucher voucher) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inft = ((Activity) context).getLayoutInflater();
        View view = inft.inflate(R.layout.dialog_add_edit_voucher, null);

        TextView title = view.findViewById(R.id.textView_Title_Dialog);
        TextInputLayout textInput_Name = view.findViewById(R.id.textInput_Name);
        TextInputLayout textInput_GiamGia = view.findViewById(R.id.textInput_GiamGia);
        TextInputLayout textInput_NSX = view.findViewById(R.id.textInput_NSX);
        TextInputLayout textInput_HSD = view.findViewById(R.id.textInput_HSD);
        AppCompatButton button = view.findViewById(R.id.button_Dialog);
        ChangeType changeType = new ChangeType();

        title.setText("Cập nhật Voucher");
        textInput_Name.getEditText().setText(voucher.getTenVoucher());
        textInput_GiamGia.getEditText().setText(voucher.getGiamGia());
        textInput_NSX.getEditText().setText(voucher.getNgayBD());
        textInput_HSD.getEditText().setText(voucher.getNgayKT());
        button.setText("Cập nhật");

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                String tenVou = changeType.deleteSpaceText(textInput_Name.getEditText().getText().toString());
                String giamGia = changeType.deleteSpaceText(textInput_GiamGia.getEditText().getText().toString());
                String nbd = changeType.deleteSpaceText(textInput_NSX.getEditText().getText().toString());
                String nkt = changeType.deleteSpaceText(textInput_HSD.getEditText().getText().toString());
                voucher.setTenVoucher(tenVou);
                voucher.setGiamGia(giamGia);
                voucher.setNgayBD(nbd);
                voucher.setNgayKT(nkt);

                voucherDAO.updateVoucher(voucher);
                dialog.dismiss();
                listVou.clear();
                listVou.addAll(voucherDAO.selectVoucher(null, null, null, null));
                notifyDataSetChanged();
            }
        });

    }

    public int getPosVou() {
        return posVou;
    }

    public void setPosVou(int posVou) {
        this.posVou = posVou;
    }
}