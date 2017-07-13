package Models;

public class SvNhom {
	private String maSv;
	private String tenSv;
	private String nhomTruongHien;
	private boolean nhomTruong;
	
	public SvNhom(String ma, String ten, boolean nt) {
        this.maSv = ma;
        this.tenSv = ten;
        this.nhomTruong = nt;
        if(nt)
        	this.nhomTruongHien = "X";
        else
        	this.nhomTruongHien = "";
    }
	
	public String getMaSv() {
		return maSv;
	}
	public void setMaSv(String masv) {
		this.maSv = masv;
	}
	
	public String getTenSv() {
		return tenSv;
	}
	public void setTenSv(String tensv) {
		this.tenSv = tensv;
	}
	
	public boolean getNhomTruong() {
		return nhomTruong;
	}
	public void setNhomTruong(boolean nt) {
		this.nhomTruong = nt;
		if(nt)
        	this.nhomTruongHien = "X";
        else
        	this.nhomTruongHien = "";
	}
	
	public String getNhomTruongHien() {
		return nhomTruongHien;
	}
}
