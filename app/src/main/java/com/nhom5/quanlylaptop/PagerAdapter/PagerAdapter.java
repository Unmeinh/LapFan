package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.FragmentKH.KHAccountFragment;
import com.nhom5.quanlylaptop.FragmentKH.KHGioHangFragment;
import com.nhom5.quanlylaptop.FragmentKH.KHHomeFragment;
import com.nhom5.quanlylaptop.FragmentKH.KHNotifiFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {
    String TAG = "PagerAdapter_____";

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int n) {
        Fragment frag = null;
        switch (n) {
            case 0:
                frag = new KHHomeFragment();
                break;
            case 1:
                frag = new KHNotifiFragment();
                break;
            case 2:
                frag = new KHGioHangFragment();
                break;
            case 3:
                frag = new KHAccountFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
