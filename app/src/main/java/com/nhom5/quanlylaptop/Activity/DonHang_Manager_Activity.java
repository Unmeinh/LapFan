package com.nhom5.quanlylaptop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.DAO.ThongBaoDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.Entity.ThongBao;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.NAV_Adapter.DH_Manager_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DonHang_Manager_Activity extends AppCompatActivity {

    TextInputLayout tilDate, tilNV, tilKH, tilDC, tilLap, tilSL, tilVou, tilTT;
    AppCompatButton addDHButton;
    Context context = this;
    DonHangDAO donHangDAO;
    LaptopDAO laptopDAO;
    KhachHangDAO khachHangDAO;
    NhanVienDAO nhanVienDAO;
    VoucherDAO voucherDAO;
    NhanVien nhanVien;
    KhachHang khachHang;
    Laptop laptop;
    Voucher voucher;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_manager);
        donHangDAO = new DonHangDAO(context);
        laptopDAO = new LaptopDAO(context);
        khachHangDAO = new KhachHangDAO(context);
        nhanVienDAO = new NhanVienDAO(context);
        voucherDAO = new VoucherDAO(context);

        findViewId();
        setUpListDialog();
        useToolbar();
    }

    private void findViewId() {
        tilDate = findViewById(R.id.textInput_Date);
        tilNV = findViewById(R.id.textInput_MaNV);
        tilKH = findViewById(R.id.textInput_MaKH);
        tilDC = findViewById(R.id.textInput_DiaChi);
        tilLap = findViewById(R.id.textInput_MaLaptop);
        tilSL = findViewById(R.id.textInput_SoLuong);
        tilVou = findViewById(R.id.textInput_MaVoucher);
        tilTT = findViewById(R.id.textInput_Total);
        addDHButton = findViewById(R.id.button_AddDH);


        addDHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonHang donHang = checkInputDonHang();
                if (donHang != null) {
                    int check = donHangDAO.insertDonHang(donHang);
                    if (check == 1) {
                        Toast.makeText(context, "Thêm đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                        addThongBao(donHang);
                        finish();
                    }
                    if (check == -1) {
                        Toast.makeText(context, "Thêm đơn hàng thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void addThongBao(DonHang donHang) {
        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO(context);

        ArrayList<NhanVien> listNV = nhanVienDAO.selectNhanVien(null, "maNV=?", new String[]{donHang.getMaNV()}, null);
        ArrayList<KhachHang> listKH = khachHangDAO.selectKhachHang(null, "maKH=?", new String[]{donHang.getMaKH()}, null);
        ArrayList<Laptop> listLap = laptopDAO.selectLaptop(null, "maLaptop=?", new String[]{donHang.getMaLaptop()}, null);


        if (listNV.size() > 0 && listKH.size() > 0 && listLap.size() > 0) {
            NhanVien getNV = listNV.get(0);
            KhachHang getKH = listKH.get(0);
            Laptop getLap = listLap.get(0);
            ThongBao thongBao = new ThongBao("TB", donHang.getMaKH(), "Quản lý đơn hàng",
                    " Bạn đã được tạo một đơn hàng mới bởi Nhân viên " + getNV.getHoNV() + " " + getNV.getTenNV() + " :\n Đơn hàng " + getLap.getTenLaptop() + "\n Tổng tiền:  " + donHang.getThanhTien(), date);
            thongBaoDAO.insertThongBao(thongBao, "kh");
            ThongBao thongBao1 = new ThongBao("TB", donHang.getMaNV(), "Quản lý đơn hàng",
                    " Bạn đã tạo một đơn hàng mới cho Khách hàng " + getKH.getHoKH() + " " + getKH.getTenKH() + " :\n Đơn hàng " + getLap.getTenLaptop() + "\n Tổng tiền:  " + donHang.getThanhTien(), date);
            thongBaoDAO.insertThongBao(thongBao1, "nv");
            ThongBao thongBao2 = new ThongBao("TB", "admin", "Quản lý đơn hàng",
                    " Nhân viên " + getNV.getHoNV() + " " + getNV.getTenNV() + " đã tạo một đơn hàng mới cho Khách hàng " + getKH.getHoKH() + " " + getKH.getTenKH() + "\n Đơn hàng " + getLap.getTenLaptop() + "\n Tổng tiền:  " + donHang.getThanhTien(), date);
            thongBaoDAO.insertThongBao(thongBao2, "ad");
        }
    }

    private DonHang checkInputDonHang() {
        Date currentTime = Calendar.getInstance().getTime();
        String dateSQL = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
        String dateForm = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);

        tilDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilDate.getEditText().setText(dateForm);
            }
        });

        tilDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilDate.getEditText().setText(dateForm);
            }
        });

        String dc = tilDC.getEditText().getText().toString();
        String tt = tilTT.getEditText().getText().toString();
        String lap = "", kh = "", nv = "", vou = "";
        int sl;
        if (laptop != null) {
            lap = laptop.getTenLaptop();
        }
        if (khachHang != null) {
            kh = khachHang.getHoKH() + " " + khachHang.getTenKH();
        }
        if (nhanVien != null) {
            nv = nhanVien.getHoNV() + " " + nhanVien.getTenNV();
        }
        if (voucher != null) {
            vou = voucher.getTenVoucher();
        }

        if (nv.equals("")) {
            tilNV.setErrorEnabled(true);
            tilNV.setError("Nhân viên không được bỏ trống!");
            return null;
        } else {
            tilNV.setErrorEnabled(false);
            tilNV.setError("");
        }

        if (kh.equals("")) {
            tilKH.setErrorEnabled(true);
            tilKH.setError("Khách hàng không được bỏ trống!");
            return null;
        } else {
            tilKH.setErrorEnabled(false);
            tilKH.setError("");
        }

        if (dc.equals("")) {
            tilDC.setErrorEnabled(true);
            tilDC.setError("Địa chỉ không được bỏ trống!");
            return null;
        } else {
            tilDC.setErrorEnabled(false);
            tilDC.setError("");
        }

        if (lap.equals("")) {
            tilLap.setErrorEnabled(true);
            tilLap.setError("Laptop không được bỏ trống!");
            return null;
        } else {
            tilLap.setErrorEnabled(false);
            tilLap.setError("");
        }

        try {
            sl = Integer.parseInt(tilSL.getEditText().getText().toString());
            tilSL.setErrorEnabled(false);
            tilSL.setError("");
        } catch (Exception e) {
            e.printStackTrace();
            tilSL.setErrorEnabled(true);
            tilSL.setError("Số lượng phải là số!");
            return null;
        }

        if (sl == 0) {
            tilSL.setErrorEnabled(true);
            tilSL.setError("Số lượng laptop phải lớn hơn 0");
            return null;
        } else {
            tilSL.setErrorEnabled(false);
            tilSL.setError("");
        }

        if (laptop != null) {
            if (sl > laptop.getSoLuong()) {
                tilSL.setErrorEnabled(true);
                tilSL.setError("Số lượng laptop còn lại: " + laptop.getSoLuong());
                return null;
            } else {
                tilSL.setErrorEnabled(false);
                tilSL.setError("");
            }
        }

        if (vou.equals("")) {
            tilVou.setErrorEnabled(true);
            tilVou.setError("Voucher không được bỏ trống!");
            return null;
        } else {
            tilVou.setErrorEnabled(false);
            tilVou.setError("");
        }

        if (tt.equals("")) {
            tilTT.setErrorEnabled(true);
            tilTT.setError("Thành tiền không được bỏ trống!");
            return null;
        } else {
            tilTT.setErrorEnabled(false);
            tilTT.setError("");
        }

        return new DonHang("", nhanVien.getMaNV(), khachHang.getMaKH(), laptop.getMaLaptop(),
                voucher.getMaVoucher(), "No Data", dc, dateSQL, "Thanh toán tại cửa hàng",
                "false", changeType.stringToStringMoney(tt), sl);
    }

    private void setUpListDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_list, null);
        TextView title = view.findViewById(R.id.textView_Title_Dialog);
        ListView listView = view.findViewById(R.id.listView_Item_DH);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);

        tilNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Nhân viên");
                dialog.show();
                ArrayList<NhanVien> list = nhanVienDAO.selectNhanVien(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(null, list, null, null);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        nhanVien = list.get(position);
                        tilNV.getEditText().setText(nhanVien.getHoNV() + " " + nhanVien.getTenNV());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilNV.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Nhân viên");
                dialog.show();
                ArrayList<NhanVien> list = nhanVienDAO.selectNhanVien(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(null, list, null, null);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        nhanVien = list.get(position);
                        tilNV.getEditText().setText(nhanVien.getHoNV() + " " + nhanVien.getTenNV());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Khách hàng");
                dialog.show();
                ArrayList<KhachHang> list = khachHangDAO.selectKhachHang(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(null, null, list, null);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        khachHang = list.get(position);
                        tilKH.getEditText().setText(khachHang.getHoKH() + " " + khachHang.getTenKH());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilKH.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Khách hàng");
                dialog.show();
                ArrayList<KhachHang> list = khachHangDAO.selectKhachHang(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(null, null, list, null);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        khachHang = list.get(position);
                        tilKH.getEditText().setText(khachHang.getHoKH() + " " + khachHang.getTenKH());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Laptop");
                dialog.show();
                ArrayList<Laptop> list = laptopDAO.selectLaptop(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(list, null, null, null);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        laptop = list.get(position);
                        tilLap.getEditText().setText(laptop.getTenLaptop());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilLap.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Laptop");
                dialog.show();
                ArrayList<Laptop> list = laptopDAO.selectLaptop(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(list, null, null, null);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        laptop = list.get(position);
                        tilLap.getEditText().setText(laptop.getTenLaptop());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilVou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Voucher");
                dialog.show();
                ArrayList<Voucher> list = voucherDAO.selectVoucher(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(null, null, null, list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        voucher = list.get(position);
                        tilVou.getEditText().setText(voucher.getTenVoucher() + " " + voucher.getGiamGia());
                        dialog.dismiss();
                    }
                });
            }
        });

        tilVou.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Danh sách Voucher");
                dialog.show();
                ArrayList<Voucher> list = voucherDAO.selectVoucher(null, null, null, null);
                DH_Manager_Adapter adapter = new DH_Manager_Adapter(null, null, null, list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        voucher = list.get(position);
                        tilVou.getEditText().setText(voucher.getTenVoucher() + " " + voucher.getGiamGia());
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Thêm Đơn hàng");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}