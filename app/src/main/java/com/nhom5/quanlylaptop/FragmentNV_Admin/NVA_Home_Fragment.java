package com.nhom5.quanlylaptop.FragmentNV_Admin;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhom5.quanlylaptop.DAO.DonHangDAO;
import com.nhom5.quanlylaptop.DAO.LaptopDAO;
import com.nhom5.quanlylaptop.Entity.DonHang;
import com.nhom5.quanlylaptop.Entity.Laptop;
import com.nhom5.quanlylaptop.R;
import com.nhom5.quanlylaptop.Support.AddData;
import com.nhom5.quanlylaptop.Support.ChangeType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NVA_Home_Fragment extends Fragment {

    LinearLayout changeTime;
    TextView textView_Date, textView_GiaTien;
    String getDate;
    AddData addData;
    ChangeType changeType = new ChangeType();
    ArrayList<Laptop> listLap = new ArrayList<>();
    ArrayList<DonHang> listDon = new ArrayList<>();
    LaptopDAO laptopDAO;
    DonHangDAO donHangDAO;
    String getMonth;
    String TAG = "NVA_Home_Fragment_____";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nva_home, container, false);
        changeTime = view.findViewById(R.id.change_Time);
        textView_Date = view.findViewById(R.id.textView_Date);
        textView_GiaTien = view.findViewById(R.id.textView_GiaTien);
        addData = new AddData(getContext());
        laptopDAO = new LaptopDAO(getContext());
        donHangDAO = new DonHangDAO(getContext());
        listLap = laptopDAO.selectLaptop(null, null, null, null);

        setDoanhThuCreate();
        onclickChangeTime();
        sortLaptop(view);
        setSLDonHang(view);
        return view;
    }

    private void setDoanhThuCreate() {
        Date currentTime = Calendar.getInstance().getTime();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(currentTime));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(currentTime));
        if (month < 10) {
            getMonth = "0" + (month);
        } else {
            getMonth = String.valueOf(month);
        }
        textView_Date.setText("Tháng " + getMonth + "/" + year);
        setDoanhThu(changeType.intDateToStringDate(month, year));
    }

    private void setSLDonHang(View view) {
        TextView slDHText = view.findViewById(R.id.textView_Soluong_DH);
        TextView slDHNum = view.findViewById(R.id.textView_Soluong);
        listDon = donHangDAO.selectDonHang(null, null, null, null);
        slDHText.setText(listDon.size() + " đơn hàng");
        slDHNum.setText(String.valueOf(listDon.size()));
    }

    private void setDoanhThu(String[] time) {
        if (time != null) {
            listDon = donHangDAO.selectDonHang(null, "ngayMua>=? and ngayMua<?", time, null);
            if (listDon != null) {
                Log.d(TAG, "setDoanhThu: hope");
                if (listDon.size() > 0) {
                    int doanhThu = addData.tinhTongKhoanThu(listDon) * 1000;
                    textView_GiaTien.setText(changeType.stringToStringMoney(doanhThu+""));
                } else {
                    textView_GiaTien.setText(changeType.stringToStringMoney("0"));
                }
            } else {
                textView_GiaTien.setText(changeType.stringToStringMoney("0"));
            }
        }
    }

    private void sortLaptop(View view) {
        TextView titleSort, tenLaptop, giaTien;
        AppCompatButton theoDoanhThu, theoSLAsc, theoSLDesc;
        ImageView imageView;
        theoDoanhThu = view.findViewById(R.id.button_Theo_DoanhThu);
        theoSLAsc = view.findViewById(R.id.button_Theo_ThapTien);
        theoSLDesc = view.findViewById(R.id.button_Theo_CaoTien);
        titleSort = view.findViewById(R.id.textView_Title);
        tenLaptop = view.findViewById(R.id.textView_TenLaptop);
        giaTien = view.findViewById(R.id.textView_GiaTien_Laptop);
        imageView = view.findViewById(R.id.imageView_Laptop);
        titleSort.setText("Sản phẩm bán chạy nhất theo doanh thu");
        Laptop laptop = addData.getTop1DoanhThu(listLap);
        if (laptop != null) {
            tenLaptop.setText(laptop.getTenLaptop());
            giaTien.setText("Giá tiền: " + laptop.getGiaTien());
            imageView.setImageBitmap(changeType.byteToBitmap(laptop.getAnhLaptop()));
        }

        theoDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleSort.setText("Sản phẩm bán chạy nhất theo doanh thu");
                Laptop laptop = addData.getTop1DoanhThu(listLap);
                if (laptop != null) {
                    tenLaptop.setText(laptop.getTenLaptop());
                    giaTien.setText("Giá tiền: " + laptop.getGiaTien());
                    imageView.setImageBitmap(changeType.byteToBitmap(laptop.getAnhLaptop()));
                }
            }
        });

        theoSLAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleSort.setText("Sản phẩm thấp tiền bán chạy nhất theo số lượng");
                Laptop laptop = addData.getTop1SoLuong(listLap, "asc");
                if (laptop != null) {
                    tenLaptop.setText(laptop.getTenLaptop());
                    giaTien.setText("Giá tiền: " + laptop.getGiaTien());
                    imageView.setImageBitmap(changeType.byteToBitmap(laptop.getAnhLaptop()));
                }
            }
        });

        theoSLDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleSort.setText("Sản phẩm cao tiền bán chạy nhất theo số lượng");
                Laptop laptop = addData.getTop1SoLuong(listLap, "desc");
                if (laptop != null) {
                    tenLaptop.setText(laptop.getTenLaptop());
                    giaTien.setText("Giá tiền: " + laptop.getGiaTien());
                    imageView.setImageBitmap(changeType.byteToBitmap(laptop.getAnhLaptop()));
                }
            }
        });
    }

    private String onclickChangeTime() {
        Calendar calendar = Calendar.getInstance();
        changeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month += 1;
                        if (month < 10) {
                            getMonth = "0" + month;
                        } else {
                            getMonth = String.valueOf(month);
                        }
                        textView_Date.setText("Tháng " + getMonth + "/" + year);
                        setDoanhThu(changeType.intDateToStringDate(month, year));
                        Log.d(TAG, "onDateSet: hope " + year + "/" + month);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        return getDate;
    }
}