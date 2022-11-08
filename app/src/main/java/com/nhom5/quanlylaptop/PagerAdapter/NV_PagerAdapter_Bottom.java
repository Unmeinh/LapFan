package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.FragmentKH.KH_Account_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_GioHang_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_Home_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_Notifi_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NVA_DonHang_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NVA_Home_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NV_Account_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NV_Notifi_Fragment;


public class NV_PagerAdapter_Bottom extends FragmentStatePagerAdapter {
    String TAG = "PagerAdapter_____";

    public NV_PagerAdapter_Bottom(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int n) {
        Fragment frag = null;
        switch (n) {
            case 0:
                frag = new NVA_Home_Fragment();
                break;
            case 1:
                frag = new NV_Notifi_Fragment();
                break;
            case 2:
                frag = new NVA_DonHang_Fragment();
                break;
            case 3:
                frag = new NV_Account_Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
