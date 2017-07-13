package Models;

public class LoaiDe {
	private boolean maLoai;
	private String tenLoai;
	
	public LoaiDe(boolean maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }
	
	public boolean getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(boolean maLoai) {
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
