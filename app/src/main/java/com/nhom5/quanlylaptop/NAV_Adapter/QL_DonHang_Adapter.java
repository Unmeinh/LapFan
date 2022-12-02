package com.nhom5.quanlylaptop.NAV_Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.nhom5.quanlylaptop.Activity.ChiTiet_DonHang_Activity;
import com.nhom5.quanlylaptop.ActivityKH.KH_DanhGia_Activity;
import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.KhachHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.DAO.NhanVienDAO;
import com.nhom5.quanlylaptop.DAO.VoucherDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.KhachHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.Entity.NhanVien;
import com.nhom5.quanlylaptop.Entity.Voucher;
import com.nhom5.quanlylaptop.KH_Adapter.KH_DonHang_Adapter;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class QL_DonHang_Adapter extends RecyclerView.Adapter<QL_DonHang_Adapter.AuthorViewHolder> {

    Context context;
    TextInputLayout tilDate, tilNV, tilKH, tilDC, tilLap, tilSL, tilVou, tilTT;
    AppCompatButton addDHButton;
    int posDH;
    NhanVien nhanVien;
    KhachHang khachHang;
    Laptop laptop;
    Voucher voucher;
    ArrayList<Laptop> listLap;
    ArrayList<KhachHang> listKH;
    ArrayList<DonHang> listDon;
    DonHangDAO donHangDAO;
    NhanVienDAO nhanVienDAO;
    KhachHangDAO khachHangDAO;
    VoucherDAO voucherDAO;
    LaptopDAO laptopDAO;
    TextView countDH;
    ChangeType changeType = new ChangeType();
    String TAG = "QL_DonHang_Adapter_____";

    public QL_DonHang_Adapter(ArrayList<Laptop> listLap, ArrayList<DonHang> listDon, ArrayList<KhachHang> listKH, Context context, TextView countDH) {
        this.listLap = listLap;
        this.listDon = listDon;
        this.listKH = listKH;
        this.context = context;
        this.countDH = countDH;
        donHangDAO = new DonHangDAO(context);
        nhanVienDAO = new NhanVienDAO(context);
        laptopDAO = new LaptopDAO(context);
        voucherDAO = new VoucherDAO(context);
        khachHangDAO = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public QL_DonHang_Adapter.AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup vGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_nva_don_hang, vGroup, false);
        return new QL_DonHang_Adapter.AuthorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QL_DonHang_Adapter.AuthorViewHolder author, @SuppressLint("RecyclerView") final int pos) {
        DonHang donHang = setRow(pos, author);

        author.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (donHang != null) {
                    Intent intent = new Intent(context, ChiTiet_DonHang_Activity.class);
                    final Bundle bundle = new Bundle();
                    bundle.putBinder("donhang", donHang);
                    Log.d(TAG, "onBindViewHolder: DonHang: " + donHang.toString());
                    intent.putExtras(bundle);
                    intent.putExtra("typeUser", "NV");
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Load thông tin sản phẩm lỗi!\nXin vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        author.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosDH(pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (countDH != null) {
            if (listDon.size() > 0) {
                countDH.setText(String.valueOf(listDon.size()));
            } else {
                countDH.setText(String.valueOf(0));
            }
        }
        return listDon.size();
    }

    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tenKH, tenLaptop, phone, money, date;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKH = itemView.findViewById(R.id.textView_TenKH);
            tenLaptop = itemView.findViewById(R.id.textView_TenLaptop);
            phone = itemView.findViewById(R.id.textView_SDT);
            money = itemView.findViewById(R.id.textView_Soluong);
            date = itemView.findViewById(R.id.textView_Date);
            itemView.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(Menu.NONE, R.id.item_CapNhat, Menu.NONE, "Cập nhật");
            MenuItem delete = menu.add(Menu.NONE, R.id.item_Xoa, Menu.NONE, "Xóa");
            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {

            @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                DonHang donHang = listDon.get(getPosDH());

                switch (item.getItemId()) {
                    case R.id.item_Xoa:
                        donHangDAO.deleteDonHang(donHang);
                        listDon.clear();
                        listDon.addAll(donHangDAO.selectDonHang(null, null, null, "ngayMua"));
                        notifyDataSetChanged();
                        break;
                    case R.id.item_CapNhat:
                        donHangDAO = new DonHangDAO(context);
                        nhanVienDAO = new NhanVienDAO(context);
                        laptopDAO = new LaptopDAO(context);
                        voucherDAO = new VoucherDAO(context);
                        khachHangDAO = new KhachHangDAO(context);
                        openDialogUpdate(donHang);
                        break;
                }
                return true;
            }


        };
    }

    public DonHang setRow(int pos, @NonNull QL_DonHang_Adapter.AuthorViewHolder author) {
        ChangeType changeType = new ChangeType();
        Log.d(TAG, "setRow: " + pos);
        DonHang donHang = listDon.get(pos);
        Laptop laptop = new Laptop("No Data", "No Data", "No Data", "No Data", "0", 0, 0, new byte[]{});
        KhachHang khachHang = new KhachHang("No Data", "No Data", "No Data", "No Data", "No Data", "No Data", "No Data",
                "No Data", "No Data", new byte[]{});
        Log.d(TAG, "setRow: DonHang: " + donHang.toString());

        for (int i = 0; i < listLap.size(); i++) {
            Laptop getLap = listLap.get(i);
            if (donHang.getMaLaptop().equals(getLap.getMaLaptop())) {
                laptop = getLap;
            }
        }

        for (int i = 0; i < listKH.size(); i++) {
            KhachHang getKH = listKH.get(i);
            if (donHang.getMaKH().equals(getKH.getMaKH())) {
                khachHang = getKH;
            }
        }

        author.tenKH.setText(changeType.fullNameKhachHang(khachHang));
        author.tenLaptop.setText(laptop.getTenLaptop());
        author.phone.setText(khachHang.getPhone());
        author.money.setText(donHang.getThanhTien());
        author.date.setText(donHang.getNgayMua());
        return donHang;
    }

    private void openDialogUpdate(DonHang donHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inft = ((Activity) context).getLayoutInflater();
        View view = inft.inflate(R.layout.dialog_donhang_manager, null);

        tilDate = view.findViewById(R.id.textInput_Date);
        tilNV = view.findViewById(R.id.textInput_MaNV);
        tilKH = view.findViewById(R.id.textInput_MaKH);
        tilDC = view.findViewById(R.id.textInput_DiaChi);
        tilLap = view.findViewById(R.id.textInput_MaLaptop);
        tilSL = view.findViewById(R.id.textInput_SoLuong);
        tilVou = view.findViewById(R.id.textInput_MaVoucher);
        tilTT = view.findViewById(R.id.textInput_Total);
        addDHButton = view.findViewById(R.id.button_AddDH);
        ChangeType changeType = new ChangeType();


        Date currentTime = Calendar.getInstance().getTime();

        setDialogData(currentTime, donHang);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        setUpListDialog();
        addDHButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                checkInputDonHang();

                String dateSQL = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
                String maNV = donHang.getMaNV();
                if (nhanVien != null) {
                    maNV = nhanVien.getMaNV();
                }
                String maKH = donHang.getMaKH();
                if (khachHang != null) {
                    maKH = khachHang.getMaKH();
                }
                String maLaptop = donHang.getMaLaptop();
                if (laptop != null) {
                    maLaptop = laptop.getMaLaptop();
                }
                String maVou = donHang.getMaVoucher();
                if (voucher != null) {
                    maVou = voucher.getMaVoucher();
                }
                String diachi = changeType.deleteSpaceText(tilDC.getEditText().getText().toString());
                String soluong = changeType.deleteSpaceText(tilSL.getEditText().getText().toString());
                String thanhtien = changeType.deleteSpaceText(tilTT.getEditText().getText().toString());
                donHang.setNgayMua(dateSQL);
                donHang.setMaNV(maNV);
                donHang.setMaKH(maKH);
                donHang.setDiaChi(diachi);
                donHang.setMaLaptop(maLaptop);
                donHang.setSoLuong(Integer.parseInt(soluong));
                donHang.setMaVoucher(maVou);
                donHang.setThanhTien(changeType.stringToStringMoney(changeType.stringMoneyToInt(thanhtien) + ""));

                donHangDAO.updateDonHang(donHang);
                dialog.dismiss();
                listDon.clear();
                listDon.addAll(donHangDAO.selectDonHang(null, null, null, "ngayMua"));
                notifyDataSetChanged();
            }
        });
    }

    private void setDialogData(Date currentTime, DonHang donHang) {
        String dateForm = new SimpleDateFormat("dd/MM/yyyy").format(currentTime);
        tilDate.getEditText().setText(dateForm);
        tilDC.getEditText().setText(donHang.getDiaChi());
        tilSL.getEditText().setText(String.valueOf(donHang.getSoLuong()));
        tilTT.getEditText().setText(donHang.getThanhTien());
        addDHButton.setText("Cập nhật");

        ArrayList<NhanVien> listNV = nhanVienDAO.selectNhanVien(null, "maNV=?", new String[]{donHang.getMaNV()}, null);
        if (listNV != null) {
            NhanVien nv = listNV.get(0);
            tilNV.getEditText().setText(changeType.fullNameNhanVien(nv));
        }

        ArrayList<KhachHang> listKH = khachHangDAO.selectKhachHang(null, "maKH=?", new String[]{donHang.getMaKH()}, null);
        if (listKH != null) {
            KhachHang kh = listKH.get(0);
            tilKH.getEditText().setText(changeType.fullNameKhachHang(kh));
        }

        ArrayList<Laptop> listLap = laptopDAO.selectLaptop(null, "maLaptop=?", new String[]{donHang.getMaLaptop()}, null);
        if (listLap != null) {
            Laptop lap = listLap.get(0);
            tilLap.getEditText().setText(lap.getTenLaptop());
        }

        ArrayList<Voucher> listVou = voucherDAO.selectVoucher(null, "maVoucher=?", new String[]{donHang.getMaVoucher()}, null);
        if (listVou != null) {
            Voucher vou = listVou.get(0);
            tilVou.getEditText().setText(vou.getTenVoucher() + " : Sale: " + vou.getGiamGia());
        }

    }

    private void setUpListDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
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
                        tilNV.getEditText().setText(changeType.fullNameNhanVien(nhanVien));
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
                        tilNV.getEditText().setText(changeType.fullNameNhanVien(nhanVien));
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
                        tilKH.getEditText().setText(changeType.fullNameKhachHang(khachHang));
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
                        tilKH.getEditText().setText(changeType.fullNameKhachHang(khachHang));
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
                        Voucher voucher;
                        voucher = list.get(position);
                        tilVou.getEditText().setText(voucher.getTenVoucher() + " : Sale " + voucher.getGiamGia());
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
                        tilVou.getEditText().setText(voucher.getTenVoucher() + " : Sale " + voucher.getGiamGia());
                        dialog.dismiss();
                    }
                });
            }
        });
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
            kh = changeType.fullNameKhachHang(khachHang);
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
                "Hoàn thành", "false", changeType.stringToStringMoney(tt), sl);
    }

    public int getPosDH() {
        return posDH;
    }

    public void setPosDH(int posDH) {
        this.posDH = posDH;
    }
}