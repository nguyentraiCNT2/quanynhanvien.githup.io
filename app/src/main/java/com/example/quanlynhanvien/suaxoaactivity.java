package com.example.quanlynhanvien;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class suaxoaactivity  extends AppCompatActivity {
    private EditText ettMaNV,ettTenNV,ettTuoiNV,ettDiaChi;
    private Button btnSua,btnBack,btnXoa;
    private DBHelper dbHelper;
    private NhanVien nhanvienToUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suaxoa_activity);
        map();
        dbHelper = new DBHelper(this);

        // Nhận thông tin thí sinh từ Intent
        String ManhanvienToUpdate = getIntent().getStringExtra("MA_NHAN_VIEN_TO_UPDATE");
        if (ManhanvienToUpdate != null) {
            nhanvienToUpdate = dbHelper.getNhanVien(ManhanvienToUpdate);
            loadData();
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaNhanVien();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoaThiSinh();
            }
        });
    }
    private void loadData() {
        // Hiển thị thông tin thí sinh trên giao diện
        ettMaNV.setText(nhanvienToUpdate.getMaNV());
        ettTenNV.setText(nhanvienToUpdate.getTenNV());
        ettTuoiNV.setText(String.valueOf(nhanvienToUpdate.getTuoiNV()));
        ettDiaChi.setText(String.valueOf(nhanvienToUpdate.getDiaChi()));
    }
    private void suaNhanVien() {
        // Lấy thông tin từ EditText
        String MaNV = ettMaNV.getText().toString().trim();
        String TenNV = ettTenNV.getText().toString().trim();
        String TuoiNV = ettTuoiNV.getText().toString().trim();
        String DiaChi = ettDiaChi.getText().toString().trim();

        // Cập nhật thông tin thí sinh trong cơ sở dữ liệu
        dbHelper.suaNhanVien(nhanvienToUpdate.getMaNV(), MaNV, TenNV, TuoiNV, DiaChi);

        // Hiển thị thông báo thành công và kết thúc activity
        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }
    private void xoaThiSinh() {
        if (nhanvienToUpdate != null) {
            String maNhanVienToDelete = nhanvienToUpdate.getMaNV();
            dbHelper.xoaNhanVien(maNhanVienToDelete);
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }
    public void map(){
        ettMaNV = findViewById(R.id.ettMaNV);
        ettTenNV = findViewById(R.id.ettTenNV);
        ettTuoiNV = findViewById(R.id.ettTuoiNV);
        ettDiaChi = findViewById(R.id.ettDiaChi);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gửi kết quả về khi quay về
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
