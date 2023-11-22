package com.example.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class themactivity  extends AppCompatActivity {

    private EditText ettMaNV,ettTenNV,ettTuoiNV,ettDiaChi;
    private Button btnthem,btnBack;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_activity);
        map();
        dbHelper = new DBHelper(this);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themNhanVien();
            }
        });
    }
    private void themNhanVien() {
        String MaNV = ettMaNV.getText().toString().trim();
        String TenNV = ettTenNV.getText().toString().trim();
        String TuoiNV = ettTuoiNV.getText().toString().trim();
        String DiaChi = ettDiaChi.getText().toString().trim();

        dbHelper.themNhanVien(MaNV, TenNV, TuoiNV, DiaChi);

        // Cập nhật lại danh sách và thông báo thành công
        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        ettMaNV.setText("");
        ettTenNV.setText("");
        ettTuoiNV.setText("");
        ettDiaChi.setText("");

        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
    }
public void map(){
    ettMaNV = findViewById(R.id.ettMaNV);
    ettTenNV = findViewById(R.id.ettTenNV);
    ettTuoiNV = findViewById(R.id.ettTuoiNV);
    ettDiaChi = findViewById(R.id.ettDiaChi);
    btnthem = findViewById(R.id.btnthem);
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
