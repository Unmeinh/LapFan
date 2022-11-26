package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.FragmentNV_Admin.Add_Staff_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.Admin_ThongBao_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NV_ThongBao_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NVA_Home_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_ThongKe_Fragment;


public class Admin_PagerAdapter_Bottom extends FragmentStatePagerAdapter {
    String TAG = "PagerAdapter_____";

    public Admin_PagerAdapter_Bottom(@NonNull FragmentManager fm) {
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
                frag = new Admin_ThongBao_Fragment();
                break;
            case 2:
                frag = new QL_ThongKe_Fragment();
                break;
            case 3:
                frag = new Add_Staff_Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
