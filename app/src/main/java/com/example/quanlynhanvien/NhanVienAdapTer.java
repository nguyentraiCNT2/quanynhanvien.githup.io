package com.example.quanlynhanvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NhanVienAdapTer extends ArrayAdapter<NhanVien> {
    public NhanVienAdapTer(@NonNull Context context, @NonNull List<NhanVien> NhanVienList){
        super(context, 0, NhanVienList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.viewlist_activity, parent, false);
        }
        NhanVien nhanVien =getItem(position);
        TextView manhanvien = convertView.findViewById(R.id.manhanvien);
        TextView hotennhanvien = convertView.findViewById(R.id.hotennhanvien);
        TextView tuoi = convertView.findViewById(R.id.tuoi);


        if (nhanVien != null) {
            manhanvien.setText("" + nhanVien.getMaNV());
            hotennhanvien.setText("" + nhanVien.getTenNV());
            tuoi.setText("" + nhanVien.getTuoiNV());
        }

        return convertView;
    }
}
