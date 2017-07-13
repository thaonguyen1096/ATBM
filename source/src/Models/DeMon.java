package Models;

import java.util.Vector;

public class DeMon extends De {
	private String mon;
	protected String loaiDA;
//	private boolean loaiDe;
	private int slDangKyTD;
	private int slDangKy;
	private int slSVNhom;
	private String ngayBDDangKy;
	private String deadline;
	private String giaoVienPhuTrach;
	protected String loaiDeHien;
	private String maNhom;
	
	public DeMon(String de, String mon, String loaiDeh, String loaiDA, String moTa, int slDangKyTD, int slDangKy, int slSVNhom, String ngayBDDangKy, String deadline, String gv, String nhom) {
		super();
		this.maDe = de;
		this.mon = mon;
		this.loaiDA = loaiDA;
//		this.loaiDe = loaiDe;
		this.moTa = moTa;
		this.slDangKyTD = slDangKyTD;
		this.slDangKy = slDangKy;
		this.slSVNhom = slSVNhom;
		this.ngayBDDangKy = ngayBDDangKy;
		this.deadline = deadline;
		this.giaoVienPhuTrach = gv;
		this.maNhom = nhom;
		this.loaiDeHien = loaiDeh;
//		this.loaiDe = loaiDe;
//		if(loaiDe)
//			this.loaiDeHien = "Cá nhân";
//		else
//			this.loaiDeHien = "Nhóm";
	}
	
	public DeMon(){
		
	}
	
	public DeMon(Vector<Object> info){
		maDe = (String) info.get(0);
		moTa = (String) info.get(1);
		mon = (String) info.get(2);
		loaiDeHien = (String) info.get(3);
		loaiDA = (String) info.get(4);
		ngayBDDangKy = (String) info.get(5);
		slDangKyTD = (int) info.get(6);
		slDangKy = (int) info.get(7);
		slSVNhom = (int) info.get(8);
		deadline = (String) info.get(9);
	}
	
	//ma de
	public String getDe() {
		return maDe;
	}
	public void setDe(String de) {
		this.maDe = de;
	}
	//ma mon hoc
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	//loai do an
	public String getLoaiDA() {
		return loaiDA;
	}
	public void setLoaiDA(String da) {
		this.loaiDA = da;
	}
//	//loai de
//	public boolean isLoaiDe() {
//		return loaiDe;
//	}
//	public void setLoaiDe(boolean loaiDe) {
//		this.loaiDe = loaiDe;
//		if(loaiDe)
//			this.loaiDeHien = "Cá nhân";
//		else
//			this.loaiDeHien = "Nhóm";
//			
//	}
	public void setLoaiDeHien(String l){
		this.loaiDeHien = l;
	}
	public String getLoaiDeHien() {
		return loaiDeHien;
	}
	//mo ta
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	//sl dang ky toi da
	public int getSlDangKyTD() {
		return slDangKyTD;
	}
	public void setSlDangKyTD(int slDangKyTD) {
		this.slDangKyTD = slDangKyTD;
	}
	//so luong sinh vien nhom
	public int getSlSVNhom() {
		return slSVNhom;
	}
	public void setSlSVNhom(int slSVNhom) {
		this.slSVNhom = slSVNhom;
	}
	//ngay bat dau dang ky
	public String getNgayBDDangKy() {
		return ngayBDDangKy;
	}
	public void setNgayBDDangKy(String ngayBDDangKy) {
		this.ngayBDDangKy = ngayBDDangKy;
	}
	//sl da dang ky
	public int getSlDangKy() {
		return slDangKy;
	}
	public void setSlDangKy(int slDangKy) {
		this.slDangKy = slDangKy;
	}
	//deadline
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	//Bang danh sach de con han dang ky va bang giao vien TTDe cua lop: rong
	//Bang xem danh sach de da dang ky:
	//		- Neu la de ca nhan: hien giao vien phu trach de
	//		- Neu la de nhom: hien giao vien phu trach nhom
	public String getGiaoVienPhuTrach() {
		return giaoVienPhuTrach;
	}
	public void setGiaoVienPhuTrach(String gv) {
		this.giaoVienPhuTrach = gv;
	}
	//Bang danh sach de con han dang ky va bang giao vien TTDe cua lop: rong
	//Bang xem danh sach de da dang ky:
	//		- Neu la de ca nhan: rong
	//		- Neu la de nhom: hien ma nhom
	public String getMaNhom() {
		return maNhom;
	}
	public void setMaNhom(String ma) {
		this.maNhom = ma;
	}
}