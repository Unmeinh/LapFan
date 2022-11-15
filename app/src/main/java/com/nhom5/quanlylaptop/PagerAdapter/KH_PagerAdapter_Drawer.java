package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.FragmentKH.KH_GioHang_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_Home_Fragment;
import com.nhom5.quanlylaptop.FragmentKH.KH_Notifi_Fragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopDellFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopAsusFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopHPFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopAcerFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.LaptopMsiFragment;
import com.nhom5.quanlylaptop.FragmentLaptop.MacBookFragment;


public class KH_PagerAdapter_Drawer extends FragmentStatePagerAdapter {
    String TAG = "PagerAdapter_____";

    public KH_PagerAdapter_Drawer(@NonNull FragmentManager fm) {
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
                frag = new KH_Notifi_Fragment();
                break;
            case 2:
                frag = new LaptopDellFragment();
                break;
            case 3:
                frag = new LaptopHPFragment();
                break;
            case 4:
                frag = new LaptopAsusFragment();
                break;
            case 5:
                frag = new LaptopAcerFragment();
                break;
            case 6:
                frag = new LaptopMsiFragment();
                break;
            case 7:
                frag = new MacBookFragment();
                break;
            case 8:
                frag = new KH_GioHang_Fragment();
                break;
            default:
                frag = new KH_Home_Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 9;
    }
}
