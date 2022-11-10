package com.nhom5.quanlylaptop.PagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nhom5.quanlylaptop.FragmentNV_Admin.NVA_FPTShop_Fragment;
import com.nhom5.quanlylaptop.FragmentNV_Admin.NVA_Home_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_DonHang_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_KhachHang_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Laptop_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_NhanVien_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_ThongKe_Fragment;
import com.nhom5.quanlylaptop.FragmentQuanLy.QL_Voucher_Fragment;


public class Admin_PagerAdapter_Drawer extends FragmentStatePagerAdapter {
    String TAG = "PagerAdapter_____";

    public Admin_PagerAdapter_Drawer(@NonNull FragmentManager fm) {
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
                frag = new NVA_FPTShop_Fragment();
                break;
            case 2:
                frag = new QL_Laptop_Fragment();
                break;
            case 3:
                frag = new QL_DonHang_Fragment();
                break;
            case 4:
                frag = new QL_KhachHang_Fragment();
                break;
            case 5:
                frag = new QL_Voucher_Fragment();
                break;
            case 6:
                frag = new QL_NhanVien_Fragment();
                break;
            case 7:
                frag = new QL_ThongKe_Fragment();
                break;
            default:
                frag = new NVA_Home_Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 8;
    }
}
