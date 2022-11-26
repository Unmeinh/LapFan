package com.nhom5.quanlylaptop.CreateWallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

import com.nhom5.quanlylaptop.Entity.Bank;
import com.nhom5.quanlylaptop.KH_Adapter.Bank_Adapter;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vi_Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vi_3, container, false);
        ArrayList<Bank> list = new ArrayList<>();

        Bank bank = new Bank(R.drawable.bank_acb, "Ngân hàng Á Châu (ACB)");
        list.add(bank);

        Bank bank0 = new Bank(R.drawable.bank_bidv, " TMCP Đầu tư và Phát triển Việt Nam (BIDV)");
        list.add(bank0);

        Bank bank1 = new Bank(R.drawable.bank_mb, "Ngân hàng Quân đội (MB Bank)");
        list.add(bank1);

        Bank bank2 = new Bank(R.drawable.bank_tp, "Ngân hàng Thương mại Cổ phần Tiên Phong (TP Bank)");
        list.add(bank2);

        Bank bank3 = new Bank(R.drawable.bank_scb, "Ngân hàng thương mại cổ phần Sài Gòn (SCB)");
        list.add(bank3);

        Bank bank4 = new Bank(R.drawable.bank_vib, "Ngân hàng Quốc tế (VIB)");
        list.add(bank4);

        Bank bank5 = new Bank(R.drawable.bank_viettin, "Ngân hàng TMCP Công Thương Việt Nam (VietinBank)");
        list.add(bank5);

        Bank bank6 = new Bank(R.drawable.bank_teckcom, "Ngân hàng thương mại cổ phần Kỹ Thương Việt Nam (Techcombank)");
        list.add(bank6);

        ListView listView = view.findViewById(R.id.listView_Bank);
        Bank_Adapter bankAdapter = new Bank_Adapter(list);
        listView.setAdapter(bankAdapter);
        return view;
    }
}