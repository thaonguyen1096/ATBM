package Models;

import java.util.Vector;

public class Nhom {

    private String maNhom;
    private String tenNhom;
    //SinhVien
    private String nhomTruong;
    private int soLuong;
    //private Vector<User> thanhVien;
    
    public Nhom(Vector<String> info){
    	maNhom = info.get(0);
    	tenNhom = info.get(1);
    	nhomTruong = info.get(2);
    	soLuong = Integer.parseInt(info.get(3));
    }

    public Nhom(String ma, String ten, String nt, int sl) {
    	this.maNhom = ma;
    	this.tenNhom = ten;
    	this.nhomTruong = nt;
    	this.soLuong = sl;
    }
    //ma nhom
    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String ma) {
        this.maNhom = ma;
    }
    //ten nhom
    public String getTenNhom() {
        return tenNhom;
    }
   
    public void setTenNhom(String ten) {
        this.tenNhom = ten;
    }
  //nhom truong
    public String getNhomTruong() {
        return nhomTruong;
    }
    
    public void setNhomTruong(String ten) {
        this.nhomTruong = ten;
    }
    //so luong
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int sl) {
        this.soLuong = sl;
    }
    
    
}
