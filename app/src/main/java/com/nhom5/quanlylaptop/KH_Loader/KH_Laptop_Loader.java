package com.nhom5.quanlylaptop.KH_Loader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopAcerFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopAsusFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopDellFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopHPFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopMsiFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.MacBookFragment;
import com.nhom5.quanlylaptop.KH_Adapter.KH_Laptop_Adapter;
import com.nhom5.quanlylaptop.R;
import java.util.ArrayList;

public class KH_Laptop_Loader extends AsyncTask<String, Void, ArrayList<Laptop>> {
    @SuppressLint("StaticFieldLeak")
    LaptopDellFragment laptopDellFragment = null;
    LaptopHPFragment laptopHPFragment = null;
    LaptopAcerFragment laptopAcerFragment = null;
    LaptopAsusFragment laptopAsusFragment = null;
    LaptopMsiFragment laptopMsiFragment = null;
    MacBookFragment macBookFragment = null;
    @SuppressLint("StaticFieldLeak")
    Context context;
    String TAG = "LaptopLoader_____";
    LaptopDAO laptopDAO;

    public KH_Laptop_Loader(LaptopDellFragment laptopDellFragment, Context context) {
        this.laptopDellFragment = laptopDellFragment;
        this.context = context;
    }

    public KH_Laptop_Loader(LaptopHPFragment laptopHPFragment, Context context) {
        this.laptopHPFragment = laptopHPFragment;
        this.context = context;
    }

    public KH_Laptop_Loader(LaptopAcerFragment laptopAcerFragment, Context context) {
        this.laptopAcerFragment = laptopAcerFragment;
        this.context = context;
    }

    public KH_Laptop_Loader(LaptopAsusFragment laptopAsusFragment, Context context) {
        this.laptopAsusFragment = laptopAsusFragment;
        this.context = context;
    }

    public KH_Laptop_Loader(LaptopMsiFragment laptopMsiFragment, Context context) {
        this.laptopMsiFragment = laptopMsiFragment;
        this.context = context;
    }

    public KH_Laptop_Loader(MacBookFragment macBookFragment, Context context) {
        this.macBookFragment = macBookFragment;
        this.context = context;
    }

    @Override
    protected ArrayList<Laptop> doInBackground(String... strings) {

        laptopDAO = new LaptopDAO(context);
        String maHangLap = strings[0];

        return laptopDAO.selectLaptop(null, "maHangLap=?", new String[]{maHangLap}, null);
    }

    @Override
    protected void onPostExecute(ArrayList<Laptop> listLap) {
        super.onPostExecute(listLap);
        laptopDAO = new LaptopDAO(context);

        if (macBookFragment != null){
            RecyclerView reViewMac = macBookFragment.getActivity().findViewById(R.id.recyclerView_Macbook);
            setupReView(listLap, reViewMac);
        } else if (laptopHPFragment != null){
            RecyclerView reViewHP = laptopHPFragment.getActivity().findViewById(R.id.recyclerView_Laptop_HP);
            setupReView(listLap, reViewHP);
        } else if (laptopAcerFragment != null){
            RecyclerView reViewAcer = laptopAcerFragment.getActivity().findViewById(R.id.recyclerView_Laptop_Acer);
            setupReView(listLap, reViewAcer);
        } else if (laptopAsusFragment != null){
            RecyclerView reViewAsus = laptopAsusFragment.getActivity().findViewById(R.id.recyclerView_Laptop_Asus);
            setupReView(listLap, reViewAsus);
        } else if (laptopMsiFragment != null){
            RecyclerView reViewMsi = laptopMsiFragment.getActivity().findViewById(R.id.recyclerView_Laptop_Msi);
            setupReView(listLap, reViewMsi);
        } else if (laptopDellFragment != null){
            RecyclerView reViewDell = laptopDellFragment.getActivity().findViewById(R.id.recyclerView_Laptop_Dell);
            setupReView(listLap, reViewDell);
        }

    }

    private void setupReView(ArrayList<Laptop> listLap, RecyclerView recyclerView){
        KH_Laptop_Adapter kh_laptop_adapter = new KH_Laptop_Adapter(listLap, context);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(kh_laptop_adapter);
    }

}
