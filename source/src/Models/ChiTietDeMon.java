package Models;

import java.util.Vector;

public class ChiTietDeMon extends DeMon {
	private String giaoVienPhuTrach;
	private String maNhom;
	
	public ChiTietDeMon(Vector<String> info)
	{
		super();
		maDe =  info.get(0);
		moTa =  info.get(1);
		loaiDA =  info.get(2);
		loaiDeHien =  info.get(3);
		giaoVienPhuTrach =  info.get(4);
		maNhom = info.get(5);
		
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
