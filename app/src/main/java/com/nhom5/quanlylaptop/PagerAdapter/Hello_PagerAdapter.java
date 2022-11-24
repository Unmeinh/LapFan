package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.HelloWorld.Hello_Fragment1;
import com.nhom5.quanlylaptop.HelloWorld.Hello_Fragment2;
import com.nhom5.quanlylaptop.HelloWorld.Hello_Fragment3;
import com.nhom5.quanlylaptop.HelloWorld.Hello_Fragment4;

public class Hello_PagerAdapter extends FragmentStatePagerAdapter {
    public Hello_PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Hello_Fragment1();
            case 1:
                return new Hello_Fragment2();
            case 2:
                return new Hello_Fragment3();
            case 3:
                return new Hello_Fragment4();
            default:
                return new Hello_Fragment1();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}