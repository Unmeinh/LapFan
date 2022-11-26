package com.nhom5.quanlylaptop.KH_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nhom5.quanlylaptop.Entity.Bank;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class Bank_Adapter extends BaseAdapter {
    ArrayList<Bank> listBank;

    public Bank_Adapter(ArrayList<Bank> listBank) {
        this.listBank = listBank;
    }

    @Override
    public int getCount() {
        return listBank.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup vGroup) {
        View row = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.cardview_bank, vGroup, false);
        ImageView img = row.findViewById(R.id.imageView_Bank);
        TextView name = row.findViewById(R.id.textView_TenBank);
        RadioButton check = row.findViewById(R.id.radioButton_Bank);

        Bank bank = listBank.get(position);
        img.setImageResource(bank.getImgRes());
        name.setText(bank.getNameBank());
        String selected = Bank.getInstance().getSelected();
        if (selected != null){
            if (selected.equals(bank.getNameBank())){
                check.setChecked(true);
            } else {
                check.setChecked(false);
            }
        }
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Bank.getInstance().setSelected(bank.getNameBank());
                notifyDataSetChanged();
            }
        });

        return row;
    }
}
