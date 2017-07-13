package Models;

import java.util.Vector;

public class LoaiDA {
	private String maLoai;
	private String tenLoai;
	
	public LoaiDA(String maLoai, String tenLoai) {
        	this.maLoai = maLoai;
        	this.tenLoai = tenLoai;
    }
	
	public LoaiDA(Vector<String> info){
		maLoai = info.get(0);
		tenLoai = info.get(1);
	}


	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	
	
	@Override
    	public String toString()  {
        	return this.tenLoai;
    	}
}
