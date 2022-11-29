package com.nhom5.quanlylaptop.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.nhom5.quanlylaptop.R;

public class DiaChi_Manager_Activity extends AppCompatActivity {

    Context context = this;
    Spinner spinnerThanhPho, spinnerQuanHuyen, spinnerXaPhuong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_manager);
        spinnerThanhPho = findViewById(R.id.spinner_ThanhPho);
        spinnerQuanHuyen = findViewById(R.id.spinner_QuanHuyen);
        spinnerXaPhuong = findViewById(R.id.spinner_PhuongXa);
        useToolbar();
        setSpinnerThanhPho();
    }

    private void setSpinnerThanhPho() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, thanhPho);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThanhPho.setAdapter(adapter);

        spinnerThanhPho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tp = spinnerThanhPho.getItemAtPosition(position).toString();
                switch (tp) {
                    case "Cần Thơ": {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quanThuocCanTho);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerQuanHuyen.setAdapter(adapter);
                        break;
                    }
                    case "Đà Nẵng": {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quanThuocDaNang);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerQuanHuyen.setAdapter(adapter);
                        break;
                    }
                    case "Hà Nội": {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quanThuocHaNoi);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerQuanHuyen.setAdapter(adapter);
                        break;
                    }
                    case "Thanh Hóa": {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quanThuocThanhHoa);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerQuanHuyen.setAdapter(adapter);
                        break;
                    }
                    case "Ninh Bình": {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quanThuocNinhBinh);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerQuanHuyen.setAdapter(adapter);
                        break;
                    }
                    case "Thái Bình": {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quanThuocThaiBinh);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerQuanHuyen.setAdapter(adapter);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Phần cần làm thêm
    private void setSpinnerQuanHuyen() {
    }

    private void useToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Normal));
        TextView titleToolbar = findViewById(R.id.textView_Title_Toolbar);
        titleToolbar.setText("Địa chỉ nhận hàng");
        ImageButton back = findViewById(R.id.imageButton_Back_Toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    String[] thanhPho = {"Chọn Tỉnh/ Thành phố", "Hà Nội", "Ninh Bình", "Thái Bình", "Đà Nẵng", "Thanh Hóa", "Cần Thơ"};
    //quận / huyện
    String[] quanThuocCanTho = {"Chọn Quận/ Huyện", "Phong Điền", "Ninh Kiều", "Bình Thủy", "Ô Môn", "Thốt Nốt", "Cờ Đỏ"};
    String[] quanThuocDaNang = {"Chọn Quận/ Huyện", "Hải Châu", "Cẩm Lệ", "Thanh Khê", "Liên Chiểu", "Ngũ Hành Sơn"};
    String[] quanThuocHaNoi = {"Chọn Quận/ Huyện", "Bắc Từ Liêm", "Nam Từ Liêm", "Đống Đa", "Cầu Giấy"};
    String[] quanThuocThanhHoa = {"Chọn Quận/ Huyện", "Nga Sơn", "Nông Cống", "Hà Trung", "Hậu Lộc", "Thọ Xuân"};
    String[] quanThuocNinhBinh = {"Chọn Quận/ Huyện", "Hoa Lư", "Kim Sơn", "Quan Nho", "Tam Điệp"};
    String[] quanThuocThaiBinh = {"Chọn Quận/ Huyện", "Hưng Hà", "Thái Thụy", "Đông Hưng", "Kiến Xương"};
    //Phường/ Xã
    String[] xaThuocPhongDien = {"Chọn Phường/ Xã", "Phong Bình", "Phong Hải", "Điền Hương", "Điền Hải", "Điền Hòa", "Điền Môn", "Điền Lộc"};
    String[] xaThuocNinhKieu = {"Chọn Phường/ Xã", "An Hòa", "Thới Bình", "An Nghiệp", "An Cư", "An Hội", "Tân An", "An Phú", "Xuân Khánh", "Hưng Lợi"};
    String[] xaThuocBinhThuy = {"Chọn Phường/ Xã", "Bùi Hữu Nghĩa", "Long Hòa", "Trà An", "Thới An Đông"};
    String[] xaThuocOMon = {"Chọn Phường/ Xã", "Định Môn", "Phước Thới", "Tân Thới", "Thới Đông", "Trường Lạc", "Trường Xuân"};
    String[] xaThuocThotNot = {"Chọn Phường/ Xã", "Trung Kiên", "Thuận Hưng", "Tân Hưng", "Tân Lộc"};
    String[] xaThuocCoDo = {"Chọn Phường/ Xã", "Đông Hiệp", "Đông Thắng", "Trung Hưng", "Thạch Phú", "Trung An"};
    //
    String[] xaThuocHaiChau = {"Chọn Phường/ Xã", "Hải Châu 1", "Hải Châu 2", "Thạch Thang", "Thanh Bình", "Thuận Phước", "Nam Dương", "Phước Ninh", "Bình Hiên", "Hòa Cường Nam", "Hòa Cường Bắc"};
    String[] xaThuocCamLe = {"Chọn Phường/ Xã", "Khuê Trung", "Hòa Thọ Đông", "Hòa Thọ Tây", "Hòa An", "Hòa Phát", "Hòa Xuân"};
    String[] xaThuocThanhKhe = {"Chọn Phường/ Xã", "Vĩnh Trung", "Tân Chính", "Thạc Gián", "Chính Gián", "Tam Thuận", "Xuân Hà"};
    String[] xaThuocLienChieu = {"Chọn Phường/ Xã", "Hòa Minh", "Hòa Khánh Nam", "Hoà Khánh Bắc", "Hòa Hiệp Nam", " Hoà Hiệp Bắc"};
    String[] xaThuocNguHanhSon = {"Chọn Phường/ Xã", "Hòa Hải", "Hòa Quý", "Khuê Mỹ", "Mỹ An"};
    //
    String[] xaThuocBacTuLiem = {"Chọn Phường/ Xã", "Đức Thắng", "Đông Ngạc", "Thụy Phương", "Liên Mạc", "Thượng Cát", "Tây Tựu", "Minh Khai", "Phú Diễn", "Phúc Diễn", "Xuân Đỉnh"};
    String[] xaThuocNamTuLiem = {"Chọn Phường/ Xã", "Xuân Phương", "Phương Canh", "Ngọc Mạch", "Mễ Trì", "Tây Mỗ"};
    String[] xaThuocDongDa = {"Chọn Phường/ Xã", "Cát Linh", "Hàng Bột", "Láng Hạ", "Láng Thượng", "Kim Liên", "Khương Thượng", "Nam Đồng", "Ngã Tư Sở"};
    String[] xaThuocCauGiay = {"Chọn Phường/ Xã", "Nghĩa Đô", "Nghĩa Tân", "Mai Dịch", "Yên Hòa", "Trung Hòa"};
    //
    String[] xaThuocNgaSon = {"Chọn Phường/ Xã", "Nga An", "Nga Bạch", "Nga Điền", "Nga Giáp", "Nga Hải", "Nga Hưng", "Nga Liên", "Nga Lĩnh", "Nga Mỹ", "Nga Nhân", "Nga Phú", "Nga Thạch", "Nga Thái", "Nga Thắng", "Nga Thanh", "Nga Thành", "Nga Thiện", "Nga Thủy", "Nga Trung", "Nga Trường", "Nga Văn", "Nga Vịnh", "Nga Yên"};
    String[] xaThuocNongCong = {"Chọn Phường/ Xã", "Công Bình", "Công Chính", "Hoàng Sơn", "Minh Khôi", "Minh Nghĩa", "Minh Thọ", "Tân Khang", "Tân Phúc"};
    String[] xaThuocHaTrung = {"Chọn Phường/ Xã", "Hà Bắc", "Hà Bình", "Hà Châu", "Hà Giang", "Hà Hải", "Hà Lai", "Yến Sơn", "Lĩnh Toại", "Yên Dương"};
    String[] xaThuocHauLoc = {"Chọn Phường/ Xã", "Cầu Lộc", "Châu Lộc", "Đa Lộc", "Đại Lộc", "Đồng Lộc", "Hải Lộc", "Hoa Lộc", "Hòa Lộc", "Hưng Lộc", "Liên Lộc", "Lộc Sơn", "Lộc Tân"};
    String[] xaThuocThoXuan = {"Chọn Phường/ Xã", "Thọ Bình", "Thọ Cường", "Thọ Dân", "Thọ Diên", "Thọ Hải", "Thọ Lâm", "Thọ Lập", "Thọ Lộc", "Thọ Minh", "Thọ Ngọc"};
    //
    String[] xaThuocHoaLu = {"Chọn Phường/ Xã", "Ninh Giang", "Ninh Khang", "Ninh Mỹ", "Ninh Vân", "Ninh Thắng", "Ninh Hải", "Ninh Xuân"};
    String[] xaThuocKimSon = {"Chọn Phường/ Xã", "Phát Diệm", "Chất Bình", "Yên Mật", "Kim Đông", "Lai Thành", "Yên Lộc", "Tân Thành", "Lưu Phương"};
    String[] xaThuocQuanNho = {"Chọn Phường/ Xã", "Cúc Phương", "Đồng Phong", "Đức Long", "Gia Lâm", "Gia Sơn", "Gia Thủy", "Gia Tường", "Kỳ Phú", "Lạc Vân", "Lạng Phong"};
    String[] xaThuocTamDiep = {"Chọn Phường/ Xã", "Quang Sơn", "Yên Bình", "Yên Sơn", "Đông Sơn", "Tây Sơn", "Tân Bình"};
    //
    String[] xaThuocHungHa = {"Chọn Phường/ Xã", "Canh Tân", "Chí Hòa", "Chi Lăng", "Cộng Hòa", "Dân Chủ", "Điệp Nông", "Đoan Hùng", "Độc Lập"};
    String[] xaThuocThaiThuy = {"Chọn Phường/ Xã", "Thái Dương", "Thái Giang", "Thái Hà", "Thái Hoà", "Thái Học", "Thái Hồng", "Thái Hưng", "Thái Nguyên", "Thái Phúc", "Thái Sơn"};
    String[] xaThuocDongHung = {"Chọn Phường/ Xã", "Đông Dương", "Đông Hoàng", "Đông Á", "Đông Phong", "Đông Huy", "Đông Lĩnh", "Đông Kinh", "Đông Tân"};
    String[] xaThuocKienXuong = {"Chọn Phường/ Xã", "Quang Minh", "Bình Minh", "Thượng Hiền", " Quang Bình", " Quang Lịch", "Vũ Trung", "Vũ Quý"};

}