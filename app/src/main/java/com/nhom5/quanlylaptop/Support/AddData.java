package com.nhom5.quanlylaptop.Support;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.IdData;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.R;

import java.util.ArrayList;

public class AddData {
    Context context;
    NhanVienDAO nhanVienDAO;
    DonHangDAO donHangDAO;
    LaptopDAO laptopDAO;
    ChangeType changeType = new ChangeType();
    String TAG = "AddData_____";

    public AddData(Context context) {
        this.context = context;
        nhanVienDAO = new NhanVienDAO(context);
        donHangDAO = new DonHangDAO(context);
        laptopDAO = new LaptopDAO(context);
    }

    public void addDataDoanhSo(ArrayList<NhanVien> listNV) {
        if (listNV != null) {
            if (listNV.size() > 0) {
                for (int i = 0; i < listNV.size(); i++) {
                    NhanVien nhanVien = listNV.get(i);
                    ArrayList<DonHang> listDon = donHangDAO.selectDonHang(null, "maNV=?", new String[]{nhanVien.getMaNV()}, null);

                    int doanhSo = 0;
                    int cout = 0;
                    if (listDon != null) {
                        if (listDon.size() > 0) {
                            for (int j = 0; j < listDon.size(); j++) {
                                doanhSo += changeType.stringMoneyToInt(listDon.get(j).getThanhTien());
                            }
                            cout = listDon.size();
                        }
                    }

                    NhanVien update = new NhanVien(nhanVien.getMaNV(), nhanVien.getHoNV(), nhanVien.getTenNV(), nhanVien.getGioiTinh(),
                            nhanVien.getEmail(), nhanVien.getMatKhau(), nhanVien.getQueQuan(), nhanVien.getPhone(),
                            doanhSo, cout, nhanVien.getAvatar());
                    nhanVienDAO.updateNhanVien(update);
                }
            }
        }
    }

