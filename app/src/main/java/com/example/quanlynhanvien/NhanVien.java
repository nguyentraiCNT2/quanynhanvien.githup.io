package com.example.quanlynhanvien;

public class NhanVien {
    private String MaNV;
    private String TenNV;
    private String TuoiNV;
    private String DiaChi;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String tuoiNV, String diaChi) {
        MaNV = maNV;
        TenNV = tenNV;
        TuoiNV = tuoiNV;
        DiaChi = diaChi;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getTuoiNV() {
        return TuoiNV;
    }

    public void setTuoiNV(String tuoiNV) {
        TuoiNV = tuoiNV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
