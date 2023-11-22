package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvNhanvien;
    private Button btncreate;
    private List<NhanVien> listnhanvien ;
    private NhanVienAdapTer nhanVienAdapter;
    private static final int REQUEST_CODE_THEM = 1;
    private static final int REQUEST_CODE_SUA_XOA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();
                listnhanvien = GetAll();
        updateListView(listnhanvien);
        nhanVienAdapter = new NhanVienAdapTer(this, listnhanvien);
        lvNhanvien.setAdapter(nhanVienAdapter);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, themactivity.class);
                startActivityForResult(intent, REQUEST_CODE_THEM);
            }
        });
        lvNhanvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                NhanVien selectedNhaVien = listnhanvien.get(position);

                Intent intent = new Intent(MainActivity.this, suaxoaactivity.class);
                intent.putExtra("MA_NHAN_VIEN_TO_UPDATE", selectedNhaVien.getMaNV());
                startActivityForResult(intent, REQUEST_CODE_SUA_XOA);

                return true;
            }
        });

    }
    private List<NhanVien> GetAll() {
        List<NhanVien> ls = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(this);
        ls = dbHelper.GetAll();
        return ls;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_THEM) {
            if (resultCode == RESULT_OK) {
                listnhanvien = GetAll();
                nhanVienAdapter.clear();
                nhanVienAdapter.addAll(listnhanvien);
                nhanVienAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == REQUEST_CODE_SUA_XOA) {
            if (resultCode == RESULT_OK) {
                listnhanvien = GetAll();
                nhanVienAdapter.clear();
                nhanVienAdapter.addAll(listnhanvien);
                nhanVienAdapter.notifyDataSetChanged();
            }
        }
    }
    private void updateListView(List<NhanVien> danhSach) {
        nhanVienAdapter = new NhanVienAdapTer(this, danhSach);
        lvNhanvien.setAdapter(nhanVienAdapter);
    }
    public void map(){
        lvNhanvien = findViewById(R.id.lvNhanvien);
        btncreate = findViewById(R.id.btncreate);
    }
}