    public void addDemoLaptopDell() {
        Bitmap bm0 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/260171/dell-gaming-g15-5515-r5-p105f004dgr-291121-114930-600x600.jpg");
        if (bm0 != null) {
            Laptop lp0 = new Laptop("LP0", "LDell", "Laptop Dell Gaming G15 5515 R5 5600H"
                    , "RAM 16GB", "23.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm0)));
            laptopDAO.insertLaptop(lp0);
        }

        Bitmap bm1 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/264370/dell-inspiron-15-3511-i3-1115g4-4gb-256gb-600x600.jpg");
        if (bm1 != null) {
            Laptop lp1 = new Laptop("LP1", "LDell", "Laptop Dell Inspiron 15 3511 i3 1115G4"
                    , "RAM 4GB", "12.090.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm1)));
            laptopDAO.insertLaptop(lp1);
        }

        Bitmap bm2 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/271090/dell-gaming-alienware-m15-r6-i7-11800h-32gb-1tb-ssd-8gb-600x600.jpg");
        if (bm2 != null) {
            Laptop lp2 = new Laptop("LP2", "LDell", "Laptop Dell Gaming Alienware m15 R6 i7 11800H"
                    , "RAM 32GB", "61.640.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm2)));
            laptopDAO.insertLaptop(lp2);
        }

        Bitmap bm3 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/271540/dell-gaming-g15-5511-i7-p105f006agr-140222-091722-600x600.jpg");
        if (bm0 != null) {
            Laptop lp3 = new Laptop("LP3", "LDell", "Laptop Dell Gaming G15 5511 i7 11800H"
                    , "RAM 8GB", "33.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm3)));
            laptopDAO.insertLaptop(lp3);
        }

        Bitmap bm4 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/292640/dell-xps-13-plus-9320-i7-5cg56-thumb-600x600.jpg");
        if (bm4 != null) {
            Laptop lp4 = new Laptop("LP4", "LDell", "Laptop Dell XPS 13 Plus 9320 i7 1260P"
                    , "RAM 16GB", "59.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm4)));
            laptopDAO.insertLaptop(lp4);
        }
    }

    public void addDemoLaptopHP() {
        Bitmap bm0 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/288400/hp-victus-16-d0292tx-i5-5z9r3pa-thumb-600x600.jpg");
        if (bm0 != null) {
            Laptop lp0 = new Laptop("LP5", "LHP", "Laptop HP VICTUS 16 d0292TX i5 11400H"
                    , "RAM 8GB", "26.590.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm0)));
            laptopDAO.insertLaptop(lp0);
        }

        Bitmap bm1 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/296789/hp-envy-x360-13-bf0090tu-i7-76b13pa-101122-093057-600x600.jpg");
        if (bm1 != null) {
            Laptop lp1 = new Laptop("LP6", "LHP", "Laptop HP Envy X360 13 bf0090TU i7 1250U"
                    , "RAM 16GB", "32.090.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm1)));
            laptopDAO.insertLaptop(lp1);
        }

        Bitmap bm2 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/266157/hp-envy-x360-convert-13-ay1056au-r7-601q8pa-170322-023537-600x600.jpg");
        if (bm2 != null) {
            Laptop lp2 = new Laptop("LP7", "LHP", "Laptop HP Envy x360 Convert 13 ay1056AU R7 5800U"
                    , "RAM 8GB", "25.890.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm2)));
            laptopDAO.insertLaptop(lp2);
        }

        Bitmap bm3 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/285965/hp-pavilion-x360-14-ek0055tu-i7-6l293pa-270822-110932-600x600.jpg");
        if (bm0 != null) {
            Laptop lp3 = new Laptop("LP8", "LHP", "Laptop HP Pavilion X360 14 ek0055TU i7 1255U"
                    , "RAM 16GB", "25.090.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm3)));
            laptopDAO.insertLaptop(lp3);
        }

        Bitmap bm4 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/268676/hp-pavilion-x360-14-dy0171tu-i3-4y1d6pa-170322-015258-600x600.jpg");
        if (bm4 != null) {
            Laptop lp4 = new Laptop("LP9", "LHP", "Laptop HP Pavilion X360 14 dy0171TU i3 1125G4"
                    , "RAM 4GB", "13.690.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm4)));
            laptopDAO.insertLaptop(lp4);
        }
    }

    public void addDemoLaptopAcer() {
        Bitmap bm0 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/269314/acer-swift-x-sfx16-51g-516q-i5-nxayksv002-120122-023135-600x600.jpg");
        if (bm0 != null) {
            Laptop lp0 = new Laptop("LP10", "LAcer", "Laptop Acer Swift X SFX16 51G 516Q i5 11320H"
                    , "RAM 16GB", "28.590.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm0)));
            laptopDAO.insertLaptop(lp0);
        }

        Bitmap bm1 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/283458/acer-nitro-5-tiger-an515-58-773y-i7-nhqfksv001-thumb-600x600.jpg");
        if (bm1 != null) {
            Laptop lp1 = new Laptop("LP11", "LAcer", "Laptop Acer Nitro 5 Tiger AN515 58 773Y i7 12700H"
                    , "RAM 8GB", "31.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm1)));
            laptopDAO.insertLaptop(lp1);
        }

        Bitmap bm2 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/260058/acer-nitro-5-gaming-an515-57-720a-i7-nhqeqsv004-171121-024959-600x600.jpg");
        if (bm2 != null) {
            Laptop lp2 = new Laptop("LP12", "LAcer", "Laptop Acer Nitro 5 Gaming AN515 57 720A i7 11800H"
                    , "RAM 8GB", "28.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm2)));
            laptopDAO.insertLaptop(lp2);
        }

        Bitmap bm3 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/269313/acer-swift-3-sf314-511-55qe-i5-nxabnsv003-120122-022600-600x600.jpg");
        if (bm0 != null) {
            Laptop lp3 = new Laptop("LP13", "LAcer", "Laptop Acer Swift 3 SF314 511 55QE i5 1135G7"
                    , "RAM 16GB", "22.590.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm3)));
            laptopDAO.insertLaptop(lp3);
        }

        Bitmap bm4 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/273432/acer-aprise-a315-57g-32qp-i3-1005g1-4gb-256gb-2gb-mx330-010322-044114-600x600.jpg");
        if (bm4 != null) {
            Laptop lp4 = new Laptop("LP14", "LAcer", "Laptop Acer Aspire A315 57G 32QP i3 1005G1"
                    , "RAM 4GB", "13.190.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm4)));
            laptopDAO.insertLaptop(lp4);
        }
    }

    public void addDemoLaptopAsus() {
        Bitmap bm0 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/279259/asus-tuf-gaming-fx506lhb-i5-hn188w-600x600.jpeg");
        if (bm0 != null) {
            Laptop lp0 = new Laptop("LP15", "LAsus", "Laptop Asus TUF Gaming FX506LHB i5 10300H"
                    , "RAM 8GB", "18.790.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm0)));
            laptopDAO.insertLaptop(lp0);
        }

        Bitmap bm1 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/274539/asus-gaming-rog-flow-z13-gz301z-i7-ld110w-160322-120057-600x600.jpg");
        if (bm1 != null) {
            Laptop lp1 = new Laptop("LP16", "LAsus", "Laptop Asus Gaming ROG Flow Z13 GZ301Z i7 12700H"
                    , "RAM 16GB", "48.690.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm1)));
            laptopDAO.insertLaptop(lp1);
        }

        Bitmap bm2 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/284507/asus-rog-strix-gaming-g513r-r7-hn038w-170822-061143-600x600.jpg");
        if (bm2 != null) {
            Laptop lp2 = new Laptop("LP17", "LAsus", "Laptop Asus ROG Strix Gaming G513R R7 6800H"
                    , "RAM 8GB", "28.690.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm2)));
            laptopDAO.insertLaptop(lp2);
        }

        Bitmap bm3 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/268667/asus-zenbook-ux425e-i7-1165g7-16gb-600x600.jpg");
        if (bm0 != null) {
            Laptop lp3 = new Laptop("LP18", "LAsus", "Laptop Asus ZenBook UX425E i7 1165G7"
                    , "RAM 16GB", "27.190.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm3)));
            laptopDAO.insertLaptop(lp3);
        }

        Bitmap bm4 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/298373/asus-expertbook-b5402cba-i5-ki0353w-thumb-600x600.jpg");
        if (bm4 != null) {
            Laptop lp4 = new Laptop("LP19", "LAsus", "Asus ExpertBook B5402CB i5 1240P"
                    , "RAM 16GB", "26.990.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm4)));
            laptopDAO.insertLaptop(lp4);
        }
    }

    public void addDemoLaptopMsi() {
        Bitmap bm0 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/256266/msi-gaming-modern-14-b11sbu-i5-669vn-600x600.jpg");
        if (bm0 != null) {
            Laptop lp0 = new Laptop("LP20", "LMSi", "Laptop MSI Modern 14 B11SBU i5 1155G7"
                    , "RAM 8GB", "15.790.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm0)));
            laptopDAO.insertLaptop(lp0);
        }

        Bitmap bm1 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/264029/msi-gaming-bravo-15-b5dd-r5-5600h-8gb-512gb-4gb-600x600.jpg");
        if (bm1 != null) {
            Laptop lp1 = new Laptop("LP21", "LMSi", "Laptop MSI Gaming Bravo 15 B5DD R5 5600H"
                    , "RAM 8GB", "18.090.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm1)));
            laptopDAO.insertLaptop(lp1);
        }

        Bitmap bm2 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/249151/msi-gaming-ge66-raider-11uh-i7-259vn-600x600.jpg");
        if (bm2 != null) {
            Laptop lp2 = new Laptop("LP22", "LMSi", "Laptop MSI Gaming GE66 Raider 11UH i7 11800H"
                    , "RAM 32GB", "77.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm2)));
            laptopDAO.insertLaptop(lp2);
        }

        Bitmap bm3 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/249152/msi-gaming-ge66-raider-11ug-i7-258vn-600x600.jpg");
        if (bm0 != null) {
            Laptop lp3 = new Laptop("LP23", "LMSi", "Laptop MSI Gaming GE66 Raider 11UG i7 11800H"
                    , "RAM 16GB", "59.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm3)));
            laptopDAO.insertLaptop(lp3);
        }

        Bitmap bm4 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/274783/msi-creator-z16-a12uet-i7-036vn-200322-110544-600x600.jpg");
        if (bm4 != null) {
            Laptop lp4 = new Laptop("LP24", "LMSi", "Laptop MSI Creator Z16 A12UET i7 12700H"
                    , "RAM 16GB", "60.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm4)));
            laptopDAO.insertLaptop(lp4);
        }
    }

    public void addDemoLaptopMac() {
        Bitmap bm0 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/289472/apple-macbook-air-m2-2022-16gb-256gb-thumb-600x600.jpg");
        if (bm0 != null) {
            Laptop lp0 = new Laptop("LP25", "LMac", "Laptop Apple MacBook Air M2 2022 16GB"
                    , "RAM 16GB", "38.990.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm0)));
            laptopDAO.insertLaptop(lp0);
        }

        Bitmap bm1 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/253636/apple-macbook-pro-16-m1-pro-2021-10-core-cpu-600x600.jpg");
        if (bm1 != null) {
            Laptop lp1 = new Laptop("LP26", "LMac", "Laptop Apple MacBook Pro 16 M1 Pro 2021 10 core-CPU"
                    , "RAM 16GB", "66.990.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm1)));
            laptopDAO.insertLaptop(lp1);
        }

        Bitmap bm2 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/253582/apple-macbook-pro-16-m1-max-2021-1.jpg");
        if (bm2 != null) {
            Laptop lp2 = new Laptop("LP27", "LMac", "Laptop Apple MacBook Pro 16 M1 Max 2021 10 core-CPU"
                    , "RAM 32GB", "92.990.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm2)));
            laptopDAO.insertLaptop(lp2);
        }

        Bitmap bm3 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/282828/apple-macbook-pro-13-inch-m2-2022-1.jpg");
        if (bm0 != null) {
            Laptop lp3 = new Laptop("LP28", "LMac", "Laptop Apple MacBook Pro M2 2022 8GB"
                    , "RAM 8GB", "35.990.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm3)));
            laptopDAO.insertLaptop(lp3);
        }

        Bitmap bm4 = changeType.urlToBitmap(context, "https://cdn.tgdd.vn/Products/Images/44/231244/grey-1-org.jpg");
        if (bm4 != null) {
            Laptop lp4 = new Laptop("LP29", "LMac", "Laptop Apple MacBook Air M1 2020 8GB"
                    , "RAM 8GB", "27.490.000₫", changeType.checkByteInput(changeType.bitmapToByte(bm4)));
            laptopDAO.insertLaptop(lp4);
        }
    }
}
