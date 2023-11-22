package com.example.quanlynhanvien;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NhanVienDB";
    public static final String TABLE_NAME = "NhanVien";
    public static final String COL_MA_NHAN_VIEN = "MaNV";
    public static final String COL_TEN_NHAN_VIEN = "TenNV";
    public static final String COL_DIA_CHI= "DiaChi";
    public static final String COL_TUOI_NHAN_VIEN = "TuoiNV";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_MA_NHAN_VIEN + " TEXT, " +
                COL_TEN_NHAN_VIEN + " TEXT, " +
                COL_DIA_CHI + " TEXT, " +
                COL_TUOI_NHAN_VIEN + " TEXT) " ;
        db.execSQL(createTable);

        themDuLieuMau(db, "NV-110", "Nguyen Van A", "25", "Ha Noi");
        themDuLieuMau(db, "NV-112", "Nguyen Van B", "36", "phu tho");
        themDuLieuMau(db, "NV-113", "Nguyen Van C", "35", "HaNoi");
        themDuLieuMau(db, "NV-114", "Nguyen Van D", "26", "Hai duong");
        themDuLieuMau(db, "NV-115", "Nguyen Van E", "45", "Ha Noi");
        themDuLieuMau(db, "NV-116", "Nguyen Van F",  "26", "Da Nang");
    }



    private void themDuLieuMau(SQLiteDatabase db, String MaNV, String TenNV, String TuoiNV, String DiaChi) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MA_NHAN_VIEN, MaNV);
        contentValues.put(COL_TEN_NHAN_VIEN, TenNV);
        contentValues.put(COL_DIA_CHI,DiaChi );
        contentValues.put(COL_TUOI_NHAN_VIEN, TuoiNV);
        db.insert(TABLE_NAME, null, contentValues);
    }
    public void themNhanVien( String MaNV, String TenNV, String TuoiNV, String DiaChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MA_NHAN_VIEN, MaNV);
        contentValues.put(COL_TEN_NHAN_VIEN, TenNV);
        contentValues.put(COL_DIA_CHI, DiaChi);
        contentValues.put(COL_TUOI_NHAN_VIEN, TuoiNV);

        db.insert(TABLE_NAME, null, contentValues);

    }
    public List<NhanVien> GetAll() {
        List<NhanVien> ls = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String MaNV = cursor.getString(cursor.getColumnIndex(COL_MA_NHAN_VIEN));
                @SuppressLint("Range") String TenNV = cursor.getString(cursor.getColumnIndex(COL_TEN_NHAN_VIEN));
                @SuppressLint("Range") String DiaChi = cursor.getString(cursor.getColumnIndex(COL_DIA_CHI));
                @SuppressLint("Range") String TuoiNV = cursor.getString(cursor.getColumnIndex(COL_TUOI_NHAN_VIEN));
                ls.add(new NhanVien(MaNV, TenNV, TuoiNV, DiaChi));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ls;
    }
    public NhanVien getNhanVien(String MaNhanvien) {
        NhanVien nhanVien = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_MA_NHAN_VIEN + "=?";
            cursor = db.rawQuery(query, new String[]{MaNhanvien});

            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String MaNV = cursor.getString(cursor.getColumnIndex(COL_MA_NHAN_VIEN));
                @SuppressLint("Range") String TenNV = cursor.getString(cursor.getColumnIndex(COL_TEN_NHAN_VIEN));
                @SuppressLint("Range") String DiaChi = cursor.getString(cursor.getColumnIndex(COL_DIA_CHI));
                @SuppressLint("Range") String TuoiNV = cursor.getString(cursor.getColumnIndex(COL_TUOI_NHAN_VIEN));
                nhanVien = new NhanVien(MaNV, TenNV, TuoiNV, DiaChi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return nhanVien;
    }
    public void suaNhanVien(String oldMaNV, String newMaNv, String TenNV,String TuoiNv,String DiaChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_MA_NHAN_VIEN, newMaNv);
        values.put(COL_TEN_NHAN_VIEN, TenNV);
        values.put(COL_DIA_CHI, DiaChi);
        values.put(COL_TUOI_NHAN_VIEN, TuoiNv);

        try {
            db.update(TABLE_NAME, values, COL_MA_NHAN_VIEN + " = ?", new String[]{oldMaNV});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    public void xoaNhanVien(String MaNV) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(TABLE_NAME, COL_MA_NHAN_VIEN + " = ?", new String[]{MaNV});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
