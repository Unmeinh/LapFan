package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.FragmentKH.KH_Account_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_GioHang_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_Home_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_ThongBao_Fragment;


public class KH_PagerAdapter_Bottom extends FragmentStatePagerAdapter {
    String TAG = "PagerAdapter_____";

    public KH_PagerAdapter_Bottom(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int n) {
        Fragment frag = null;
        switch (n) {
            case 0:
                frag = new KH_Home_Fragment();
                break;
            case 1:
                frag = new KH_ThongBao_Fragment();
                break;
            case 2:
                frag = new KH_GioHang_Fragment();
                break;
            case 3:
                frag = new KH_Account_Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
