/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 * POJO class for reader management
 * Represents a reader in the library system
 * 
 * @author abc
 */
public class QuanLyDocGia {
    private int maDocGia;        // Mã độc giả
    private String tenDocGia;    // Tên độc giả
    private String diaChi;       // Địa chỉ độc giả
    private String soDienThoai;  // Số điện thoại
    private String email;        // Email
    private String ngayDangKy;   // Ngày đăng ký
    private boolean daXoa;       // Trạng thái đã xóa

    // Constructor không tham số
    public QuanLyDocGia() {
        this.maDocGia = 0;
        this.tenDocGia = "";
        this.diaChi = "";
        this.soDienThoai = "";
        this.email = "";
        this.ngayDangKy = "";
        this.daXoa = false;
    }
    
    public QuanLyDocGia(int maDocGia, String tenDocGia, String diaChi, String soDienThoai, String email, String ngayDangKy, boolean daXoa) {
        this.maDocGia = maDocGia;
        this.tenDocGia = tenDocGia;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayDangKy = ngayDangKy;
        this.daXoa = daXoa;
    }
    
    public QuanLyDocGia(String tenDocGia, String diaChi, String soDienThoai, String email,boolean daXoa) {
        this.tenDocGia = tenDocGia;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayDangKy = "0000-00-00";
        this.daXoa = daXoa;
    }
    
    
    // Getter và Setter
    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    // Override toString để hiển thị thông tin độc giả
    @Override
    public String toString() {
        return "QuanLyDocGia{" +
                "maDocGia=" + maDocGia +
                ", tenDocGia='" + tenDocGia + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", ngayDangKy='" + ngayDangKy + '\'' +
                ", daXoa=" + daXoa +
                '}';
    }
}
